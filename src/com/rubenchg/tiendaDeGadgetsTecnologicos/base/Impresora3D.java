package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public class Impresora3D extends Gadget {
    private double anchoImpresionMM;
    private double fondoImpresionMM;
    private double altoImpresionMM;
    private String tecnologia;
    private boolean tieneAutonivelacion;

    public Impresora3D() {
        super();
    }
    public Impresora3D(String codigo, String nombre, String marca,
                       double precio, int stock, LocalDate fechaAlta,
                       String descripcion, double anchoImpresionMM,
                       double fondoImpresionMM, double altoImpresionMM,
                       String tecnologia, boolean tieneAutonivelacion) {
        super(codigo, nombre, marca, precio, stock, fechaAlta, descripcion);
        this.anchoImpresionMM = anchoImpresionMM;
        this.fondoImpresionMM = fondoImpresionMM;
        this.altoImpresionMM = altoImpresionMM;
        this.tecnologia = tecnologia;
        this.tieneAutonivelacion = tieneAutonivelacion;
    }

    public double getAnchoImpresionMM() {
        return anchoImpresionMM;
    }

    public void setAnchoImpresionMM(double anchoImpresionMM) {
        this.anchoImpresionMM = anchoImpresionMM;
    }

    public double getFondoImpresionMM() {
        return fondoImpresionMM;
    }

    public void setFondoImpresionMM(double fondoImpresionMM) {
        this.fondoImpresionMM = fondoImpresionMM;
    }

    public double getAltoImpresionMM() {
        return altoImpresionMM;
    }

    public void setAltoImpresionMM(double altoImpresionMM) {
        this.altoImpresionMM = altoImpresionMM;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public boolean isTieneAutonivelacion() {
        return tieneAutonivelacion;
    }

    public void setTieneAutonivelacion(boolean tieneAutonivelacion) {
        this.tieneAutonivelacion = tieneAutonivelacion;
    }

    @Override
    public String toString() {
        return "Impresora3D{" +
                "anchoImpresionMM=" + anchoImpresionMM +
                ", fondoImpresionMM=" + fondoImpresionMM +
                ", altoImpresionMM=" + altoImpresionMM +
                ", tecnologia='" + tecnologia + '\'' +
                ", tieneAutonivelacion=" + tieneAutonivelacion +
                "} " + super.toString();
    }
}
