package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public class TecladoSplit extends Gadget {
    private int numeroTeclas;
    private String tipoSwitch;
    private boolean inalambrico;
    private boolean hotSwap;

    public TecladoSplit() {
        super();
    }

    public TecladoSplit(String codigo, String nombre, String marca,
                        double precio, int stock, LocalDate fechaAlta,
                        String descripcion, int numeroTeclas, String tipoSwitch,
                        boolean inalambrico, boolean hotSwap) {
        super(codigo, nombre, marca, precio, stock, fechaAlta, descripcion);
        this.numeroTeclas = numeroTeclas;
        this.tipoSwitch = tipoSwitch;
        this.inalambrico = inalambrico;
        this.hotSwap = hotSwap;
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

    public boolean isInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    public boolean isHotSwap() {
        return hotSwap;
    }

    public void setHotSwap(boolean hotSwap) {
        this.hotSwap = hotSwap;
    }

    @Override
    public String toString() {
        return "TecladoSplit{" +
                "numeroTeclas=" + numeroTeclas +
                ", tipoSwitch='" + tipoSwitch + '\'' +
                ", inalambrico=" + inalambrico +
                ", hotSwap=" + hotSwap +
                "} " + super.toString();
    }
}