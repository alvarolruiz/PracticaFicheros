package Utilities;

import java.util.InputMismatchException;

import static com.company.Main.tecla;

public class Validaciones {

    /**
     * Valida si la opción recibida se encuentra entre las posibles del menú
     *
     * @param op
     * @return boolean
     */
    public static boolean validarOpcionMenu(int op) throws InputMismatchException{
        //TODO no puede recibir como parametro un string;
        boolean b=false;
        if(op>=0&&op<=5){
            b=true;
        }else{
            System.out.println("Opción no valida");
        }
        return b;
    }

    /**
     * Comprueba que el nombre o apellidos recibidos por teclado son del tamaño predefinido o menos y que no son nulos.
     * Devuelve un nombre válido.
     *
     * @param opcion nombre o apellidos
     * @return String nombre/Apellido valido
     */
    public static String validarNombre_Apellidos(String opcion) {
        String name="";
        Boolean correcto = false;
        System.out.println(opcion+":");
        if(opcion.equals("Nombre")){
            name = tecla.nextLine();
        }
        do {
            name = tecla.nextLine();
            if (name.length() <= Constantes.LONGITUD_NOMBRE && !name.isEmpty()) {
                correcto = true;
            }else{
                System.out.println(opcion +" no válido. "+opcion+" no puede tener más de 25 caracteres ni estar vacío");
            }
        }while(!correcto);
        return Format.formatearString(name, Constantes.LONGITUD_NOMBRE);
    }

    /**
     * Método que comprueba que la letra de un dni recibido por teclado es correcta.
     * Devuelve un dni válido formateado para ser escrito en el fichero.
     * @return String
     */
     public static String validarDni() {
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
            if(caracteres[resto]==letra&&dni.length()== Constantes.LONGITUD_DNI){
                correcto = true;
            }else{
                System.out.println("Dni incorrecto");
            }
        }while(!correcto);
        return Format.formatearString(dni, Constantes.LONGITUD_DNI);
    }

    /**
     * Método que comprueba que el teléfono es del tamaño predefinido
     * Devuelve un telefono válido formateado para ser escrito en el fichero.
     * @return int
     */
    public static int validarTelefono() {
        int telefono=0;
        boolean correcto = false;
        System.out.println("Teléfono: ");
        try {
            do {
                telefono = tecla.nextInt();
                if (telefono != 0 && String.valueOf(telefono).length() == Constantes.LONGITUD_TELEFONO) {
                    correcto=true;
                } else {
                    System.out.println("Telefono incorrecto, escriba uno válido");
                }
            }while(!correcto);
        }catch (InputMismatchException e){
            e.printStackTrace();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return Format.formatearInt(telefono, Constantes.LONGITUD_TELEFONO);
    }

    /**
     * Metodo que comprueba qu la direcion es del tamaño predefinido.
     * Devuelve una dirección válida formateada para ser escrita en el fichero
     * @return String
     */
    public static String validarDireccion() {
        String direccion;
        boolean correcto=false;
        System.out.println("Dirección");
        direccion=tecla.nextLine();
        do{
            direccion=tecla.nextLine();
            if (direccion.length() <= Constantes.LONGITUD_DIRECCION && !direccion.isEmpty()) {
                correcto=true;
            } else {
                System.out.println("La direccion no puede tener más de 30 caracteres ni estar vacío");
            }
        }while(!correcto);
        return Format.formatearString(direccion, Constantes.LONGITUD_DIRECCION);
    }
}
