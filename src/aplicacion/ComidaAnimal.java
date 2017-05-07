package aplicacion;


import aplicacion.Animal;
import aplicacion.Comida;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rosa
 */
public class ComidaAnimal {
    private Animal animal; 
    private Comida comida; 
    private int racion; 

    public ComidaAnimal(Animal animal, Comida comida, int racion) {
        this.animal = animal;
        this.comida = comida;
        this.racion = racion;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public int getRacion() {
        return racion;
    }

    public void setRacion(int racion) {
        this.racion = racion;
    }
    
    
}
