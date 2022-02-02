package com.company;

import Entities.Cliente;

import java.util.Scanner;

import FileManager.FicheroAccesoAleatorioDatos;
import FileManager.FicheroAccesoAleatorioIndice;
import Utilities.Constantes;
import Utilities.Validaciones;
import View.Menu;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.company.Main.tecla;

public class Gestora {

    public FicheroAccesoAleatorioDatos clientes;
    public FicheroAccesoAleatorioIndice indice;

    public Gestora() {
        try {
            clientes = new FicheroAccesoAleatorioDatos(Constantes.NOMBRE_FICHERO_DATOS,
                    "rw", Constantes.getTamanoRegistros());
            indice = new FicheroAccesoAleatorioIndice(Constantes.NOMBRE_FICHERO_INDICE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra al usuario el menú y llama a sus funciones.
     * Hasta que el usuario no ponga la opción 0 se seguirá
     * mostrando el menú
     */
    public void accionMenu() {
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
                    System.out.println("Adios");
                    break;
            }
        }
    }

    /**
     * Pide por teclado todos los campos de un cliente y los escribe en el fichero de datos e indice
     * Precondiciones: Todas las propiedaddes introducidas deben ser válidas
     * Postcondiciones: Se escribe el cliente completo en el fichero clientes y el dni con la posición que ocupa
     * dicho cliente en el fichero indice
     */
    private void anadirCliente() {
        System.out.println("Introduce los siguientes datos para añadir un cliente nuevo.");
        String nombre = Validaciones.PedirYValidarNombre();
        String apellidos = Validaciones.PedirYValidarApellido();
        String dni = Validaciones.PedirYValidarDni();
        String telefono = Validaciones.PedirYValidarTelefono();
        String direccion = Validaciones.PedirYValidarDireccion();
        Cliente cliente = new Cliente(nombre, apellidos, dni, telefono, direccion);
        escribirEnFichero(cliente);
        escribirEnIndice(dni);
    }

    /**
     * Escribe en el fichero clientes un cliente pasaddo por parámetro
     *
     * @param cliente
     */
    private void escribirEnFichero(Cliente cliente) {
        try {
            clientes.escribirRegistro(cliente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escribirEnIndice(String dni) {
        indice.addIndex(dni, clientes.getPosicionPuntero());
        indice.guardarIndiceEnFichero();

    }

    /**
     * Método que pide por teclado un dni y muestra el cliente con dicho dni.
     */
    private void consultarCliente() {
        System.out.println("Introduce el dni del cliente a mostrar");
        tecla.skip("\n");
        String dni;
        dni = tecla.nextLine();
        try {
            int posicion = indice.getFromIndex(dni);
            clientes.leerRegistro(posicion);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    private static void exportarClientes() {

    }

    private static void configuracionDeExportacion() {
    }

    private void borrarCliente() {
        System.out.println("Introduce el dni del cliente que desea borrar");
        tecla.skip("\n");
        String dni;
        dni = tecla.nextLine();
        int posicion = indice.getFromIndex(dni);
        try{
            clientes.eliminarRegistro(posicion);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
