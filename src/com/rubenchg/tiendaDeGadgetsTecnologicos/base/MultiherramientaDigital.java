package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public class MultiherramientaDigital extends Gadget {
    private int autonomiaHoras;
    private boolean tieneNFC;


    public MultiherramientaDigital() {
        super();
    }

    public MultiherramientaDigital(String codigo, String nombre, String marca,
                                   double precio, LocalDate fechaAlta,
                                   String descripcion, int autonomiaHoras, boolean tieneNFC) {
        super(codigo, nombre, marca, precio, fechaAlta, descripcion);
        this.autonomiaHoras = autonomiaHoras;
        this.tieneNFC = tieneNFC;
    }

    public int getAutonomiaHoras() {
        return autonomiaHoras;
    }

    public void setAutonomiaHoras(int autonomiaHoras) {
        this.autonomiaHoras = autonomiaHoras;
    }

    public boolean isTieneNFC() {
        return tieneNFC;
    }

    public void setTieneNFC(boolean tieneNFC) {
        this.tieneNFC = tieneNFC;
    }

    @Override
    public String toString() {
        return "Multiherramienta digital: " + super.toString();
    }
}
