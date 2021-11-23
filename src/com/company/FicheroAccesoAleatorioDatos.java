package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FicheroAccesoAleatorioDatos implements Serializable {
    private static final int LONGITUD_NOMBRE=25*2; //String
    private static final int LONGITUD_APELLIDO=25*2; //String
    private static final int LONGITUD_DNI=9*2; //String
    private static final int LONGITUD_TELEFONO=9*4; //int
    private static final int LONGITUD_DIRECCION=30*2; //String
    private static final int NUMERO_CAMPOS=5; //String
    private static final int LONGITUD_TOTAL_CLIENTES=LONGITUD_NOMBRE+ LONGITUD_APELLIDO+LONGITUD_DNI+LONGITUD_TELEFONO+LONGITUD_DIRECCION;
    public RandomAccessFile  ficheroClientes;
    public int tamanoRegistros;
    public int numeroRegistros;

    public FicheroAccesoAleatorioDatos(String nombre, String permisos) throws FileNotFoundException {
            this.ficheroClientes = new RandomAccessFile(new File(nombre),permisos);
            this.tamanoRegistros = LONGITUD_TOTAL_CLIENTES;

    }

    public void escribirRegistro(Cliente registro){
            int tamanoCampo=0;
            String campo="";

            for (int i = 0; i < NUMERO_CAMPOS; i++) {
            tamanoCampo= setTamanoCampo(i);
            campo= setCampo(i,registro);
                    try {
                        ficheroClientes.seek(ficheroClientes.length());
                        StringBuffer sb = new StringBuffer();
                        sb.append(obtenerBytes(campo));
                        sb.setLength(tamanoCampo);
                        ficheroClientes.write(String.valueOf(sb).getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                //Preguntar por finally
                    /*finally {
                        try {
                            ficheroClientes.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }*/
            }
            try {
                ficheroClientes.close();
            }catch (IOException e){
                e.printStackTrace();
            }

    }

    /**Devuelve un String con el contenido que corresponde a determinado campo
     * @param i entero que determina el campo que será escrito
     * @param registro Es el cliente que está siendo escrito en el fichero
     * @return String. El valor del campo que toca escribir
     */
    private String setCampo(int i, Cliente registro) {
        String campo = "";
        switch (i) {
            case 0:
                campo = registro.getNombre();
                break;
            case 1:
                campo = registro.getApellidos();
                break;
            case 2:
                campo = registro.getDni();
                break;
            case 3:
                campo = String.valueOf(registro.getTelefono());
                break;
            case 4:
                campo = registro.getDireccion();
                break;
        }
        return campo;
    }
    /** Método destinado a ser utilizado dentro de un bucle. Devuelve el tamaño de cada campo a ser escrito
     * @param i posicion bucle
     * @return tamaño del campo a escribir
     */
    private int setTamanoCampo(int i) {
        int tamanoCampo = 0;
        switch (i) {
            case 0:
                tamanoCampo = LONGITUD_NOMBRE;
                break;
            case 1:
                tamanoCampo = LONGITUD_APELLIDO;
                break;
            case 2:
                tamanoCampo = LONGITUD_DNI;
                break;
            case 3:
                tamanoCampo = LONGITUD_TELEFONO;
                break;
            case 4:
                tamanoCampo = LONGITUD_DIRECCION;
                break;
        }
        return tamanoCampo;
    }

    private void escribirFicheroIndice(String campo, int tamano) {
        StringBuffer sb = new StringBuffer();
        sb.append(obtenerBytes(campo));
        sb.setLength(tamano);
        //this.ficheroIndice.write(tamano);
    }

    public static byte[] obtenerBytes(String campo){
        byte[] dato = campo.getBytes();
        return dato;
    }

    public static void eliminarFicheroSiExiste (String nombre) throws FileNotFoundException{
            File file = new File(nombre);
            if (file.exists()) {
                file.delete();
            }
    }

    public void close() throws IOException{
        if(ficheroClientes!=null){
            ficheroClientes.close();
        }
    }

    public void leerRegistro(int pos) throws IOException {
        ficheroClientes.seek(pos *LONGITUD_TOTAL_CLIENTES);
        ficheroClientes.read();
    }

}