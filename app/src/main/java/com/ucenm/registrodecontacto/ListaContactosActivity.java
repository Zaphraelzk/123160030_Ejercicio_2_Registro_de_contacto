package com.ucenm.registrodecontacto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaContactosActivity extends AppCompatActivity {

    private static final String TAG = "CicloVida";
    private ContactoAdapter adapter;
    private TextView tvVacio;
    private final List<Contacto> contactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        Log.d(TAG, "ListaContactosActivity: onCreate");

        RecyclerView rvContactos = findViewById(R.id.rvContactos);
        Button btnNuevo = findViewById(R.id.btnNuevo);
        tvVacio = findViewById(R.id.tvVacio);

        rvContactos.setLayoutManager(new LinearLayoutManager(this));

        contactos.addAll(ContactosRepository.cargar(this));
        adapter = new ContactoAdapter(contactos);
        rvContactos.setAdapter(adapter);

        actualizarLista();

        btnNuevo.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ListaContactosActivity.this,
                    MainActivity.class);
            startActivity(intent);
        });
    }

    private void actualizarLista() {
        adapter.notifyDataSetChanged();

        tvVacio.setVisibility(
                contactos.isEmpty()
                        ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ListaContactosActivity: onResume");

        if (adapter != null) {
            contactos.clear();
            contactos.addAll(ContactosRepository.cargar(this));
            actualizarLista();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ListaContactosActivity: onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ListaContactosActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ListaContactosActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ListaContactosActivity: onDestroy");
    }
}
