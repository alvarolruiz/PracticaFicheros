package com.company;

public class Cliente {
    private String nombre;
    private String apellidos;
    private String dni;
    private int telefono;
    private String direccion;

    public Cliente() {

    }
    public Cliente(String nombre, String apellidos, String dni, int telefono, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {

    }
        public int getTelefono () {
            return telefono;
        }

        public void setTelefono ( int telefono){
            if (telefono != 0 && String.valueOf(telefono).length() == 9) {
                this.telefono = telefono;
            }
        }

        public String getDireccion () {
            return direccion;
        }

        public void setDireccion (String direccion){

        }

        @Override
        public String toString () {
            return "Cliente{" +
                    "nombre='" + nombre + '\'' +
                    ", apellidos='" + apellidos + '\'' +
                    ", dni='" + dni + '\'' +
                    ", telefono=" + telefono +
                    ", direccion='" + direccion + '\'' +
                    '}';
        }


}
