package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FicheroAccesoAleatorio {

    public RandomAccessFile  ficheroClientes;
    public int tamanoRegistros;
    public int numeroRegistros;

    public FicheroAccesoAleatorio(String nombre, String permisos) throws FileNotFoundException {
        this.ficheroClientes = new RandomAccessFile(new File(nombre),permisos);
        this.tamanoRegistros = calcularTamanoRegistros();
        this.numeroRegistros=0;
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


    public int calcularTamanoRegistros(){
        int total;
        int nombre=25;
        int apellido=25;
        int dni=9;
        int tlf=9;
        int direccion=30;
        total=nombre+apellido+dni+tlf+direccion;
        return total;
    }
}
