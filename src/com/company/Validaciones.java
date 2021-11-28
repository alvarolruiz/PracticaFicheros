package com.company;

import static com.company.Main.tecla;

public class Validaciones {
    private static final int LONGITUD_NOMBRE=25; //String 25*2
    private static final int LONGITUD_APELLIDO=25; //String 25*2
    private static final int LONGITUD_DNI=9; //String 9*2
    private static final int LONGITUD_TELEFONO=9; //int 9*4
    private static final int LONGITUD_DIRECCION=30; //String 30*2
    private static final int TAMAÑO_REGISTROS =LONGITUD_NOMBRE*2+ LONGITUD_APELLIDO*2+LONGITUD_DNI*2+LONGITUD_TELEFONO*4+LONGITUD_DIRECCION*2;


    public static int getTamañoRegistros(){
        return  TAMAÑO_REGISTROS;
    }
    /**Valida si la opción recibida se encuentra entre las posibles del menú
     * @param op
     * @return boolean
     */
    public static boolean validarOpcionMenu(int op) {
        //TODO no puede recibir como parametro un string;
        boolean b=false;
        if(op>=0&&op<=5){
            b=true;
        }else{
            System.out.println("Opción no valida");
        }
        return b;
    }

    /** Comprueba que el nombre o apellidos son de tamaño 25 o menos y que no son nulos
     * @param opcion nombre o apellidos
     * @return string nombre/Apellido valido
     */
    public static String validarNombre_Apellidos(String opcion) {
        String name="";
        Boolean correcto = false;
        System.out.println(opcion+":");
        if (opcion.equals("Nombre")) {
            name = tecla.nextLine();
        }
        do {
            name = tecla.nextLine();
            if (name.length() <= LONGITUD_NOMBRE && !name.isEmpty()) {
                correcto = true;
            }else{
                System.out.println(opcion +" no válido. "+opcion+" no puede tener más de 25 caracteres ni estar vacío");
            }
        }while(!correcto);
        return Utilidades.formatearString(name,LONGITUD_NOMBRE);
    }

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
            if(caracteres[resto]==letra&&dni.length()==LONGITUD_DNI){
                correcto = true;
            }else{
                System.out.println("Dni incorrecto");
            }
        }while(!correcto);
        return Utilidades.formatearString(dni, LONGITUD_DNI);
    }

    public static int validarTelefono() {
        int telefono;
        boolean correcto = false;
        System.out.println("Teléfono: ");
        do {
            telefono = tecla.nextInt();
            if (telefono != 0 && String.valueOf(telefono).length() == LONGITUD_TELEFONO) {
                correcto=true;
            } else {
                System.out.println("Telefono incorrecto, escriba uno válido");
            }
        }while(!correcto);
        return Utilidades.formatearInt(telefono,LONGITUD_TELEFONO);
    }

    public static String validarDireccion() {
        String direccion;
        boolean correcto=false;
        System.out.println("Dirección");
        direccion=tecla.nextLine();
        do{
            direccion=tecla.nextLine();
            if (direccion.length() <= LONGITUD_DIRECCION && !direccion.isEmpty()) {
                correcto=true;
            } else {
                System.out.println("La direccion no puede tener más de 30 caracteres ni estar vacío");
            }
        }while(!correcto);
        return Utilidades.formatearString(direccion, LONGITUD_DIRECCION);
    }
}
