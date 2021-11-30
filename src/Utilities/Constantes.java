package Utilities;

public class Constantes {
    public static final String NOMBRE_FICHERO_DATOS = "clientes.bin";
    public static final String NOMBRE_FICHERO_INDICE = "indice_clientes.bin";
    public static final int LONGITUD_NOMBRE=25;
    public static final int LONGITUD_APELLIDO=25;
    public static final int LONGITUD_DNI=9;
    public static final int LONGITUD_TELEFONO=9;
    public static final int LONGITUD_DIRECCION=30;
    public static final int TAMANO_REGISTROS=LONGITUD_NOMBRE+ LONGITUD_APELLIDO+LONGITUD_TELEFONO+LONGITUD_TELEFONO+LONGITUD_DIRECCION;

    public static int getTamanoRegistros(){
        return TAMANO_REGISTROS;
    }
/*public static int getLongitudNombreBytes(){ return LONGITUD_NOMBRE*2;}
public static int getLongitudApellidoBytes(){ return LONGITUD_APELLIDO*2;}
public static int getLongitudDniBytes(){ return LONGITUD_DNI*2;}
public static int getLongitudTelefonoBytes(){ return LONGITUD_TELEFONO*2;}
public static int getLongitudDireccionBytes(){ return LONGITUD_DIRECCION*2;}*/

    public static int getTamanoCampoBytes(String campo){
        int tamañoCampo=0;
        switch (campo){
            case("Nombre"): tamañoCampo= LONGITUD_NOMBRE;
                break;
            case("Apellido"): tamañoCampo=LONGITUD_APELLIDO;
                break;
            case("Dni"): tamañoCampo=LONGITUD_DNI;
                break;
            case("Telefono"):tamañoCampo=LONGITUD_TELEFONO;
                break;
            case("Direccion"):tamañoCampo=LONGITUD_DIRECCION;
                break;
        }
        return tamañoCampo;
    }



}
