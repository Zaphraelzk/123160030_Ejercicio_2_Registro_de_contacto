
package com.ucenm.registrodecontacto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionActivity extends AppCompatActivity {

    private static final String TAG = "CicloVida";
    private String nombre, correo, telefono, tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);
        Log.d(TAG, "ConfirmacionActivity: onCreate");

        nombre = getIntent().getStringExtra("nombre");
        correo = getIntent().getStringExtra("correo");
        telefono = getIntent().getStringExtra("telefono");
        tipo = getIntent().getStringExtra("tipo");

        TextView tvDatos = findViewById(R.id.tvDatos);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        tvDatos.setText(
                "Nombre: " + nombre +
                        "\nCorreo: " + correo +
                        "\nTeléfono: " + telefono +
                        "\nTipo: " + tipo
        );

        btnVolver.setOnClickListener(v -> finish());

        btnConfirmar.setOnClickListener(v -> {
            Contacto contacto = new Contacto(
                    nombre, correo, telefono, tipo);

            ContactosRepository.agregar(ConfirmacionActivity.this, contacto);

            Intent intent = new Intent(
                    ConfirmacionActivity.this,
                    ListaContactosActivity.class);

            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ConfirmacionActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ConfirmacionActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ConfirmacionActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ConfirmacionActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ConfirmacionActivity: onDestroy");
    }
}
