package com.company;

import Entities.Cliente;
import FileManager.FicheroAccesoAleatorioDatos;
import FileManager.FicheroAccesoAleatorioIndice;
import Utilities.Format;
import Utilities.Validaciones;
import View.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.company.Main.tecla;

public class Gestora {

    public static final String NOMBRE_FICHERO_DATOS = "clientes.bin";
    public static final String NOMBRE_FICHERO_INDICE = "indice_clientes.bin";
    public static int POSICION;
    public static FicheroAccesoAleatorioDatos clientes;
    public static FicheroAccesoAleatorioIndice indice;

    static {
        try {
            clientes = new FicheroAccesoAleatorioDatos(NOMBRE_FICHERO_DATOS, "rw", Format.getTamanoRegistros());
            indice = new FicheroAccesoAleatorioIndice(NOMBRE_FICHERO_INDICE);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void accionMenu() {
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
                    indice.escribirMapEnFile();
                    fin = true;
                    break;

            }
        }
    }

    private static void anadirCliente () {
        System.out.println("Introduce los siguientes datos para añadir un cliente nuevo.");
        String nombre = Validaciones.validarNombre_Apellidos("Nombre");
        String apellidos = Validaciones.validarNombre_Apellidos("Apellidos");
        String dni = Validaciones.validarDni();
        int telefono = Validaciones.validarTelefono();
        String direccion = Validaciones.validarDireccion();
        Cliente cliente = new Cliente(nombre, apellidos, dni, String.valueOf(telefono), direccion);
        try {
            clientes.escribirRegistro(cliente);
            indice.añadirIndice(cliente.getDni());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void consultarCliente () {
        String dni ="";
        System.out.println("Introduce el dni del cliente a consultar");
        int posicion = indice.getPosicionRegistro(tecla.nextLine());
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
