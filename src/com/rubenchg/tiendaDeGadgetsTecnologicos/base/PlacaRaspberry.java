package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public class PlacaRaspberry extends Gadget {
    private int ramGB;
    private int numeroPuertosUSB;

    public PlacaRaspberry() {
        super();
    }

    public PlacaRaspberry(String codigo, String nombre, String marca,
                          double precio, LocalDate fechaAlta,
                          String descripcion, int ramGB, int numeroPuertosUSB) {
        super(codigo, nombre, marca, precio, fechaAlta, descripcion);
        this.ramGB = ramGB;
        this.numeroPuertosUSB = numeroPuertosUSB;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public int getNumeroPuertosUSB() {
        return numeroPuertosUSB;
    }

    public void setNumeroPuertosUSB(int numeroPuertosUSB) {
        this.numeroPuertosUSB = numeroPuertosUSB;
    }

    @Override
    public String toString() {
        return "Raspberry: " + super.toString();
    }
}
