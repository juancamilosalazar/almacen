package co.com.pilae.almacen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import co.com.pilae.almacen.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToConsultarClientes(View view) {
        Intent intent = new Intent(this,ActivityPersona.class);
        startActivity(intent);
    }

    public void goToAgregarClientes(View view) {
        Intent intent = new Intent(this,AgregarCliente.class);
        startActivity(intent);
    }
}
