package com.rubenchg.tiendaDeGadgetsTecnologicos.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.rubenchg.tiendaDeGadgetsTecnologicos.base.Gadget;

import javax.swing.*;

/**
 * Vista Swing de la tienda: formulario, lista y botones.
 */
public class Ventana {
    public JFrame frame;
    public DefaultListModel<Gadget> dlmGadgets;
    private JPanel panelPrincipal;
    public JTextField txtCodigo;
    private JLabel labelCodigo;
    private JLabel labelNombre;
    private JLabel labelMarca;
    private JLabel labelPrecio;
    private JLabel labelFechaDeAlta;
    public JTextArea txtAreaDescripcion;
    public JTextField txtNombre;
    public JTextField txtMarca;
    public JTextField txtPrecio;
    public JTextField txtDato1;
    public JTextField txtDato2;
    public JLabel lblDato1;
    public JLabel lblDato2;
    private JPanel panelRbotonesUp;
    public JRadioButton rbTeclado;
    public JRadioButton rbRaspberry;
    public JRadioButton rbMultiherramienta;
    private JLabel labelDescripcion;
    public JList<Gadget> lstGadgets;
    private JPanel panelBotonesDown;
    public JButton btnNuevo;
    public JButton btnImportar;
    public JButton btnExportar;
    public JButton btnCerrar;
    public DatePicker dpFechaAlta;
    public JButton btnBorrar;

    public Ventana() {
        initComponents();
        // Crea la ventana y coloca dentro panelPrincipal.
        frame = new JFrame("Tienda de gadgets tecnológicos");
        frame.setContentPane(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Ajusta el tamaño al contenido, centra la ventana y la muestra.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void initComponents() {
        // Utiliza dlmGadgets para mostrar los artículos en lstGadgets.
        dlmGadgets = new DefaultListModel<Gadget>();
        lstGadgets.setModel(dlmGadgets);

        // Permite seleccionar un solo tipo de gadget a la vez.
        ButtonGroup grupoTipos = new ButtonGroup();
        grupoTipos.add(rbTeclado);
        grupoTipos.add(rbRaspberry);
        grupoTipos.add(rbMultiherramienta);

        // Selecciona Teclado al abrir el formulario.
        rbTeclado.doClick();
    }

}
