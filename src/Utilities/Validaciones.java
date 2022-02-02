package Utilities;

import java.util.InputMismatchException;

import static com.company.Main.tecla;

public class Validaciones {

    public static boolean validarOpcionMenuExportación(int op){
        boolean b=false;
        if(op>=0&&op<=6){
            b=true;
        }else{
            System.out.println("Opción no valida");
        }
        return b;
    }

    /**
     * Valida si la opción recibida se encuentra entre las posibles del menú
     *
     * @param op
     * @return boolean
     */
    public static boolean validarOpcionMenuPrincipal(int op) throws InputMismatchException{
        boolean b=false;
        if(op>=0&&op<=5){
            b=true;
        }else{
            System.out.println("Opción no valida");
        }
        return b;
    }

    /**
     * Comprueba que el nombre recibido por teclado es del tamaño predefinido o menos y que no es nulo.
     * Devuelve un nombre válido formateado para ser escrito en el fichero
     *
     * @return String
     */
    public static String PedirYValidarNombre() {
        String nombre="";
        Boolean correcto = false;
        tecla.nextLine();
        do {
            System.out.println("Nombre:");
            if(nombre!=null) {
                nombre = tecla.nextLine();
            }
            if (nombre.length() <= Constantes.LONGITUD_NOMBRE && !nombre.isEmpty()) {
                correcto = true;
            }else{
                System.out.println(String.format("Nombre no válido, no puede tener más de %d caracteres ni estar vacío.",Constantes.LONGITUD_NOMBRE));
            }
        }while(!correcto);
        return Format.formatearString(nombre, Constantes.LONGITUD_NOMBRE);
    }

    /**
     * Comprueba que el apellido recibido por teclado es del tamaño predefinido o menos y que no es nulo.
     * Devuelve un apellido válido formateado para ser escrito en el fichero
     *
     * @return String
     */
    public static String PedirYValidarApellido(){
        String apellido="";
        Boolean correcto = false;
        do {
            System.out.println("Apellido:");
            apellido = tecla.nextLine();
            if (apellido.length() <= Constantes.LONGITUD_APELLIDO && !apellido.isEmpty()) {
                correcto = true;
            }else{
                System.out.println(String.format("Apellido no válido, no puede tener más de %d caracteres ni estar vacío.",Constantes.LONGITUD_APELLIDO));
            }
        }while(!correcto);
        return Format.formatearString(apellido, Constantes.LONGITUD_APELLIDO);

    }

    /**
     * Método que comprueba que la letra de un dni recibido por teclado es correcta.
     * Devuelve un dni válido formateado para ser escrito en el fichero.
     *
     * @return String
     */
     public static String PedirYValidarDni() {
        char caracteres[] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        String dni="";
        int numero;
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
                System.out.println(String.format("Tamaño del dni no puede ser inferior a %d",Constantes.LONGITUD_DNI));
            }
            if(caracteres[resto]==letra&&dni.length()== Constantes.LONGITUD_DNI){
                correcto = true;
            }else{
                System.out.println("Dni incorrecto");
            }
        }while(!correcto);
        return Format.formatearString(dni, Constantes.LONGITUD_DNI);
    }

    /**
     * Método que comprueba que el teléfono recibido por teclado es del tamaño predefinido.
     * Devuelve un teléfono válido formateado para ser escrito en el fichero.
     *
     * @return string
     */
    public static String PedirYValidarTelefono() {
        String telefono="";
        boolean correcto = false;
        System.out.println("Teléfono: ");
            do {
                telefono=tecla.nextLine();
                if (telefono.length() == Constantes.LONGITUD_TELEFONO) {
                    correcto=true;
                } else {
                    System.out.println(String.format("Telefono no válido. Ten en cuenta que tu telefono debe constar de %d números.",Constantes.LONGITUD_TELEFONO));
                }
            }while(!correcto);

        return Format.formatearString(telefono,Constantes.LONGITUD_TELEFONO);
    }

    /**
     * Método que comprueba que la dirección es del tamaño predefinido.
     * Devuelve una dirección válida formateada para ser escrita en el fichero
     *
     * @return String
     */
    public static String PedirYValidarDireccion() {
        String direccion="";
        boolean correcto=false;
        System.out.println("Dirección");
        do{
            direccion=tecla.nextLine();
            if (direccion.length() <= Constantes.LONGITUD_DIRECCION && !direccion.isEmpty()) {
                correcto=true;
            } else {
                System.out.println(String.format("Dirección no válida, no puede tener más de %d caracteres ni estar vacío.",Constantes.LONGITUD_DIRECCION));
            }
        }while(!correcto);
        return Format.formatearString(direccion, Constantes.LONGITUD_DIRECCION);
    }
}
