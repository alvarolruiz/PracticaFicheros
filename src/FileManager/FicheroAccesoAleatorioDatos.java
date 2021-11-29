package FileManager;

import Entities.Cliente;

import java.io.*;

public class FicheroAccesoAleatorioDatos {

    public RandomAccessFile  ficheroClientes;
    public int tamanoRegistros;

    public FicheroAccesoAleatorioDatos(String nombre, String permisos, int tamanoRegistros) throws FileNotFoundException {
            this.ficheroClientes = new RandomAccessFile(new File(nombre),permisos);
            this.tamanoRegistros = tamanoRegistros;
    }

    public void escribirRegistro( Cliente registro) throws IOException {
        try (ByteArrayOutputStream escribir = new ByteArrayOutputStream();
             ObjectOutputStream salida = new ObjectOutputStream(escribir)){
            ficheroClientes.seek(ficheroClientes.length());
            salida.writeObject((Cliente)registro);
            salida.flush();
            byte [] array = escribir.toByteArray();
            ficheroClientes.write(array);
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
            ficheroClientes.readFully(array);
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

