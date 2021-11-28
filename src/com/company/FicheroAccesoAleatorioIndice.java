package com.company;

import java.io.*;
import java.util.HashMap;

public class FicheroAccesoAleatorioIndice {
    private static final int LONGITUD_DNI=9*2; //String
    private static FileWriter fw=null; //String
    private static ObjectOutputStream out = null;
    private static ObjectInputStream ois = null;
    public String nombreFichero;
    public File ficheroIndice;
    public HashMap<String, Integer> mapIndice= new HashMap<String,Integer>();
    public int posicion=0;

    public FicheroAccesoAleatorioIndice(String nombre) throws FileNotFoundException {
        this.nombreFichero = nombre;
        this.ficheroIndice= new File(nombre);
        this.getMapIndice();

        }
        //this.tamanoRegistros = LONGITUD_TOTAL_INDICE;


    public void añadirIndice (String dni) throws IOException {
        mapIndice.put(dni,posicion);
        posicion++;
    }

    public void escribirMapEnFile() {
        try {
            this.vaciarFichero();
            File file =  new File (nombreFichero);
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(mapIndice);
            out.flush();
            if(out!=null){
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getMapIndice(){
        if(!ficheroIndice.exists()) {
            try {
                ficheroIndice.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            try {
                ois = new ObjectInputStream(new FileInputStream(ficheroIndice));
                mapIndice = (HashMap<String, Integer>) ois.readObject();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

    }

    private void vaciarFichero(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nombreFichero)));
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPosicionRegistro(String dni){
        try {
            return this.mapIndice.get(dni);

        }catch ()
    }

}
