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

//Todo Cuando introduces un registro que contenga ñ tood se desplaza una posicion a la derecha. Solucionar
    //Todo Borrar registro
    // Todo Exportar Fichero
    // Todo Pruebas Unitarias


public class Main {

public static Scanner tecla = new Scanner(System.in);

    public static void main(String[] args) {
    Gestora gestora = new Gestora();
    gestora.accionMenu();

    }



}
