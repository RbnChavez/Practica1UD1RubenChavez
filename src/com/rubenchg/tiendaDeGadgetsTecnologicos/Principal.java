package com.rubenchg.tiendaDeGadgetsTecnologicos;

import com.rubenchg.tiendaDeGadgetsTecnologicos.gui.GadgetsControlador;
import com.rubenchg.tiendaDeGadgetsTecnologicos.gui.GadgetsModelo;
import com.rubenchg.tiendaDeGadgetsTecnologicos.gui.Ventana;

/**
 * Inicia la tienda creando la ventana, el modelo y el controlador.
 */
public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        GadgetsModelo modelo = new GadgetsModelo();
        GadgetsControlador controlador = new GadgetsControlador(vista, modelo);
    }
}
