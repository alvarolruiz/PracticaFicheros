package FileManager;

import Entities.Cliente;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FicheroExportacionCliente {
    private String nombreFichero;
    private File fichero;

    public FicheroExportacionCliente(String nombreFichero){
        this.nombreFichero = nombreFichero;
        crearFichero();
    }

    /**
     * Inicializa el fichero
     */
    private void crearFichero() {
        fichero = new File(nombreFichero);
        if(!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <b>Precondiciones: </b>Ninguna<br/>
     * <b>Postcondiciones: </b>Se escribirán en el fichero todos los clientes recibidos en la lista con la codificacion
     * indicada <br/>
     * @param listaClientes
     * @param charsetName
     * @throws IOException
     */
    public void escribirClientesEnFichero(ArrayList<Cliente> listaClientes, Charset charsetName) throws IOException {
        fichero.delete();
        crearFichero();
        try(FileOutputStream fos = new FileOutputStream(this.fichero);
            OutputStreamWriter output = new OutputStreamWriter(fos, charsetName);
            BufferedWriter bufferedOutput = new BufferedWriter(output)) {
            for(Cliente c : listaClientes){
                bufferedOutput.write(c.toString());
                bufferedOutput.newLine();
            }
        }
    }
}
