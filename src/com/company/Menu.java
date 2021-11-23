package com.company;

import java.io.RandomAccessFile;

import static com.company.Main.tecla;

public class Menu {

    /**Imprime el menú y devuelve un entero con la opción escogida por el usuario, la cual será
     * Validada previamente
     *
     * @return int
     */
    public static int imprimirMenuPrincipal() {
        int opcion;
            do {
                System.out.println("Menu Principal:");
                System.out.println("------------------");
                System.out.println("1. Nuevo cliente.");
                System.out.println("2. Consultar cliente.");
                System.out.println("3. Borrar cliente.");
                System.out.println("4. Configuración de exportación.");
                System.out.println("5. Exportar clientes.");
                System.out.println("0. Salir.");
                System.out.println("Introduce una opción");
                opcion = tecla.nextInt();
            }while (!Validaciones.validarOpcionMenu(opcion));
        return opcion;
    }



}


