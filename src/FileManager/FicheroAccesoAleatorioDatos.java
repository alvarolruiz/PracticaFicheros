package FileManager;

import Entities.Cliente;

import java.io.*;

public class FicheroAccesoAleatorioDatos {

    public RandomAccessFile  ficheroClientes;
    public File archivo;
    public int tamanoRegistros;
    public String nombreFichero;

    public FicheroAccesoAleatorioDatos(String nombre, String permisos, int tamanoRegistros) throws FileNotFoundException {
        this.nombreFichero=nombre;
        archivo= new File(nombre);
            this.ficheroClientes = new RandomAccessFile(archivo,permisos);
            this.tamanoRegistros = tamanoRegistros;
    }

    /**
     * Escribe un cliente completo en el random access file.
     * Precondiciones: Que el cliente recibido no sea nulo
     * @param registro
     * @throws IOException
     */
    public void escribirRegistro(Cliente registro) throws IOException {
        //Si no funciona escribir campo por campo y despues hacer seek.
        ObjectOutputStream oos=null;
        try (FileOutputStream salida = new FileOutputStream(nombreFichero,true)){
            oos=new ObjectOutputStream(salida);

            oos.writeObject(registro);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recibe una posición y lee el cliente que se encuentre en ella
     * Precondiciones: Que el fichero contenga algún registro en esa posición
     * @param pos
     * @throws IOException
     */
    public void leerRegistro(int pos) throws IOException {
        int posicion = pos * tamanoRegistros;
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(archivo))){
            ois.skipNBytes(posicion);
            int pont =(int) ficheroClientes.getFilePointer();
            Cliente c;
            c = (Cliente) ois.readObject();
            System.out.println(c);
            ois.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        /*byte [] array = new byte[(int)ficheroClientes.length()];
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
        }*/

    }

    /**
     * Elimina el fichero con el nombre pasado por prámetro si existe.
     * @param nombre
     * @throws FileNotFoundException
     */
    public void eliminarFicheroSiExiste (String nombre) throws FileNotFoundException{
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

