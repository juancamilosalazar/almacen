package co.com.pilae.almacen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.almacen.R;
import co.com.pilae.almacen.adapter.PersonaAdapter;
import co.com.pilae.almacen.entidades.Persona;
import co.com.pilae.almacen.persistencia.room.DataBaseHelper;
import co.com.pilae.almacen.utilidades.ActionBarUtil;

public class ActivityPersona extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.listViewPersonas)
    public ListView listViewPersonas;
    @BindView(R.id.txtBuscar)
    public EditText txtBuscar;
    List<Persona> personas = new ArrayList<>();
    private PersonaAdapter personaAdapter;
    private DatabaseReference databaseReference;
    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        ButterKnife.bind(this);
        initComponents();
        loadPersonas();
        buscarOnTextListener();
    }




    private void loadPersonas() {
        personas = new ArrayList<>();
        databaseReference.child("personas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String nombre  = ds.child("nombre").getValue().toString();
                        String numeroTelefono  = ds.child("numeroTelefono").getValue().toString();
                        String saldo  = ds.child("saldo").getValue().toString();
                        //String nombre  = ds.child("nombre").getValue().toString();
                        personas.add(buildPerson(nombre,numeroTelefono,saldo));
                    }
                    loadAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //personas = db.getPersonaDao().listar();

    }

    private void loadAdapter() {
        personaAdapter = new PersonaAdapter(this,personas);
        listViewPersonas.setAdapter(personaAdapter);
    }

    private Persona buildPerson(String nombre, String numeroTelefono, String saldo) {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setNumeroTelefono(numeroTelefono);
        persona.setSaldo(Double.valueOf(saldo));
        return persona;
    }


    private void initComponents() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.personas));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRestart() {
        super.onRestart();
        loadPersonas();
    }
    private void buscarOnTextListener() {
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                personaAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
