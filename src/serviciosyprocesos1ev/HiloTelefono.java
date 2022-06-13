/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosyprocesos1ev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jborregb
 */
public class HiloTelefono implements Runnable {
    //Atributo que representa a todos los empleados
    private Collection empleados;
    
    //Atributo que representa el primer archivo que recorrerá este hilo
    private int firstFile;
    
    //Atributo que representa el ultimo archivo que recorrerá este hilo
    private int lastFile;

    //Constructor
    public HiloTelefono(Collection empleados, int firstFile, int lastFile) {
        this.empleados = empleados;
        this.firstFile = firstFile;
        this.lastFile = lastFile;
    }
    
    //Metodo run del hilo
    @Override
    public void run() {
        try {
            File txt;
            FileReader fr;
            BufferedReader br;
            String line;
            //for que recorrerá los i entre el primer y ultimo archivo
            for (int i = firstFile; i <= lastFile; i++) {
                
                //ese i representará el nombre del archivo a leer el cual instanciamos
                txt = new File("RegistroLlamadas\\"+i+".txt");
                fr = new FileReader(txt);
                br = new BufferedReader(fr);
                
                //Mientras haya lineas en el archivo
                while ((line = br.readLine()) != null) {
                    //Separaremos la linea en dos, siendo el separador la coma
                    String[] array = line.split(",");
                    
                    //Seleccionamos el empleado cuyo nombre coincida con el de la linea
                    Empleado empleado = buscar(empleados, array[0]);
                    
                    //Le incrementamos los minutos hablados añadiendole los que estan en la linea
                    empleado.incrementSync(Integer.parseInt(array[1]));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloTelefono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método que dada una colección y un nombre, busca el empleado en la coleccion con dicho nombre
    public static Empleado buscar(Collection<Empleado> collection, String nombre) {
        //For que recorre la coleccion
        for (Empleado empleado : collection) {
            //Si el nombre del empleado coincide con el nombre dado
            if (empleado.getName().equals(nombre)) {
                //Devolvemos el empleado
                return empleado;
            }
        }
        //Si no lo encuentra, devolvemos null
        return null;
    }
}
    
