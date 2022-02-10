package FileManager;

import Entities.Cliente;
import Utilities.Constantes;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FicheroAccesoAleatorioDatos {

    public RandomAccessFile ficheroClientes;
    private int tamanoRegistros;
    private Integer posicionPuntero;
    private String nombreFichero;
    private String permisos;


    public int getTamanoRegistros() {
        return tamanoRegistros;
    }

    public Integer getPosicionPuntero() { return posicionPuntero; }

    public FicheroAccesoAleatorioDatos(String nombre, String permisos, int tamanoRegistros) throws FileNotFoundException {
        this.nombreFichero = nombre;
        this.permisos = permisos;
        this.tamanoRegistros = tamanoRegistros;
        crearFicheroClientes();
    }

    public void crearFicheroClientes() throws FileNotFoundException {
            this.ficheroClientes = new RandomAccessFile(nombreFichero, permisos);
    }
    /**
     * Escribe un cliente completo en el random access file.
     * Precondiciones: Que el cliente recibido no sea nulo
     *
     * @param registro
     * @throws IOException
     */
    public void escribirRegistro(@NotNull Cliente registro) throws IOException {
        try {
            ficheroClientes.seek(ficheroClientes.length());
            escribirCampo(registro.getNombre());
            escribirCampo(registro.getApellidos());
            escribirCampo(registro.getDni());
            escribirCampo(registro.getTelefono());
            escribirCampo(registro.getDireccion());
            actualizarPosicionPuntero();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>Precondiciones:</b> ninguna<br/>
     * <b>Postcondiciones:</b> Escribe dicho campo en el fichero
     * @param campo texto a escribir
     * @throws IOException ha ocurrido algún error con el flujo de datos
     */
    public void escribirCampo(String campo) throws IOException {
        if(campo!= null){
            ficheroClientes.write(campo.getBytes(Charset.defaultCharset()));
        }else{
            throw new NullPointerException();
        }
    }

    /**
     * <b>Precondiciones:</b> ninguna<br/>
     * <b>Postcondiciones:</b> Una vez se ha escrito un nuevo registro actualiza la posicion del puntero.
     * @throws IOException ha ocurrido algún error con el flujo de datos
     */
    private void actualizarPosicionPuntero() throws IOException {
        posicionPuntero = Integer.valueOf(((int)ficheroClientes.getFilePointer() / tamanoRegistros)-1) ;
    }

    /**
     * Este metodo recibirá una lista con todos los clientes que no han sido eliminados y la escribirá en el fichero.
     * vacio
     * @param lista
     */
    public void actualizarFicheroNoEliminados(@NotNull ArrayList<Cliente> lista) throws IOException {
        vaciarFichero();
        for (Cliente c:lista) {
            escribirRegistro(c);
        }
    }

    public void vaciarFichero(){
        try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nombreFichero)));
        bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>Precondiciones:</b> Que exista un cliente en dicha posicion <br/>
     * <b>Postcondiciones:</b> Recibe una posición y lee el cliente que se encuentre en ella
     * @throws IOException ha ocurrido algún error con el flujo de datos
     */
    public void leerRegistro(int pos) throws IOException {
        int posicion = pos * tamanoRegistros;
        if(ficheroClientes.length()!=0){
            ficheroClientes.seek(posicion);
            System.out.println(String.format("Nombre: %s", leerCampo("Nombre")));
            System.out.println(String.format("Apellido: %s", leerCampo("Apellido")));
            System.out.println(String.format("Dni: %s", leerCampo("Dni")));
            System.out.println(String.format("Telefono: %s", leerCampo("Telefono")));
            System.out.println(String.format("Direccion: %s", leerCampo("Direccion")));
        }else{
            System.out.println("El fichero está vacío");
        }
    }

    /**
     * <b>Precondiciones:</b> Que exista un cliente en dicha posición<br/>
     * <b>Postcondiciones:</b> Devuelve los datos de dicho registro en un objeto cliente.
     * @throws IOException ha ocurrido algún error con el flujo de datos
     */
    public Cliente getDatosCliente(int pos) throws IOException{
        ficheroClientes.seek(pos*tamanoRegistros);
        Cliente cliente = new Cliente(leerCampo("Nombre"), leerCampo("Apellido"),
                leerCampo("Dni"), leerCampo("Telefono"), leerCampo("Direccion"));
        return cliente;
    }

    /**
     * <b>Precondiciones:</b> Que el puntero esté situado en el lugar correcto (inicio registro)<br/>
     * <b>Postcondiciones:</b> El método devuelve un string con el contenido del campo
     * @throws IOException ha ocurrido algún error con el flujo de datos
     */
    public String leerCampo(String campoALeer) throws IOException {
        byte[] b = new byte[Constantes.getTamanoCampoBytes(campoALeer)];
        ficheroClientes.read(b);
        return new String(b, Charset.defaultCharset());
    }


    /**
     * Para eliminar un registro se copiará el contenido del fichero excepto el registro a borrar a una lista de personas. tras ello
     * el fichero de datos se eliminará y se volverá a crear con el contenido de la lista
     */
    public boolean eliminarRegistro(int pos) throws IOException {
        long nRegistros = getNumeroRegistros();
        ArrayList <Cliente> regNoEliminados = new ArrayList<>();
        boolean borrado=false;
        for (int i = 0; i < nRegistros; i++) {
            if(i!=pos){
                regNoEliminados.add(getDatosCliente(i));
            }
        }
        if(regNoEliminados.size()==nRegistros-1){
            actualizarFicheroNoEliminados(regNoEliminados);
            borrado=true;
        }
        return borrado;
    }

    public ArrayList<Cliente> getListaClientes () throws IOException {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        for (int i = 0; i < getNumeroRegistros(); i++) {
                listaClientes.add(getDatosCliente(i));
        }
        return listaClientes;
    }

    public long getNumeroRegistros() {
        long nRegistros = 0;
        try{
            nRegistros=ficheroClientes.length()/tamanoRegistros;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nRegistros;
    }



    /**
     * Elimina el fichero con el nombre pasado por prámetro si existe.
     *
     * @throws FileNotFoundException
     */
    public void eliminarFicheroClientesSiExiste() throws FileNotFoundException {
        File file = new File(nombreFichero);
        if (file.exists()) {
            file.delete();
        }
    }

    public void close() throws IOException {
        if (ficheroClientes != null) {
            ficheroClientes.close();
        }
    }


}

