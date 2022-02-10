package FileManager;

import Utilities.Validaciones;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FicheroConfiguracionExportacion {
    private final Charset DEFAULT_CHARSET= StandardCharsets.UTF_8;
    private String nombreFichero;
    private File fichero;

    public FicheroConfiguracionExportacion(String nombreFichero){
        this.nombreFichero = nombreFichero;
        crearFichero();
        setFormatoConfiguracion(DEFAULT_CHARSET);
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
     * <b>Precondiciones: </b> El charset debe ser válido
     * <b>Poscondiciones: </b> El fichero se borrará y se volverá a crear con dicho charset
     * @param charsetName
     * @throws IOException
     */
    public boolean setFormatoConfiguracion(Charset charsetName)  {
        boolean correcto=false;
        if(Validaciones.validarCharset(charsetName)){
            try (FileOutputStream fos = new FileOutputStream(this.fichero);
                 DataOutputStream output = new DataOutputStream(fos)) {
                    output.writeBytes(charsetName.name());
                    correcto=true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return correcto;
    }

    public Charset getFormatoConfiguracion(){
        String charsetName = "";
        Charset charset = DEFAULT_CHARSET;
        try(FileInputStream fis = new FileInputStream(fichero);
        DataInputStream input = new DataInputStream(fis)){
        charsetName=new String(input.readAllBytes());
            charset =Charset.forName(charsetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charset;
    }


}
