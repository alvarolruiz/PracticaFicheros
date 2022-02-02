package FileManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FicheroAccesoAleatorioIndice {
    public String nombreFichero;
    public HashMap<String, Integer> indice;
    public File file;

    public FicheroAccesoAleatorioIndice(String nombre) throws IOException {
        this.nombreFichero = nombre;
        cargarIndex();
    }

    /**
     * Método que inicializa el map que contendrá todos los registros de indice
     *
     * @throws IOException
     */
    public void cargarIndex() throws IOException {
        indice = new HashMap<>();
        cargarIndexDeFichero();
    }

    /**
     * Método que rellena el map con todos los entry contenidos en el fichero propiedades.
     * Precondiciones: Que el fichero de propiedades no esté vacío
     *
     * @throws IOException
     */

    public void cargarIndexDeFichero() throws IOException {
        crearFichero();
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        if (!properties.isEmpty()) {
            for (String key : properties.stringPropertyNames()) {
                indice.put(key, Integer.parseInt(String.valueOf(properties.get(key))));
            }
        }
    }

    /**
     * Método que crea el fichero en el que se escribirán los datos del map y del que se cargaran posteriormente.
     *
     * @throws IOException
     */
    private void crearFichero() throws IOException {
        file = new File(nombreFichero);
        if (!new File(nombreFichero).exists()) {
            file.createNewFile();
        }
    }

    /**
     * Método que guarda el map en un fichero de propiedades, de modo que se podrá recuperar después con
     * el método cargarIndexDeFichero()
     * Precondiciones: Que el map no sea nulo
     * Postcondiciones: Se escribirán todas las entrys del map en el fichero
     */
    public void guardarIndiceEnFichero()  {
        Properties properties = new Properties();
        for (Map.Entry<String, Integer> entry : indice.entrySet()) {
            properties.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        try {
            crearFichero();
            properties.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade un indice nuevo al map cargado en memoria
     *
     * @param dni
     * @param posicion
     */
    public void addIndex(String dni, Integer posicion) { indice.put(dni, posicion); }

    /**
     * Obtiene la posición que ocupa en el fichero principal de datos el registro con el dni pasado por parámetro.
     * No he conseguido que funcione. El map no es nulo, y de hecho contiene el dni con el que estoy probando
     *
     * @param dni
     * @return
     */
    public int getFromIndex(String dni) {
        int posicion = 0;
        if(indice.containsKey(dni)){
           posicion = indice.get(dni);
        }else{
            System.out.println("No existe un cliente con dicho dni, se mostrará el primer cliente guardado.");
        }
        return posicion;
    }


    //this.tamanoRegistros = LONGITUD_TOTAL_INDICE;


}
