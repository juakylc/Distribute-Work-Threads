/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosyprocesos1ev;

/**
 *
 * @author jborregb
 */
public class Empleado {
    //Atributo que representará el nombre del empleado
    private String name;
    
    //Atributo que representará los minutos hablados por el empleado
    private int mins;
    
    //Constructor
    public Empleado (String name, int mins) {
        this.name = name;
        this.mins = mins;
    }

    //Getter del nombre
    public String getName() {
        return name;
    }

    //Setter del nombre
    public void setName(String name) {
        this.name = name;
    }

    //Getter de los minutos
    public int getMins() {
        return mins;
    }

    //Setter de los minutos
    public void setMins(int mins) {
        this.mins = mins;
    }
    
    /*
      Método que incrementará los minutos hablados por este empleado.
      Solo podrá acceder por cada empleado un metodo a la vez
    */
    public void incrementSync(int n) {
        synchronized(this) {
            this.mins += n;
        }
    }

    //Método toString
    @Override
    public String toString() {
        return name + ": " + mins + " minutos";
    }
    
}
