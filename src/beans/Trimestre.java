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
public class Trimestre implements Serializable {
    private int id;
    private String descripcion;
    private CicloLectivo cicloLectivo;

    public Trimestre(int id, String descripcion, CicloLectivo cicloLectivo) {
        this.id = id;
        this.descripcion = descripcion;
        this.cicloLectivo = cicloLectivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CicloLectivo getCicloLectivo() {
        return cicloLectivo;
    }

    public void setCicloLectivo(CicloLectivo cicloLectivo) {
        this.cicloLectivo = cicloLectivo;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}
