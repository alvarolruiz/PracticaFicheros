package com.company;

public class Utilidades {

    public static int getTamanoRegistros(){
      return  Validaciones.getTamañoRegistros();
    }

    public static String formatearString(String cadenaAFormatear, int longCadena){
        String formatoCadena = obtenerFormatoString(longCadena);
        return (cadenaAFormatear.length()<longCadena) ? String.format(formatoCadena, cadenaAFormatear) : cadenaAFormatear.substring(0,longCadena);
    }

    public static int formatearInt(int enteroAFormatear, int longInt){
        String formatoCadena = obtenerFormatoInt(longInt);
        return (String.valueOf(enteroAFormatear).length()<longInt) ? Integer.parseInt(String.format(formatoCadena, enteroAFormatear)) : Integer.parseInt(String.valueOf(enteroAFormatear).substring(0,longInt));
    }

    public static String obtenerFormatoString(int longRegistro) {
        StringBuilder formato = new StringBuilder("%-");
        formato.append(longRegistro);
        formato.append('s');
        return formato.toString();
    }

    public static String obtenerFormatoInt(int longRegistro) {
        StringBuilder formato = new StringBuilder("%-");
        formato.append(longRegistro);
        formato.append('d');
        return formato.toString();
    }
}
