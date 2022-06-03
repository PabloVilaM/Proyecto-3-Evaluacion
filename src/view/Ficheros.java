/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Ficheros {
    PrintWriter escribir;
    FileWriter fich;
    String opcion;
    public void crearFichero(String respuesta, boolean correcion, Map lista, int code){
        
        String a = (String)lista.get(code);
        if (correcion == true){
           opcion = "correcta";
           
        }
        else{
            opcion = "incorrecta";
        }
        try {
            fich = new FileWriter("Logs.txt", true);
            escribir = new PrintWriter(fich);
            escribir.println('[' + "Tu respuesta: "+ respuesta + "\nFue la opción: "+ opcion +"\nLa pregunta fue: "+ a + "\nCon el código: "+ code + ']');

        } catch (IOException ex) {
            System.out.println("Error al crear/escribir el archivo de logs");
        }
        finally{
            escribir.close();
        }
    }
    
    
}
