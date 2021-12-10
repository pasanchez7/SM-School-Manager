/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

/**
 *
 * @author fernando
 */
public class Asignatura implements Serializable {
    private int id;
    private String nombre;
    private int planDeEstudio;

    public Asignatura(int id, String nombre, int planDeEstudio) {
        this.id = id;
        this.nombre = nombre;
        this.planDeEstudio = planDeEstudio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlanDeEstudio() {
        return planDeEstudio;
    }

    public void setPlanDeEstudio(int planDeEstudio) {
        this.planDeEstudio = planDeEstudio;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
