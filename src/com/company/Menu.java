package com.company;

import static com.company.Main.tecla;

public class Menu {

    public static boolean fin = false;

    public Menu() {
    }

    public static void menuPrincipal() {
            while (fin!=true) {
                System.out.println("Gestor de clientes:");
                System.out.println("------------------");
                System.out.println("1. Nuevo cliente");
                System.out.println("2. Consultar cliente.");
                System.out.println("3. Borrar cliente.");
                System.out.println("4. Configuración de exportación.");
                System.out.println("5. Exportar clientes.");
                System.out.println("0. Salir.");
                System.out.println("Introduce una opción");
                eleccionMenu();
            }
        }

        private static void eleccionMenu() {
            int eleccion;
            eleccion = tecla.nextInt();
            switch (eleccion){
                case(1): añadirCliente();
                    break;
                case(2): añadirCliente();
                    break;
                case(3): añadirCliente();
                    break;
                case(4): añadirCliente();
                    break;
                case(5): añadirCliente();
                    break;
                case(0): this.fin==true;
                    break;
            }
        }
    }

