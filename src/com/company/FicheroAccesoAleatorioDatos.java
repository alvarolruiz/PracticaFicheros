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
            salida.writeObject((Cliente)registro);
            salida.flush();
            salida.close();
            array = escribir.toByteArray();
            ficheroClientes.write(array);
            ficheroClientes.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerRegistro(int pos) throws IOException {
        int posicion = pos*tamanoRegistros;
        try {
            ficheroClientes= new RandomAccessFile(nombreFichero, permisosFichero);
            ficheroClientes.seek(posicion);
            array = new byte[(int)ficheroClientes.length()];
            int filepointer= (int) ficheroClientes.getFilePointer();
            ficheroClientes.readFully(array);
            leer = new ByteArrayInputStream(array);
            entrada = new ObjectInputStream(leer);
            Object clienteLeido;
            clienteLeido = entrada.readObject();
            System.out.println(clienteLeido);
            entrada.close();

        }catch (EOFException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void escribirFicheroIndice(String campo, int tamano) {


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

