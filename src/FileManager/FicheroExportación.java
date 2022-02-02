package FileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FicheroExportación {
    public BufferedWriter bw;
    public File fichExportado;
    public Charset charset;

    public FicheroExportación(String nombreFichero, Charset charset) throws IOException {
        this.charset = charset;
        fichExportado=new File(nombreFichero);
        if(!fichExportado.exists()){
            fichExportado.createNewFile();
        }
        this.bw = new BufferedWriter(new FileWriter(fichExportado,charset,true));
    }

    public void escribir() {
    }
}
