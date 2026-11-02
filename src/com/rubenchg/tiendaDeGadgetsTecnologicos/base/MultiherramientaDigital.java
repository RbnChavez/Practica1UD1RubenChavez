package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public class MultiherramientaDigital extends Gadget {
    private int autonomiaHoras;
    private boolean tieneNFC;
    private boolean tieneInfrarrojos;
    private boolean tieneRadioSubGHz;


    public MultiherramientaDigital() {
        super();
    }

    public MultiherramientaDigital(String codigo, String nombre, String marca,
                                   double precio, int stock, LocalDate fechaAlta,
                                   String descripcion, int autonomiaHoras, boolean tieneNFC,
                                   boolean tieneInfrarrojos, boolean tieneRadioSubGHz) {
        super(codigo, nombre, marca, precio, stock, fechaAlta, descripcion);
        this.autonomiaHoras = autonomiaHoras;
        this.tieneNFC = tieneNFC;
        this.tieneInfrarrojos = tieneInfrarrojos;
        this.tieneRadioSubGHz = tieneRadioSubGHz;
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

    public boolean isTieneInfrarrojos() {
        return tieneInfrarrojos;
    }

    public void setTieneInfrarrojos(boolean tieneInfrarrojos) {
        this.tieneInfrarrojos = tieneInfrarrojos;
    }

    public boolean isTieneRadioSubGHz() {
        return tieneRadioSubGHz;
    }

    public void setTieneRadioSubGHz(boolean tieneRadioSubGHz) {
        this.tieneRadioSubGHz = tieneRadioSubGHz;
    }

    @Override
    public String toString() {
        return "MultiherramientaDigital{" +
                "autonomiaHoras=" + autonomiaHoras +
                ", tieneNFC=" + tieneNFC +
                ", tieneInfrarrojos=" + tieneInfrarrojos +
                ", tieneRadioSubGHz=" + tieneRadioSubGHz +
                "} " + super.toString();
    }
}
