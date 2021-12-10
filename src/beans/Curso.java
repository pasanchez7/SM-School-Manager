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
public class Curso implements Serializable {
    private int id;
    private String descripcion;
    private int nivel;
    private String division;
    private int idDivision;

    public Curso(int id, String descripcion, int nivel, String division, int idDivision) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.division = division;
        this.idDivision = idDivision;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    }

    @Override
    public String toString() {
        return descripcion + " Division " + division;
    }
}
