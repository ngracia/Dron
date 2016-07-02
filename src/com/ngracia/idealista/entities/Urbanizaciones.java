package com.ngracia.idealista.entities;

/**
 * Created by Nestor Gracia on 29/06/2016.
 */
public class Urbanizaciones {
    private String x;
    private String y;
    private String id;
    private int rango;
    private boolean origen;
    private boolean visitada;
    private boolean posicionActual;

    public String getX() {return x;}

    public void setX(String x) {this.x = x;}

    public String getY() {return y;}

    public void setY(String y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public int getRango() {return rango;}

    public void setRango(int rango) {
        this.rango = rango;
    }

    public boolean isVisitada() {return visitada;}

    public void setVisitada(boolean visitada) {this.visitada = visitada;}

    public boolean isOrigen() {return origen;}

    public void setOrigen(boolean origen) {this.origen = origen;}

    public boolean isPosicionActual() {return posicionActual;}

    public void setPosicionActual(boolean posicionActual) {this.posicionActual = posicionActual;}


}
