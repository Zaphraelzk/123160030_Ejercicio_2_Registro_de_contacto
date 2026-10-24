package com.ucenm.registrodecontacto;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactosRepository {

    private static final String PREFERENCES_NAME = "contactos";
    private static final String KEY_CONTACTOS = "lista";

    public static List<Contacto> cargar(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                PREFERENCES_NAME, Context.MODE_PRIVATE);
        String json = preferences.getString(KEY_CONTACTOS, "[]");
        List<Contacto> contactos = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                contactos.add(new Contacto(
                        item.getString("nombre"),
                        item.getString("correo"),
                        item.getString("telefono"),
                        item.getString("tipo")));
            }
        } catch (JSONException exception) {
            preferences.edit().remove(KEY_CONTACTOS).apply();
        }

        return contactos;
    }

    public static void agregar(Context context, Contacto contacto) {
        List<Contacto> contactos = cargar(context);
        contactos.add(contacto);

        JSONArray array = new JSONArray();
        for (Contacto item : contactos) {
            JSONObject jsonContacto = new JSONObject();
            try {
                jsonContacto.put("nombre", item.getNombre());
                jsonContacto.put("correo", item.getCorreo());
                jsonContacto.put("telefono", item.getTelefono());
                jsonContacto.put("tipo", item.getTipo());
                array.put(jsonContacto);
            } catch (JSONException exception) {
                throw new IllegalStateException("No se pudo guardar el contacto", exception);
            }
        }

        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_CONTACTOS, array.toString())
                .apply();
    }

    private ContactosRepository() {
    }
}
