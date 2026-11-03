package com.rubenchg.tiendaDeGadgetsTecnologicos.gui;

import com.rubenchg.tiendaDeGadgetsTecnologicos.base.Gadget;
import com.rubenchg.tiendaDeGadgetsTecnologicos.base.MultiherramientaDigital;
import com.rubenchg.tiendaDeGadgetsTecnologicos.base.PlacaRaspberry;
import com.rubenchg.tiendaDeGadgetsTecnologicos.base.TecladoSplit;
import com.rubenchg.tiendaDeGadgetsTecnologicos.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Gestiona los botones y conecta la ventana con el modelo.
 */
public class GadgetsControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private GadgetsModelo modelo;
    private File ultimaRutaExportada;

    public GadgetsControlador(Ventana vista, GadgetsModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException ex) {
            System.out.println("No se ha podido leer la configuración: " + ex.getMessage());
        }
        registrarEventos();
        actualizarCamposTipo();
        refrescar();
    }

    private void registrarEventos() {
        // Los comandos permiten reconocer la acción aunque cambie el texto del botón.
        vista.btnNuevo.setActionCommand("Nuevo");
        vista.btnImportar.setActionCommand("Importar");
        vista.btnExportar.setActionCommand("Exportar");
        vista.btnCerrar.setActionCommand("Cerrar");
        vista.btnBorrar.setActionCommand("Borrar");
        vista.rbTeclado.setActionCommand("Teclado");
        vista.rbRaspberry.setActionCommand("Raspberry");
        vista.rbMultiherramienta.setActionCommand("Multiherramienta");

        vista.btnNuevo.addActionListener(this);
        vista.btnImportar.addActionListener(this);
        vista.btnExportar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnBorrar.addActionListener(this);
        vista.rbTeclado.addActionListener(this);
        vista.rbRaspberry.addActionListener(this);
        vista.rbMultiherramienta.addActionListener(this);
        vista.lstGadgets.addListSelectionListener(this);
        vista.frame.addWindowListener(this);
    }

    private boolean hayCamposVacios() {
        if (vista.txtCodigo.getText().isEmpty() || vista.txtNombre.getText().isEmpty() || vista.txtMarca.getText().isEmpty()
                || vista.txtPrecio.getText().isEmpty() || vista.txtDato1.getText().isEmpty() || vista.txtDato2.getText().isEmpty()
                || vista.dpFechaAlta.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    /** Comprueba los campos y da de alta el gadget elegido. */
    private void nuevoGadget() {
        if (hayCamposVacios()) {
            Util.mensajeError("Debe rellenar todos los campos, excepto la descripción, que es opcional.");
            return;
        }
        if (vista.dpFechaAlta.getDate() == null) {
            Util.mensajeError("La fecha de alta no es válida. Puede elegirla en el calendario.");
            return;
        }
        String codigo = vista.txtCodigo.getText();
        if (modelo.existeCodigo(codigo)) {
            Util.mensajeError("Ya existe un artículo con el código " + codigo + ".");
            return;
        }

        String nombre = vista.txtNombre.getText();
        String marca = vista.txtMarca.getText();
        double precio = Double.parseDouble(vista.txtPrecio.getText());
        LocalDate fechaAlta = vista.dpFechaAlta.getDate();
        String descripcion = vista.txtAreaDescripcion.getText();
        int dato1 = Integer.parseInt(vista.txtDato1.getText());

        if (precio < 0) {
            Util.mensajeError("El precio debe ser un número igual o mayor que cero.");
            return;
        }
        if (dato1 <= 0) {
            Util.mensajeError(vista.lblDato1.getText() + " debe ser un entero mayor que cero.");
            return;
        }

        if (vista.rbTeclado.isSelected()) {
            modelo.altaTecladoSplit(codigo, nombre, marca, precio, fechaAlta, descripcion, dato1, vista.txtDato2.getText());
        } else if (vista.rbRaspberry.isSelected()) {
            int puertosUSB = Integer.parseInt(vista.txtDato2.getText());
            if (puertosUSB < 0) {
                Util.mensajeError("El número de puertos USB no puede ser negativo.");
                return;
            }
            modelo.altaPlacaRaspberry(codigo, nombre, marca, precio, fechaAlta,
                    descripcion, dato1, puertosUSB);
        } else if (vista.rbMultiherramienta.isSelected()) {
            String nfc = vista.txtDato2.getText();
            if (!nfc.equalsIgnoreCase("sí") && !nfc.equalsIgnoreCase("si")
                    && !nfc.equalsIgnoreCase("no")) {
                Util.mensajeError("En el campo NFC debe escribir sí o no.");
                return;
            }
            boolean tieneNFC = !nfc.equalsIgnoreCase("no");
            modelo.altaMultiherramienta(codigo, nombre, marca, precio, fechaAlta, descripcion, dato1, tieneNFC);
        } else {
            Util.mensajeError("Debe elegir un tipo de gadget.");
            return;
        }
        refrescar();
        limpiarCampos();
    }

    /** Pide confirmación y borra el artículo seleccionado. */
    private void borrarGadget() {
        Gadget seleccionado = vista.lstGadgets.getSelectedValue();
        if (seleccionado == null) {
            Util.mensajeError("Debe seleccionar un artículo de la lista para borrarlo.");
            return;
        }
        int respuesta = Util.mensajeConfirmacion("¿Desea borrar este artículo?\n" + seleccionado, "Borrar artículo");
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        modelo.eliminarGadget(seleccionado);
        refrescar();
        limpiarCampos();
    }

    private void limpiarCampos() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtMarca.setText("");
        vista.txtPrecio.setText("");
        vista.txtAreaDescripcion.setText("");
        vista.txtDato1.setText("");
        vista.txtDato2.setText("");
        vista.dpFechaAlta.setText("");
        vista.txtCodigo.requestFocus();
    }

    public void refrescar() {
        vista.dlmGadgets.clear();
        for (Gadget gadget : modelo.obtenerGadgets()) {
            vista.dlmGadgets.addElement(gadget);
        }
    }

    private void actualizarCamposTipo() {
        if (vista.rbTeclado.isSelected()) {
            vista.lblDato1.setText("Número de teclas");
            vista.lblDato2.setText("Tipo de switches");
        } else if (vista.rbRaspberry.isSelected()) {
            vista.lblDato1.setText("RAM (GB)");
            vista.lblDato2.setText("Puertos USB");
        } else if (vista.rbMultiherramienta.isSelected()) {
            vista.lblDato1.setText("Autonomía (horas)");
            vista.lblDato2.setText("Tiene NFC (sí/no)");
        }
    }

    /** Permite elegir un XML y cargar sus artículos. */
    private void importar() {
        JFileChooser selector = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
        if (selector.showOpenDialog(vista.frame) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        try {
            // Primero carga el XML en otro modelo para conservar los artículos actuales si falla.
            GadgetsModelo modeloImportado = new GadgetsModelo();
            modeloImportado.importarXML(selector.getSelectedFile());
            modelo.obtenerGadgets().clear();
            modelo.obtenerGadgets().addAll(modeloImportado.obtenerGadgets());
            ultimaRutaExportada = selector.getSelectedFile();
            refrescar();
            limpiarCampos();
        } catch (ParserConfigurationException ex) {
            Util.mensajeError("No se ha podido preparar el lector de XML.");
        } catch (IOException ex) {
            Util.mensajeError("No se ha podido leer el archivo XML.");
        } catch (SAXException ex) {
            Util.mensajeError("El archivo no contiene un XML válido.");
        }
    }

    /** Permite elegir el archivo donde se guarda el XML. */
    private void exportar() {
        JFileChooser selector = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
        if (selector.showSaveDialog(vista.frame) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        try {
            modelo.exportar(selector.getSelectedFile());
            ultimaRutaExportada = selector.getSelectedFile();
        } catch (ParserConfigurationException ex) {
            Util.mensajeError("No se ha podido preparar el documento XML.");
        } catch (TransformerException ex) {
            Util.mensajeError("No se ha podido exportar el XML: " + ex.getMessage());
        }
    }

    private void cargarDatosConfiguracion() throws IOException {
        File fichero = new File("gadgets.conf");
        Properties configuracion = new Properties();
        FileReader lector = new FileReader(fichero);
        try {
            configuracion.load(lector);
        } finally {
            lector.close();
        }
        String ruta = configuracion.getProperty("ultimaRutaExportada");
        if (ruta != null && !ruta.isEmpty()) {
            ultimaRutaExportada = new File(ruta);
        }
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        if (ultimaRutaExportada != null) {
            configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        }
        PrintWriter escritor = new PrintWriter("gadgets.conf");
        try {
            configuracion.store(escritor, "Configuracion de la tienda de gadgets");
        } finally {
            escritor.close();
        }
    }

    /** Pide confirmación y guarda la configuración antes de salir. */
    private void cerrar() {
        if (Util.mensajeConfirmacion("¿Desea cerrar la aplicación? Los artículos que no haya exportado no se guardan.",
                "Cerrar tienda") != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            guardarConfiguracion();
        } catch (IOException ex) {
            Util.mensajeError("No se ha podido guardar la configuración: " + ex.getMessage());
        }
        System.exit(0);
    }

    /** Atiende las acciones de los botones del formulario. */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nuevo":
                nuevoGadget();
                break;
            case "Importar":
                importar();
                break;
            case "Exportar":
                exportar();
                break;
            case "Cerrar":
                cerrar();
                break;
            case "Borrar":
                borrarGadget();
                break;
            case "Teclado":
            case "Raspberry":
            case "Multiherramienta":
                actualizarCamposTipo();
                vista.txtDato1.setText("");
                vista.txtDato2.setText("");
                break;
        }
    }

    /** Muestra los datos del artículo seleccionado en la lista. */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        Gadget seleccionado = vista.lstGadgets.getSelectedValue();
        if (seleccionado == null) {
            return;
        }
        vista.txtCodigo.setText(seleccionado.getCodigo());
        vista.txtNombre.setText(seleccionado.getNombre());
        vista.txtMarca.setText(seleccionado.getMarca());
        vista.txtPrecio.setText(String.valueOf(seleccionado.getPrecio()));
        vista.txtAreaDescripcion.setText(seleccionado.getDescripcion());
        vista.dpFechaAlta.setDate(seleccionado.getFechaAlta());

        if (seleccionado instanceof TecladoSplit) {
            TecladoSplit teclado = (TecladoSplit) seleccionado;
            vista.rbTeclado.doClick();
            vista.txtDato1.setText(String.valueOf(teclado.getNumeroTeclas()));
            vista.txtDato2.setText(teclado.getTipoSwitch());
        } else if (seleccionado instanceof PlacaRaspberry) {
            PlacaRaspberry placa = (PlacaRaspberry) seleccionado;
            vista.rbRaspberry.doClick();
            vista.txtDato1.setText(String.valueOf(placa.getRamGB()));
            vista.txtDato2.setText(String.valueOf(placa.getNumeroPuertosUSB()));
        } else if (seleccionado instanceof MultiherramientaDigital) {
            MultiherramientaDigital multiherramienta = (MultiherramientaDigital) seleccionado;
            vista.rbMultiherramienta.doClick();
            vista.txtDato1.setText(String.valueOf(multiherramienta.getAutonomiaHoras()));
            vista.txtDato2.setText(multiherramienta.isTieneNFC() ? "Sí" : "No");
        }
        actualizarCamposTipo();
    }

    /** Gestiona el cierre con la X de la ventana. */
    @Override
    public void windowClosing(WindowEvent e) {
        cerrar();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
