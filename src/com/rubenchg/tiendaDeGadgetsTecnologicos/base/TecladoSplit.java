package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

/**
 * Representa un teclado split con su número de teclas y tipo de switches.
 */
public class TecladoSplit extends Gadget {
    private int numeroTeclas;
    private String tipoSwitch;

    public TecladoSplit() {
        super();
    }

    public TecladoSplit(String codigo, String nombre, String marca, double precio, LocalDate fechaAlta, String descripcion, int numeroTeclas, String tipoSwitch) {
        super(codigo, nombre, marca, precio, fechaAlta, descripcion);
        this.numeroTeclas = numeroTeclas;
        this.tipoSwitch = tipoSwitch;
    }

    public int getNumeroTeclas() {
        return numeroTeclas;
    }

    public void setNumeroTeclas(int numeroTeclas) {
        this.numeroTeclas = numeroTeclas;
    }

    public String getTipoSwitch() {
        return tipoSwitch;
    }

    public void setTipoSwitch(String tipoSwitch) {
        this.tipoSwitch = tipoSwitch;
    }

    @Override
    public String toString() {
        return "Teclado split: " + super.toString();
    }
}
