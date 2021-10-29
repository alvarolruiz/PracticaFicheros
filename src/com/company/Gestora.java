package com.company;

import java.io.RandomAccessFile;
import java.util.ArrayList;

import static com.company.Main.tecla;

public class Gestora {

    public static int NUMERO_REGISTROS = 0;
    public static int POSICION;
    public static FicheroAccesoAleatorio;

    public ArrayList <Cliente> clientes = new ArrayList<>();

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
        String nombre;
        nombre = validarNombre_Apellidos("Nombre");
        String apellidos;
        apellidos= validarNombre_Apellidos("Apellidos");
        String dni;
        dni = validarDni();
        int telefono;
        telefono = validarTelefono();
        String direccion;
        direccion= validarDireccion();
    }

    private void escribirFichero(Cliente cliente) {
        StringBuffer sb = new StringBuffer(cliente.getNombre());
        sb.setLength(25);
        cliente.getNombre();
    }




    /** Comprueba que se cumplen las condiciones dadas para el tamaño de nombre o apellidos
     * @param opcion nombre o apellidos
     * @return
     */
    private static String validarNombre_Apellidos(String opcion) {
        String name="";
        Boolean correcto = false;
        System.out.println(opcion+":");
        if (opcion.equals("Nombre")) {
            name = tecla.nextLine();
        }
        do {
            name = tecla.nextLine();
            if (name.length() <= 25 && !name.isEmpty()) {
                correcto = true;
            }else{
                System.out.println(opcion +" no válido. "+opcion+" no puede tener más de 25 caracteres ni estar vacío");
            }
        }while(!correcto);
        return name;
    }

    private static String validarDni() {
        char caracteres[] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        String dni="";
        int numero=0;
        char letra=' ';
        int resto=0;
        boolean correcto = false;
        System.out.println("Dni:");
        do{
            dni = tecla.nextLine();
            try {
                numero = Integer.parseInt(dni.substring(0, 8));
                letra = Character.toUpperCase(dni.charAt(8));
                resto = numero % 23;
            }catch (NumberFormatException e){
                System.out.println("Dni no puede estar formado solo de caracteres");
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("Tamaño del dni no puede ser inferior a 9");
            }
            if(caracteres[resto]==letra&&dni.length()==9){
                correcto = true;
            }else{
                System.out.println("Dni incorrecto");
            }
        }while(!correcto);
        return dni;
    }

    /** Este método devuelve un telefono recibido por teclado con su validación correspondiente
     * @return int --> Telefono validado.
     */
    private static int validarTelefono() {
        int telefono;
        boolean correcto = false;
        System.out.println("Teléfono: ");
        do {
            telefono = tecla.nextInt();
            if (telefono != 0 && String.valueOf(telefono).length() == 9) {
                correcto=true;
            } else {
                System.out.println("Telefono incorrecto, escriba uno válido");
            }
        }while(!correcto);
        return telefono;
    }

    /** Este método devuelve una dirección recibida por teclado con su validación correspondiente
     * @return String direccion --> Direccion validada.
     */

    private static String validarDireccion() {
        String direccion;
        boolean correcto=false;
        System.out.println("Dirección");
        direccion=tecla.nextLine();
        do{
            direccion=tecla.nextLine();
            if (direccion.length() <= 30 && !direccion.isEmpty()) {
                correcto=true;
            } else {
                System.out.println("La direccion no puede tener más de 30 caracteres ni estar vacío");
            }
        }while(!correcto);
        return direccion;
    }

    private static void exportarClientes() {

    }

    private static void configuracionDeExportacion() {
    }

    private static void borrarCliente () {
        }

    private static void consultarCliente () {

    }



    }
