/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosyprocesos1ev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author jborregb
 */
public class ServiciosYProcesos1Ev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //Llamamos al metodo descomprimir
        descomprimir();
        
        //Creamos la coleccion de empleados
        Collection<Empleado> empleados = new HashSet<Empleado>();
        
        //Añadimos los empleados
        empleados.add(new Empleado("Marta López Galván", 0));
        empleados.add(new Empleado("Clara Lago Cuevas", 0));
        empleados.add(new Empleado("Alfredo Vargas López", 0));
        empleados.add(new Empleado("Iker Gámez Luengo", 0));
        empleados.add(new Empleado("Jonathan Páez DosSantos", 0));
        empleados.add(new Empleado("Berto Ruiseñor Pérez", 0));
        empleados.add(new Empleado("Dionisio Cabello Ruivalvo", 0));
        empleados.add(new Empleado("Sara Méndez Ruíz", 0));

        //Creamos los hilos
        Thread hilo1 = new Thread(new HiloTelefono(empleados,1,10));
        Thread hilo2 = new Thread(new HiloTelefono(empleados,11,20));
        Thread hilo3 = new Thread(new HiloTelefono(empleados,21,30));
        
        //Empezamos a ejecutar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        //Esperamos a que acaben los hilos para que el hilo principal imprima el resultado
        hilo1.join();
        hilo2.join();
        hilo3.join();
        
        //Imprimimos el resultado final
        System.out.println(empleados);
        
    }
    
    public static void descomprimir() throws IOException, InterruptedException {
        System.out.println("Descomprimiendo archivo...");
        
        //Instanciamos el comando 7z x RegistroLlamadas.zip que descomprimirá el archivo RegistroLlamadas.zip respetando sus rutas
        ProcessBuilder ps = new ProcessBuilder("C:\\Program Files\\7-Zip\\7z.exe","x","RegistroLlamadas.zip");
        
        //Si diese error, lo redirigimos 
        ps.redirectErrorStream(true);
        
        //Ejecutamos el proceso
        Process pr = ps.start();
        
        //Instanciamos el BufferedReader para imprimir por pantalla la información que el proceso nos envie
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        
        //Esperamos a que el proceso acabe para seguir ejecutando el programa 
        pr.waitFor();
        
        //Imprimimos por pantalla la informacion
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line); 
        }
        
        //Finalizamos el BufferedReader
        in.close();
        
        System.out.println("\nArchivo descomprimido con exito!\n");
    }
}
