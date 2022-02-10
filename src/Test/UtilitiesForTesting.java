package Test;

import Entities.Cliente;
import Utilities.Constantes;
import Utilities.Format;

public class UtilitiesForTesting {

   public static Cliente getClienteValido(){
        String nombre = Format.formatearString("Persona", Constantes.LONGITUD_NOMBRE);
        String apellido = Format.formatearString("Martinez lopez", Constantes.LONGITUD_APELLIDO);
        String dni = Format.formatearString("77866502W", Constantes.LONGITUD_DNI);
        String telefono = Format.formatearString("666777555", Constantes.LONGITUD_TELEFONO);
        String direccion = Format.formatearString("Calle la calle", Constantes.LONGITUD_DIRECCION);
        return new Cliente(nombre,apellido,dni,telefono,direccion);
    }
}
