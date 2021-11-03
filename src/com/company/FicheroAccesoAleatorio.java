package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class FicheroAccesoAleatorio {
    private static final int LONGITUD_NOMBRE=25*2; //String
    private static final int LONGITUD_APELLIDO=25*2; //String
    private static final int LONGITUD_DNI=9*2; //String
    private static final int LONGITUD_TELEFONO=9*4; //int
    private static final int LONGITUD_DIRECCION=30*2; //String
    private static final int NUMERO_CAMPOS=5; //String
    private static final int LONGITUD_TOTAL_CLIENTES=LONGITUD_NOMBRE+ LONGITUD_APELLIDO+LONGITUD_DNI+LONGITUD_TELEFONO+LONGITUD_DIRECCION;
    private static final int LONGITUD_TOTAL_INDICE=LONGITUD_DNI+4;
    public final RandomAccessFile  ficheroClientes;
    public final RandomAccessFile  ficheroIndice;
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

    public void escribirFichero (String nombre, String apellidos, String dni, int telefono, String direccion) throws IOException {
        byte[] array;
        StringBuffer sb;
        sb = new StringBuffer(LONGITUD_NOMBRE);
        sb.append(nombre.getBytes(StandardCharsets.UTF_8));
        ficheroClientes.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        // FIchero indice??
        sb.append(apellidos.getBytes(StandardCharsets.UTF_8));
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
