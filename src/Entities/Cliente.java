package Entities;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;

    public Cliente() {

    }

    public Cliente(String nombre, String apellidos, String dni, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return this.getNombre() + this.getApellidos() + this.getDni() + this.getTelefono() + this.getDireccion();
    }


}
