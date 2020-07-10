package co.com.pilae.almacen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.almacen.R;
import co.com.pilae.almacen.entidades.Persona;
import co.com.pilae.almacen.persistencia.room.DataBaseHelper;
import co.com.pilae.almacen.utilidades.ActionBarUtil;

public class AgregarCliente extends AppCompatActivity {

    @BindView(R.id.txtTope)
    public EditText txtTopeMaximo;
    @BindView(R.id.txtNombre)
    public EditText txtNombre;
    @BindView(R.id.txtDetalle)
    public EditText txtDetalle;
    @BindView(R.id.txtReferencia)
    public EditText txtReferencia;
    @BindView(R.id.txtSaldo)
    public EditText txtSaldo;
    @BindView(R.id.txtTelefono)
    public EditText txtTelefono;
    private DatabaseReference databaseReference;

    private ActionBarUtil actionBarUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.agregar_persona));
    }

    public void guardar(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String nombre = txtNombre.getText().toString();
        String detalle = txtDetalle.getText().toString();
        String referencia = txtReferencia.getText().toString();
        Double saldo = toDouble(txtSaldo.getText().toString());
        String telefono = txtTelefono.getText().toString();
        Double tope = toDouble(txtTopeMaximo.getText().toString());
        if (validarInformacion(nombre, detalle,referencia,saldo,telefono,tope)) {
            Persona persona = getPersona(nombre, detalle,referencia,saldo,telefono,tope);
            databaseReference.child("personas").push().setValue(persona);
            new InsercionPersona().execute(persona);
            finish();
        }
    }
    private Persona getPersona(String nombre, String detalle, String referencia, Double saldo, String telefono, Double tope) {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setDetalle(detalle);
        persona.setNumeroTelefono(telefono);
        persona.setReferencia(referencia);
        persona.setTope(tope);
        persona.setSaldo(saldo);
        return persona;
    }
    private boolean validarInformacion(String nombre, String detalle, String referencia, Double saldo, String telefono, Double tope) {
        boolean esValido = true;
        if ("".equals(nombre)) {
            txtNombre.setError(getString(R.string.requerido));
            esValido = false;
        }
        if ("".equals(detalle)) {
            txtDetalle.setError(getString(R.string.requerido));
            esValido = false;
        }
        if ("".equals(referencia)) {
            txtReferencia.setError(getString(R.string.requerido));
            esValido = false;
        }

        if ("".equals(telefono)) {
            txtTelefono.setError(getString(R.string.requerido));
            esValido = false;
        }
        if (saldo == 0) {
            txtSaldo.setError(getString(R.string.requerido));
            esValido = false;
        }
        if (tope == 0) {
            txtTopeMaximo.setError(getString(R.string.requerido));
            esValido = false;
        }
        return esValido;
    }
    private Double toDouble(String valor) {
        return "".equals(valor) ? 0 : Double.parseDouble(valor);
    }
    private class InsercionPersona extends AsyncTask<Persona, Void, Void> {

        @Override
        protected Void doInBackground(Persona... personas) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).getPersonaDao().insert(personas[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), getString(R.string.successfully), Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
