package com.company;

import Entities.Cliente;
import FileManager.FicheroAccesoAleatorioDatos;
import FileManager.FicheroAccesoAleatorioIndice;
import Utilities.Constantes;
import Utilities.Validaciones;
import View.Menu;
import java.io.FileNotFoundException;
import java.io.IOException;
import static com.company.Main.tecla;

public class Gestora {

    public FicheroAccesoAleatorioDatos clientes;
    public FicheroAccesoAleatorioIndice indice;

    public Gestora()  {
        try {
            clientes = new FicheroAccesoAleatorioDatos(Constantes.NOMBRE_FICHERO_DATOS,
                    "rw", Constantes.getTamanoRegistros());
            indice = new FicheroAccesoAleatorioIndice(Constantes.NOMBRE_FICHERO_INDICE);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Muestra al usuario el menú y llama a sus funciones.
     * Hasta que el usuario no ponga la opción 0 se seguirá
     * mostrando el menú
     */

    public  void accionMenu() {
        boolean fin = false;
        int op;

        while (fin != true) {
            op = Menu.imprimirMenuPrincipal();
            switch (op) {
                case (1):
                    anadirCliente();
                    break;
                case (2):
                    consultarCliente();
                    break;
                case (3):
                    borrarCliente();
                    break;
                case (4):
                    configuracionDeExportacion();
                    break;
                case (5):
                    exportarClientes();
                    break;
                case (0):
                    fin = true;
                    break;

            }
        }
    }

    /**
     * Pide por teclado todos los campos de un cliente
     * y lo escribe en el fichero
     */

    private void anadirCliente () {
        System.out.println("Introduce los siguientes datos para añadir un cliente nuevo.");
        String nombre = Validaciones.validarNombre_Apellidos("Nombre");
        String apellidos = Validaciones.validarNombre_Apellidos("Apellidos");
        String dni = Validaciones.validarDni();
        int telefono = Validaciones.validarTelefono();
        String direccion = Validaciones.validarDireccion();
        Cliente cliente = new Cliente(nombre, apellidos, dni, String.valueOf(telefono), direccion);
        try {
            clientes.escribirRegistro(cliente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void consultarCliente () {
        String dni ="";
        System.out.println("Introduce la pos del cliente a consultar");
        int posicion = tecla.nextInt();
        try {
            clientes.leerRegistro(posicion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void exportarClientes() {

    }

    private static void configuracionDeExportacion() {
    }

    private static void borrarCliente () {
        }





    }
