package co.com.pilae.almacen.view;

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

import java.util.ArrayList;
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
        personas = db.getPersonaDao().listar();
        personaAdapter = new PersonaAdapter(this,personas);
        listViewPersonas.setAdapter(personaAdapter);
    }


    private void initComponents() {
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
