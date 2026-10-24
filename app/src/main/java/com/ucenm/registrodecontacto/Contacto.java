
package com.ucenm.registrodecontacto;

public class Contacto {
    private final String nombre;
    private final String correo;
    private final String telefono;
    private final String tipo;

    public Contacto(String nombre, String correo,
                    String telefono, String tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getTipo() { return tipo; }
}
