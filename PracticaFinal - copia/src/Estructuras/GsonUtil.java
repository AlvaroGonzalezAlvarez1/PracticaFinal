package Estructuras;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GsonUtil {

    // Método para guardar un objeto en un archivo JSON
    public static <T> void guardarObjetoEnArchivo(String rutaArchivo, T objeto) {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
        }
    }

    // Método para cargar un objeto desde un archivo JSON
    public static <T> T cargarObjetoDesdeArchivo(String rutaArchivo, Class<T> clase) {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        T objeto = null;
        try (FileReader reader = new FileReader(rutaArchivo)) {
            objeto = gson.fromJson(reader, clase);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        return objeto;
    }

    //Método que guarda varios objetos en
    public static <T> void guardarArray(String rutaArchivo, T[] datos) {
        com.google.gson.Gson gson = new com.google.gson.Gson();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
        }
    }

    //Método que carga un array de objetos desde un archivo Json
    public static <T> T[] cargarArray(String rutaArchivo, Class<T[]> claseArray) {
        com.google.gson.Gson gson=new com.google.gson.Gson();
        T[] datos=null;
        try (FileReader reader=new FileReader(rutaArchivo)) {
            datos = gson.fromJson(reader, claseArray);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: "+rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        return datos;
    }

    //Método que imprime el toString
    public static <T> void imprimirToStringDesdeArchivo(String rutaArchivo, Class<T> clase) {
        T objeto = cargarObjetoDesdeArchivo(rutaArchivo, clase);
        if (objeto!=null) {
            System.out.println(objeto.toString());
        } else {
            System.out.println("No se pudo leer el fichero: "+rutaArchivo);
        }
    }

    //Método que elimina un fichero
    public static boolean eliminarFichero(String rutaArchivo) {
        java.io.File file=new java.io.File(rutaArchivo);
        return file.delete();
    }
}
