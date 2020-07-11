package co.com.pilae.almacen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.almacen.R;
import co.com.pilae.almacen.adapter.MovimientoAdapter;
import co.com.pilae.almacen.adapter.PersonaAdapter;
import co.com.pilae.almacen.entidades.Movimiento;
import co.com.pilae.almacen.entidades.Persona;
import co.com.pilae.almacen.utilidades.ActionBarUtil;

public class MovimientoActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.personaNombre)
    public TextView personaNombre;
    @BindView(R.id.personaDescripcion)
    public TextView personaDescripcion;
    @BindView(R.id.personaSaldo)
    public TextView personaSaldo;
    @BindView(R.id.personaTopeMaximo)
    public TextView personaTopeMaximo;
    @BindView(R.id.listViewMovimiento)
    public ListView listViewMovimiento;
    private DatabaseReference databaseReference;
    List<HashMap<String,Object>> movimientosHasmap = new ArrayList<HashMap<String, Object>>();
    List<Movimiento> movimientos = new ArrayList<>();
    private MovimientoAdapter movimientoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        ButterKnife.bind(this);
        initComponents();
        loadEquipo();

    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.cliente));
    }

    private void loadEquipo() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final String idPersona = (String) getIntent().getExtras().getSerializable("id");
        databaseReference.child("personas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        if(ds.getKey().equalsIgnoreCase(idPersona)){
                            personaNombre.setText(ds.child("nombre").getValue().toString());
                            personaDescripcion.setText(ds.child("referencia").getValue().toString());
                            personaSaldo.setText(ds.child("saldo").getValue().toString());
                            personaTopeMaximo.setText(ds.child("tope").getValue().toString());
                            movimientosHasmap = (List<HashMap<String, Object>>) ds.child("movimientos").getValue();
                            buildMovimientoList(movimientosHasmap);
                        }

                    }
                    loadAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void buildMovimientoList(List<HashMap<String, Object>> movimientosHasmap) {

        for (HashMap<String,Object> movimientoHash:movimientosHasmap){
            Movimiento movimiento = new Movimiento();
            movimiento.setValor(Double.parseDouble(movimientoHash.get("valor").toString()));
            movimiento.setFecha(movimientoHash.get("fecha").toString());
            movimiento.setDescripcion(movimientoHash.get("descripcion").toString());
            movimientos.add(movimiento);
        }
    }

    private void loadAdapter() {
        movimientoAdapter = new MovimientoAdapter(this,movimientos);
        listViewMovimiento.setAdapter(movimientoAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}