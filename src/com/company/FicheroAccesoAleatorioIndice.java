package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FicheroAccesoAleatorioIndice {
    private static final int LONGITUD_DNI=9*2; //String
    public RandomAccessFile ficheroIndice;

    public FicheroAccesoAleatorioIndice(String nombre, String permisos) throws FileNotFoundException {
        this.ficheroIndice=new RandomAccessFile(new File(nombre), permisos);
        //this.tamanoRegistros = LONGITUD_TOTAL_INDICE;
    }

    private static final int LONGITUD_TOTAL_INDICE=LONGITUD_DNI+4;

}
