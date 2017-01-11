package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters;

import java.io.Serializable;

/**
 * Created by alexi on 09/01/2017.
 */
public class Vehiculo implements Serializable{

    public enum Tipo {
        SEDAN, PICKUP, OFFROAD, COUPE
    }

    private String nombre;
    private String descripcion;
    private Tipo tipo;

    public Vehiculo(String nombre, String descripcion, Tipo tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
