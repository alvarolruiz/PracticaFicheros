package com.company;

import java.io.*;

public class FicheroAccesoAleatorioDatos {

    private static byte []array=null;
    private static ByteArrayOutputStream escribir=null;
    private static ObjectOutputStream salida=null;
    private static ByteArrayInputStream leer=null;
    private static ObjectInputStream entrada = null;
    public String nombreFichero;
    public String permisosFichero;

    public RandomAccessFile  ficheroClientes;
    public int tamanoRegistros;


    public FicheroAccesoAleatorioDatos(String nombre, String permisos, int tamanoRegistros) throws FileNotFoundException {
        this.nombreFichero=nombre;
        this.permisosFichero =permisos;
            this.ficheroClientes = new RandomAccessFile(new File(nombreFichero),permisosFichero);
            this.tamanoRegistros = tamanoRegistros;

    }

    public void escribirRegistro( Cliente registro) {
        try {
            ficheroClientes= new RandomAccessFile(new File(nombreFichero), permisosFichero);
            ficheroClientes.seek(ficheroClientes.length());
            escribir = new ByteArrayOutputStream();
            salida = new ObjectOutputStream(escribir);
            salida.writeObject(registro.toString());
            salida.close();
            array = escribir.toByteArray();
            ficheroClientes.write(array);
            ficheroClientes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerRegistro(int pos) throws IOException {
        int posicion = pos*tamanoRegistros;
        /*ficheroClientes= new RandomAccessFile(new File(nombreFichero), permisosFichero);
        ficheroClientes.seek(posicion);
        entrada = new ObjectInputStream(new FileInputStream(nombreFichero));
        try {
            leer = new ByteArrayInputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
        ficheroClientes.read(leer.readAllBytes(),posicion,posicion+posicion/pos)
        System.out.println(ficheroClientes.readLine());
        ficheroClientes.close();*/
        try {
            /* Creamos o abrimos un nuevo archivo. Este archivo lo crearemos
            dentro de la carpeta src de nuestro proyecto. Además debemos tener
            en cuenta que el constructor de la clase RandomAccessFile recibe
            2 parámetros:
            El primero hace referencia a la ruta del archivo.
            El segundo hace referencia al modo de apertura del archivo:
            - r - read. Solo lectura.
            - rw - read/wirte. Lectura y escritura */
            ficheroClientes= new RandomAccessFile(nombreFichero, permisosFichero);
            ficheroClientes.seek(posicion);
            array = new byte[(int)tamanoRegistros];
            ficheroClientes.readFully(array);
            leer = new ByteArrayInputStream(array);
            entrada = new ObjectInputStream(leer);

            /* Hacemos una conversion de lo que lee el ObjectInputStream
            a un objeto de tipo Persona y lo almacenamos
            en la variable objeto nuevaPersona*/
            Cliente clienteLeido;
            clienteLeido =(Cliente) entrada.readObject();
            System.out.println(clienteLeido.toString());
            // Cerramos el objeto ObjectInputStream
            entrada.close();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void escribirFicheroIndice(String campo, int tamano) {
        StringBuffer sb = new StringBuffer();
        sb.append(obtenerBytes(campo));
        sb.setLength(tamano);
        //this.ficheroIndice.write(tamano);
    }

    public static byte[] obtenerBytes(String campo){
        byte[] dato = campo.getBytes();
        return dato;
    }

    public static void eliminarFicheroSiExiste (String nombre) throws FileNotFoundException{
            File file = new File(nombre);
            if (file.exists()) {
                file.delete();
            }
    }

    public void close() throws IOException{
        if(ficheroClientes!=null){
            ficheroClientes.close();
        }
    }



}

