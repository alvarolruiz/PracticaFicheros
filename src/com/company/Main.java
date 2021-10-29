package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Este programa sirve para introducir clientes en un fichero binario, consultarlos, borrarlos y exportar clientes en
 * el formato de codificación deseado.
 * Se podran acceder a ellos mediante otro fichero en el que se almacenan los
 * dni de los clientes (fichero auxiliar de indice), y al que estarán relacionados los clientes almacenados.
 *
 * @author  Álvaro Lauriño
 * @version 1.0
 * @since   2021-10-21
 */

public class Main {

public static Scanner tecla = new Scanner(System.in);
public static Gestora gestora = new Gestora();

    public static void main(String[] args) {
        Gestora.accionMenu();
        try {
            FicheroAccesoAleatorio clientes= new FicheroAccesoAleatorio(NOMBRE_FICHERO, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // write your code here

    }



}
