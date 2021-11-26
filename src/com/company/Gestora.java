package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.company.Main.tecla;
import static com.company.Validaciones.validarTelefono;

public class Gestora {

    public static final String NOMBRE_FICHERO_DATOS = "clientes.bin";
    public static final String NOMBRE_FICHERO_INDICE = "indice_clientes.bin";
    public static int POSICION;
    public static FicheroAccesoAleatorioDatos clientes;
    public static FicheroAccesoAleatorioIndice indice;

    static {
        try {
            clientes = new FicheroAccesoAleatorioDatos(NOMBRE_FICHERO_DATOS, "rw",Utilidades.getTamanoRegistros());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        /*try {
            indice= new FicheroAccesoAleatorioDatos(NOMBRE_FICHERO_INDICE,"rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
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
                    fin = true;
                    break;

            }
        }
    }

    private static void anadirCliente () {
        System.out.println("Introduce los siguientes datos para añadir un cliente nuevo.");
        String nombre = Validaciones.validarNombre_Apellidos("Nombre");
        String apellidos;
        apellidos= Validaciones.validarNombre_Apellidos("Apellidos");
        String dni;
        dni = Validaciones.validarDni();
        int telefono;
        telefono = Validaciones.validarTelefono();
        String direccion;
        direccion= Validaciones.validarDireccion();
        Cliente cliente= new Cliente(nombre,apellidos, dni,String.valueOf(telefono),direccion);
        clientes.escribirRegistro(cliente);
        POSICION++;
    }

    private static void consultarCliente () {
        int pos = 0;
        System.out.println("Introduce la posicion del cliente a consultar");
        pos = tecla.nextInt();
        try {
            clientes.leerRegistro(pos);
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
