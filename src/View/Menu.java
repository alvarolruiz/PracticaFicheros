package View;

import Utilities.Validaciones;

import java.util.InputMismatchException;

import static com.company.Main.tecla;

public class Menu {

    /**
     * Imprime el menú y devuelve un entero con la opción escogida por el usuario
     *
     * @return int
     */
    public static int imprimirMenuPrincipal() {
        int opcion;
            do {
                System.out.println();
                System.out.println("Menú Principal:");
                System.out.println("------------------");
                System.out.println("1. Nuevo cliente.");
                System.out.println("2. Consultar cliente.");
                System.out.println("3. Borrar cliente.");
                System.out.println("4. Configuración de exportación.");
                System.out.println("5. Exportar clientes.");
                System.out.println("0. Salir.");
                System.out.println("------------------");
                System.out.println("Introduce una opción");
                System.out.println();
                opcion=tecla.nextInt();
            }while (!Validaciones.validarOpcionMenuPrincipal(opcion));
        return opcion;
    }

    public static int imprimirMenuExportacion(){
        int opcion;
        do{
            System.out.println();
            System.out.println("Codificación por defecto");
            System.out.println("------------------");
            System.out.println("1. US_ASCII");
            System.out.println("2. ISO_8859_1");
            System.out.println("3. UTF_16");
            System.out.println("4. UTF_16LE");
            System.out.println("5. UTF_16BE");
            System.out.println("6. UTF_8");
            System.out.println("0. Volver al menú principal");
            System.out.println("------------------");
            System.out.println();
            System.out.println("Introduce una opción");
            opcion = tecla.nextInt();
        }while (!Validaciones.validarOpcionMenuExportación(opcion));
        return opcion;
    }


    public static void askBackMenu() {
        System.out.println("Cuando desee volver al menú principal escriba volver");
    }
}


