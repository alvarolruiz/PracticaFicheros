package Utilities;

public class Format {

    /**
     * Método que formatea una cadena para que ocupe el espacio determinado por parámetros.
     * @param cadenaAFormatear
     * @param longCadena
     * @return string
     */
    public static String formatearString(String cadenaAFormatear, int longCadena){
        String formatoCadena = obtenerFormatoString(longCadena);
        return (cadenaAFormatear.length()<longCadena) ? String.format(formatoCadena, cadenaAFormatear) : cadenaAFormatear.substring(0,longCadena);
    }

    /**
     * Método que devuelve un formato para una cadena en función del parámetro longRegistro
     * @param longRegistro
     * @return String
     */
    public static String obtenerFormatoString(int longRegistro) {
        StringBuilder formato = new StringBuilder("%-");
        formato.append(longRegistro);
        formato.append('s');
        return formato.toString();
    }

    /**
     * Método que formatea un entero para que ocupe siempre el espacio determinado por parámetros
     * @param enteroAFormatear
     * @param longInt
     * @return
     */
    public static int formatearInt(int enteroAFormatear, int longInt){
        String formatoCadena = obtenerFormatoInt(longInt);
        return (String.valueOf(enteroAFormatear).length()<longInt) ? Integer.parseInt(String.format(formatoCadena, enteroAFormatear)) : Integer.parseInt(String.valueOf(enteroAFormatear).substring(0,longInt));
    }

    /**
     * Método que devuelve un formato para un entero en función del parámetro longRegistro
     * @param longRegistro
     * @return String
     */
    public static String obtenerFormatoInt(int longRegistro) {
        StringBuilder formato = new StringBuilder("%-");
        formato.append(longRegistro);
        formato.append('d');
        return formato.toString();
    }
}
