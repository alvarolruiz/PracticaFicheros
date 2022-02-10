package Entities;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(apellidos, cliente.apellidos) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono) && Objects.equals(direccion, cliente.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, dni, telefono, direccion);
    }
}
