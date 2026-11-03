package com.rubenchg.tiendaDeGadgetsTecnologicos.gui;

import com.rubenchg.tiendaDeGadgetsTecnologicos.base.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.transform.TransformerException;

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
                                 double precio, LocalDate fechaAlta,
                                 String descripcion, int numeroTeclas, String tipoSwitch){
        TecladoSplit nuevoTeclado = new TecladoSplit(
                codigo, nombre, marca, precio, fechaAlta, descripcion,
                numeroTeclas, tipoSwitch);
        listaGadgets.add(nuevoTeclado);
    }

    public void altaPlacaRaspberry(String codigo, String nombre, String marca,
                                   double precio, LocalDate fechaAlta,
                                   String descripcion, int ramGB, int numeroPuertosUSB) {
         listaGadgets.add(new PlacaRaspberry(
                codigo, nombre, marca, precio, fechaAlta, descripcion,
                ramGB, numeroPuertosUSB));
    }

    public void altaMultiherramienta(String codigo, String nombre, String marca,
                                     double precio, LocalDate fechaAlta,
                                     String descripcion, int autonomiaHoras, boolean tieneNFC){
        listaGadgets.add(new MultiherramientaDigital(
                codigo, nombre, marca, precio, fechaAlta, descripcion,
                autonomiaHoras, tieneNFC));
    }
    public void exportar(File fichero) throws ParserConfigurationException, TransformerException{
        //Preparar herramientas XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Documento
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);
        //elemento Raiz
        Element raiz = documento.createElement("Gadgets");
        documento.getDocumentElement().appendChild(raiz);

        // elementos nodo
        for (Gadget gadget : listaGadgets) {
            Element nodoGadget = null;

            if (gadget instanceof TecladoSplit) {
                nodoGadget = documento.createElement("TecladoSplit");
            } else if (gadget instanceof PlacaRaspberry) {
                nodoGadget = documento.createElement("PlacaRaspberry");
            } else {
                nodoGadget = documento.createElement("MultiherramientaDigital");
            }
            raiz.appendChild(nodoGadget);

            Element nodoDatos = null;
            Text texto = null;

            nodoDatos = documento.createElement("codigo");
            texto = documento.createTextNode(gadget.getCodigo());
            nodoDatos.appendChild(texto);
            nodoGadget.appendChild(nodoDatos);
            nodoDatos = documento.createElement("nombre");
            nodoGadget.appendChild(nodoDatos);
            texto = documento.createTextNode(gadget.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("marca");
            nodoGadget.appendChild(nodoDatos);
            texto = documento.createTextNode(gadget.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoGadget.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(gadget.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechaAlta");
            nodoGadget.appendChild(nodoDatos);
            texto = documento.createTextNode(gadget.getFechaAlta().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("descripcion");
            nodoGadget.appendChild(nodoDatos);
            texto = documento.createTextNode(gadget.getDescripcion());
            nodoDatos.appendChild(texto);

            // Atributos propios de cada gadget.
            if (gadget instanceof TecladoSplit) {
                nodoDatos = documento.createElement("numeroTeclas");
                nodoGadget.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((TecladoSplit) gadget).getNumeroTeclas()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("tipoSwitch");
                nodoGadget.appendChild(nodoDatos);
                texto = documento.createTextNode(((TecladoSplit) gadget).getTipoSwitch());
                nodoDatos.appendChild(texto);
            } else if (gadget instanceof PlacaRaspberry) {
                nodoDatos = documento.createElement("ramGB");
                nodoGadget.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((PlacaRaspberry) gadget).getRamGB()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("numeroPuertosUSB");
                nodoGadget.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((PlacaRaspberry) gadget).getNumeroPuertosUSB()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos = documento.createElement("autonomiaHoras");
                nodoGadget.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((MultiherramientaDigital) gadget).getAutonomiaHoras()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("tieneNFC");
                nodoGadget.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((MultiherramientaDigital) gadget).isTieneNFC()));
                nodoDatos.appendChild(texto);
            }
        }
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

}

