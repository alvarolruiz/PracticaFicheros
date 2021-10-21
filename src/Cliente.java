public class Cliente {
    private String nombre;
    private String apellidos;
    private String dni;
    private int telefono;
    private String direccion;

    /**

     */
    public Cliente(String nombre, String apellidos, String dni,
                   int telefono, String direccion)
    {
        setNombre(nombre);
        setApellidos(apellidos);
        setDni(dni);
        setTelefono(telefono);
        setDireccion(direccion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.length()<=25&&!nombre.isEmpty()) {
            this.nombre = nombre;
        }else{
            System.out.println("El nombre no puede tener más de 25 caracteres");
        }
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if(apellidos.length()<=25&&!apellidos.isEmpty()) {
            this.apellidos = apellidos;
        }else{
            System.out.println("El apellido no puede tener más de 25 caracteres ni estar vacío");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if(dni.length()==9&&!apellidos.isEmpty()) {
            this.dni = dni;
        }else{
            System.out.println("El dni no puede tener más de 9 caracteres ni estar vacío");
        }
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        if(telefono!=0){
            this.telefono=telefono;
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if(direccion.length()<=30&&!direccion.isEmpty()) {
            this.direccion = direccion;
        }else{
            System.out.println("La direccion no puede tener más de 30 caracteres ni estar vacío");
        }
    }
}
