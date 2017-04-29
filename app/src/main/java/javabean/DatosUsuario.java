package javabean;

import java.io.Serializable;

/**
 * Created by Maria on 28/4/17.
 */

public class DatosUsuario implements Serializable{

    private String nombre;
    private int telefono;
    private String email;

    public DatosUsuario(String nombre, int telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
