package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FicheroAccesoAleatorio implements Serializable {
    private static final int LONGITUD_NOMBRE=25*2; //String
    private static final int LONGITUD_APELLIDO=25*2; //String
    private static final int LONGITUD_DNI=9*2; //String
    private static final int LONGITUD_TELEFONO=9*4; //int
    private static final int LONGITUD_DIRECCION=30*2; //String
    private static final int NUMERO_CAMPOS=5 //String

    private static final int LONGITUD_TOTAL_CLIENTES=LONGITUD_NOMBRE+ LONGITUD_APELLIDO+LONGITUD_DNI+LONGITUD_TELEFONO+LONGITUD_DIRECCION;
    private static final int LONGITUD_TOTAL_INDICE=LONGITUD_DNI+4;
    public RandomAccessFile  ficheroClientes;
    public RandomAccessFile  ficheroIndice;
    public int tamanoRegistros;
    public int numeroRegistros;

    public FicheroAccesoAleatorio(String nombre, String permisos, String tipo) throws FileNotFoundException {
        this.ficheroClientes = new RandomAccessFile(new File(nombre),permisos);
        if(tipo=="DatosClientes"){
            this.tamanoRegistros = LONGITUD_TOTAL_CLIENTES;
        }else if (tipo == "IndiceClientes"){
            this.tamanoRegistros = LONGITUD_TOTAL_INDICE;
        }
        this.numeroRegistros=0;
    }

    public void escribirFichero (Cliente registro){
        try {
            int tamano=0;
            String escribir="";
            for (int i = 0; i < NUMERO_CAMPOS; i++) {
                switch (i){
                    case 0: tamano=LONGITUD_NOMBRE;
                            escribir= registro.getNombre();
                    case 1: tamano=LONGITUD_APELLIDO;
                            escribir=registro.getApellidos();
                    case 2: tamano=LONGITUD_DNI;
                            escribir=registro.getDni();
                    case 3: tamano=LONGITUD_TELEFONO;
                            escribir=registro.getTelefono();
                    case 4: tamano=LONGITUD_DIRECCION;
                            escribir=registro.getDireccion();
                }
                for (int j = 0; j < tamano; j++) {
                    ficheroClientes.write(escribir.getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeByte (String campo, int tamano){
        StringBuffer sb = new StringBuffer(tamano);
        byte[]bytes = campo.getBytes(StandardCharsets.UTF_8);
        if(bytes!=null){
            sb.append(bytes);
        }
        // Investigar sobre interfaz Serializable
        ficheroClientes.write(sb.getChars());
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
