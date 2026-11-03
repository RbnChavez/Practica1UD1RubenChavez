package com.rubenchg.tiendaDeGadgetsTecnologicos.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Reúne los mensajes y el selector de archivos de la aplicación.
 */
public class Util {
    /** Muestra un mensaje de error. */
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /** Muestra una pregunta con las opciones Sí y No. */
    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }

    /** Prepara el selector de archivos con su ruta y filtro. */
    public static JFileChooser crearSelectorFichero(File rutaDefecto, String tipoArchivos, String extension) {
        JFileChooser selector = new JFileChooser();
        if (rutaDefecto != null) {
            selector.setCurrentDirectory(rutaDefecto);
        }
        if (extension != null) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivos, extension);
            selector.setFileFilter(filtro);
        }
        return selector;
    }
}
