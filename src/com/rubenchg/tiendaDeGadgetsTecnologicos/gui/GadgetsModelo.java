package com.rubenchg.tiendaDeGadgetsTecnologicos.gui;

import com.rubenchg.tiendaDeGadgetsTecnologicos.base.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class GadgetsModelo {
    private ArrayList<Gadget> listaGadgets = new ArrayList<>();

    public ArrayList<Gadget> obtenerGadgets() {
        return listaGadgets;
    }

    public boolean existeCodigo(String codigo){
        for (Gadget gadget : listaGadgets) {
            if (gadget.getCodigo().equals(codigo)){
                return true;
            }
        }

        return false;
    }

    public void altaTecladoSplit(String codigo, String nombre, String marca,
                                 double precio, int stock, LocalDate fechaAlta,
                                 String descripcion, int numeroTeclas, String tipoSwitch,
                                 boolean inalambrico, boolean hotSwap){
        TecladoSplit nuevoTeclado = new TecladoSplit(
                codigo, nombre, marca, precio, stock, fechaAlta, descripcion,
                numeroTeclas, tipoSwitch, inalambrico, hotSwap);
        listaGadgets.add(nuevoTeclado);
    }

    public void altaPlacaRaspberry(String codigo, String nombre, String marca,
                                   double precio, int stock, LocalDate fechaAlta,
                                   String descripcion, int ramGB, int numeroPuertosUSB,
                                   boolean tieneWifi, boolean tieneBluetooth) {
         listaGadgets.add(new PlacaRaspberry(
                codigo, nombre, marca, precio, stock, fechaAlta, descripcion,
                ramGB, numeroPuertosUSB, tieneWifi, tieneBluetooth));
    }

    public void altaMultiherramienta(String codigo, String nombre, String marca,
                                     double precio, int stock, LocalDate fechaAlta,
                                     String descripcion, int autonomiaHoras, boolean tieneNFC,
                                     boolean tieneInfrarrojos, boolean tieneRadioSubGHz){
        listaGadgets.add(new MultiherramientaDigital(
                codigo, nombre, marca, precio, stock, fechaAlta, descripcion,
                autonomiaHoras, tieneNFC, tieneInfrarrojos, tieneRadioSubGHz));
    }
    public void altaImpresora3D(String codigo, String nombre, String marca,
                                double precio, int stock, LocalDate fechaAlta,
                                String descripcion, double anchoImpresionMM,
                                double fondoImpresionMM, double altoImpresionMM,
                                String tecnologia, boolean tieneAutonivelacion){
        listaGadgets.add(new Impresora3D(
                codigo, nombre, marca, precio, stock, fechaAlta, descripcion,
                anchoImpresionMM, fondoImpresionMM, altoImpresionMM,
                tecnologia, tieneAutonivelacion));
    }


}

