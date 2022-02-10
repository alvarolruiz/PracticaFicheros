package Test.FileManagerTest;
import Entities.Cliente;
import FileManager.FicheroAccesoAleatorioDatos;
import Utilities.Constantes;
import Utilities.Format;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class FicheroAccesoAleatorioDatosTest {
    private static FicheroAccesoAleatorioDatos ficheroClientes;
    public static final String RUTA_FICHERO_CLIENTES_PRUEBA = "src/Test/FilesTest/clientes_prueba.bin";

    @BeforeAll
    static void instanciarFileAccess() {
        try {
            ficheroClientes = new FicheroAccesoAleatorioDatos(RUTA_FICHERO_CLIENTES_PRUEBA,"rw", Constantes.getTamanoRegistros());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void borrarFicheroPrueba(){
        try {
            ficheroClientes.eliminarFicheroClientesSiExiste();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void vaciarFicheroPrueba(){
        ficheroClientes.vaciarFichero();
    }

    @Disabled
    public Cliente getClienteValido1(){
        String nombre = Format.formatearString("Persona", Constantes.LONGITUD_NOMBRE);
        String apellido = Format.formatearString("Martinez lopez", Constantes.LONGITUD_APELLIDO);
        String dni = Format.formatearString("77866502W", Constantes.LONGITUD_DNI);
        String telefono = Format.formatearString("666777555", Constantes.LONGITUD_TELEFONO);
        String direccion = Format.formatearString("Calle la calle", Constantes.LONGITUD_DIRECCION);
        return new Cliente(nombre,apellido,dni,telefono,direccion);
    }

    @Disabled
    public Cliente getClienteValido2(){
        String nombre = Format.formatearString("Persona2", Constantes.LONGITUD_NOMBRE);
        String apellido = Format.formatearString("Martinez ", Constantes.LONGITUD_APELLIDO);
        String dni = Format.formatearString("77848439V", Constantes.LONGITUD_DNI);
        String telefono = Format.formatearString("666777555", Constantes.LONGITUD_TELEFONO);
        String direccion = Format.formatearString("Calle la calle", Constantes.LONGITUD_DIRECCION);
        return new Cliente(nombre,apellido,dni,telefono,direccion);
    }



    @Disabled
    public Cliente getClienteNoValido(){
        String nombre = null;
        String apellido = Format.formatearString("Martinez lopez", Constantes.LONGITUD_APELLIDO);
        String dni = Format.formatearString("77866502W", Constantes.LONGITUD_DNI);
        String telefono = Format.formatearString("666777555", Constantes.LONGITUD_TELEFONO);
        String direccion = Format.formatearString("Calle la calle", Constantes.LONGITUD_DIRECCION);
        return new Cliente(nombre,apellido,dni,telefono,direccion);
    }


    @Test
    void TestCasoValidoAgregarClienteFichero () {
        Cliente clienteValido1 = getClienteValido1();
        Cliente clienteValido2 = getClienteValido2();
        try {
            ficheroClientes.escribirRegistro(clienteValido1);
            ficheroClientes.escribirRegistro(clienteValido2);
            assertEquals(clienteValido1,ficheroClientes.getDatosCliente(0));
            assertEquals(clienteValido2,ficheroClientes.getDatosCliente(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void TestCasoValidoGetListaClientes(){
        ArrayList<Cliente> listaClientes= new ArrayList<>();
        Cliente clienteValido1 = getClienteValido1();
        Cliente clienteValido2 = getClienteValido2();
        listaClientes.add(clienteValido1);
        listaClientes.add(clienteValido2);
        try {
            ficheroClientes.escribirRegistro(clienteValido1);
            ficheroClientes.escribirRegistro(clienteValido2);
            assertEquals(listaClientes,ficheroClientes.getListaClientes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Ingresamos una lista de personas al fichero de clientes de prueba, le pedimos que nos devuelva toda la lista del fichero
    //excepto, el numero 2, simulando que es una persona borrada, devolviendo la lista de personas que no han sido borradas
    //Demostraremos que el método no funciona, ya que no ha devuelto la 2º persona, que se ha simulado que ha sido eliminada
    @Test
    public void TestCasoValidoEliminarRegistro() throws IOException {
        Cliente clienteValido1 = getClienteValido1();
        Cliente clienteValido2 = getClienteValido2();
        try {
            ficheroClientes.escribirRegistro(clienteValido1);
            ficheroClientes.escribirRegistro(clienteValido2);
        }catch (IOException e){
            e.printStackTrace();
        }
    assertEquals(true,ficheroClientes.eliminarRegistro(1));
    }

    @Test
    public void TestCasoNoValidoEliminarRegistro() throws IOException {
        assertNotEquals(true,ficheroClientes.eliminarRegistro(1));
    }

    //NOTA: El cliente es validado en el menú, de modo que esta situación no podrá darse nunca
    @Test
    void TestCasoNoValidoAgregarClienteFicheroPropiedadNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> ficheroClientes.escribirRegistro(getClienteNoValido()));
    }


}
