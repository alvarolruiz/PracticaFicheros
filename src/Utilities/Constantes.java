package Utilities;

public class Constantes {
    public static final String NOMBRE_FICHERO_DATOS = "clientes.bin";
    public static final String NOMBRE_FICHERO_INDICE = "indice_clientes.bin";
    protected static final int LONGITUD_NOMBRE=25; // * 2 (char)
    protected static final int LONGITUD_APELLIDO=25; // * 2 (char)
    protected static final int LONGITUD_DNI=9; // * 2 (char)
    protected static final int LONGITUD_TELEFONO=9; // * 4 (int)
    protected static final int LONGITUD_DIRECCION=30; // * 2 (char)
    protected static final int TAMANO_REGISTROS=LONGITUD_NOMBRE*2+ LONGITUD_APELLIDO*2+4+LONGITUD_TELEFONO*4+LONGITUD_DIRECCION*2;

    public static int getTamanoRegistros(){
        return TAMANO_REGISTROS;
    }



}
