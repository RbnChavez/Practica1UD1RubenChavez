package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public abstract class Gadget {
    private String codigo;
    private String nombre;
    private String marca;
    private double precio;
    private LocalDate fechaAlta;
    private String descripcion;


    public Gadget() {
    }

    public Gadget(String codigo, String nombre, String marca, double precio, LocalDate fechaAlta, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.fechaAlta = fechaAlta;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return getCodigo() + " " + getMarca() + " " + getNombre();
    }
}
