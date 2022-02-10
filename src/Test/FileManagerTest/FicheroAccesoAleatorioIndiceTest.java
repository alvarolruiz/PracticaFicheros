package Test.FileManagerTest;

import FileManager.FicheroIndice;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FicheroAccesoAleatorioIndiceTest {

    public static FicheroIndice ficheroIndice;
    public static final String RUTA_FICHERO_INDICE_PRUEBA = "src/Test/FilesTest/indice_clientes_prueba.bin";
    public static final String DNI_PRUEBA = "77866502W";
    @BeforeAll
    static void instanciarFicheroIndice(){
        try {
            ficheroIndice = new FicheroIndice(RUTA_FICHERO_INDICE_PRUEBA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void borrarFicheroPrueba(){
        if(ficheroIndice.file.exists()){
            ficheroIndice.file.delete();
        }
    }

    //Antes de cada método vaciamos el fichero de clientes de prueba
    @BeforeEach
    void vaciarFicheroClientesPrueba() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ficheroIndice.nombreFichero)));
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //NOTA: estos 2 métodos se necesitan para testear que funcionan correctamente, por eso los meto
    //en el mismo test
    @Test
    void TestCasoValidoAddIndex() {
        ficheroIndice.addIndex(DNI_PRUEBA,0);
        assertEquals(0, ficheroIndice.getFromIndex(DNI_PRUEBA));
    }

    //Al no encontrar un DNI, lanza una EOFException, el programa principal se encarga de gestionar esa excepción
    @Test
    void TestValidoGetFromIndex() {
        assertEquals(0,ficheroIndice.getFromIndex("22337778Y"));
    }

    //Agregará un DNI de un cliente, luego lo borrará y al buscarlo lanzará EOFException ya que no se encuentra,
    //NOTA: Este test es una ampliación del test anterior, ya que demuestra que se ha borrado lanzado EOFException al buscarlo
    @Test
    void TestCasoValidoGetAndDeleteFromIndex() {
        ficheroIndice.addIndex("77866502W",0);
        assertEquals(0,ficheroIndice.getAndDeleteFromIndex("77866502W"));

    }


}
