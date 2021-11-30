package FileManager;

import Entities.Cliente;
import Utilities.Constantes;
import Utilities.Validaciones;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static Utilities.Constantes.*;
import static java.nio.charset.StandardCharsets.UTF_16;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FicheroAccesoAleatorioDatos {

    public RandomAccessFile ficheroClientes;
    public File archivo;
    public int tamanoRegistros;
    public String nombreFichero;

    public FicheroAccesoAleatorioDatos(String nombre, String permisos, int tamanoRegistros) throws FileNotFoundException {
        nombreFichero = nombre;
        this.ficheroClientes = new RandomAccessFile(nombreFichero, permisos);
        this.tamanoRegistros = tamanoRegistros;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirCampo(String campo) throws IOException {
    ficheroClientes.write(campo.getBytes(Charset.defaultCharset()));
    }

    /**
     * Recibe una posición y lee el cliente que se encuentre en ella
     * Precondiciones: Que el fichero contenga algún registro en esa posición
     *
     * @param pos
     * @throws IOException
     */
    public void leerRegistro(int pos) throws IOException {
        int posicion=pos * tamanoRegistros;
        ficheroClientes.seek(posicion);
        System.out.println(String.format("Nombre: %s",leerCampo("Nombre")));
        System.out.println(String.format("Apellido: %s",leerCampo("Apellido")));
        System.out.println(String.format("Dni: %s",leerCampo("Dni")));
        System.out.println(String.format("Telefono: %s",leerCampo("Telefono")));
        System.out.println(String.format("Direccion: %s",leerCampo("Direccion")));
    }

    public String leerCampo(String campoALeer) throws IOException {
        byte[]b=new byte[Constantes.getTamanoCampoBytes(campoALeer)];
        ficheroClientes.read(b);
        return new String(b, Charset.defaultCharset());
    }



    /**
     * Elimina el fichero con el nombre pasado por prámetro si existe.
     *
     * @param nombre
     * @throws FileNotFoundException
     */
    public void eliminarFicheroSiExiste(String nombre) throws FileNotFoundException {
        File file = new File(nombre);
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

