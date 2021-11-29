package FileManager;

import Entities.Cliente;

import java.io.*;

public class FicheroAccesoAleatorioDatos {

    public RandomAccessFile  ficheroClientes;
    public int tamanoRegistros;
    public String nombreFichero;

    public FicheroAccesoAleatorioDatos(String nombre, String permisos, int tamanoRegistros) throws FileNotFoundException {
        this.nombreFichero=nombre;
            this.ficheroClientes = new RandomAccessFile(new File(nombreFichero),permisos);
            this.tamanoRegistros = tamanoRegistros;
    }

    public void escribirRegistro( Cliente registro) throws IOException {
        ObjectOutputStream oos=null;
        try (FileOutputStream salida = new FileOutputStream(nombreFichero,true)){
            if(ficheroClientes.length()<0) {
                oos=new ObjectOutputStream(salida);
            }else{
                oos=new MyObjectOutputStream(salida);
            }
            oos.writeObject(registro);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerRegistro(int pos) throws IOException {
        int posicion = pos*tamanoRegistros;
        byte [] array = new byte[(int)ficheroClientes.length()];
        try (ByteArrayInputStream leer = new ByteArrayInputStream(array);
             ObjectInputStream entrada = new ObjectInputStream(leer)){
            ficheroClientes.seek(posicion);
            ficheroClientes.read(array);
            Object clienteLeido;
            clienteLeido = entrada.readObject();
            System.out.println(clienteLeido);
        }catch (EOFException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarFicheroSiExiste (String nombre) throws FileNotFoundException{
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



}

