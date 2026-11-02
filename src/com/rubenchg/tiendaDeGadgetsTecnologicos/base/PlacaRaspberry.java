package com.rubenchg.tiendaDeGadgetsTecnologicos.base;

import java.time.LocalDate;

public class PlacaRaspberry extends Gadget {
    private int ramGB;
    private int numeroPuertosUSB;
    private boolean tieneWifi;
    private boolean tieneBluetooth;

    public PlacaRaspberry() {
        super();
    }

    public PlacaRaspberry(String codigo, String nombre, String marca,
                          double precio, int stock, LocalDate fechaAlta,
                          String descripcion, int ramGB, int numeroPuertosUSB,
                          boolean tieneWifi, boolean tieneBluetooth) {
        super(codigo, nombre, marca, precio, stock, fechaAlta, descripcion);
        this.ramGB = ramGB;
        this.numeroPuertosUSB = numeroPuertosUSB;
        this.tieneWifi = tieneWifi;
        this.tieneBluetooth = tieneBluetooth;
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

    public boolean isTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public boolean isTieneBluetooth() {
        return tieneBluetooth;
    }

    public void setTieneBluetooth(boolean tieneBluetooth) {
        this.tieneBluetooth = tieneBluetooth;
    }

    @Override
    public String toString() {
        return "PlacaRaspberry{" +
                "ramGB=" + ramGB +
                ", numeroPuertosUSB=" + numeroPuertosUSB +
                ", tieneWifi=" + tieneWifi +
                ", tieneBluetooth=" + tieneBluetooth +
                "} " + super.toString();
    }
}
