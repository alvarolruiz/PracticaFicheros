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


}
