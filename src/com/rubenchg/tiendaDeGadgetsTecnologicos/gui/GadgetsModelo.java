package com.rubenchg.tiendaDeGadgetsTecnologicos.gui;

import com.rubenchg.tiendaDeGadgetsTecnologicos.base.*;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

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
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.transform.TransformerException;

/**
 * Gestiona la lista de gadgets y su importación y exportación XML.
 */
public class GadgetsModelo {
    private ArrayList<Gadget> listaGadgets = new ArrayList<>();

    public ArrayList<Gadget> obtenerGadgets() {
        return listaGadgets;
    }

    /** Comprueba si ya hay un artículo con ese código. */
    public boolean existeCodigo(String codigo){
        for (Gadget gadget : listaGadgets) {
            if (gadget.getCodigo().equals(codigo)){
                return true;
            }
        }

        return false;
    }

    /** Elimina de la lista el artículo indicado. */
    public boolean eliminarGadget(Gadget gadget) {
        return listaGadgets.remove(gadget);
    }

    /** Añade un teclado split a la lista. */
    public void altaTecladoSplit(String codigo, String nombre, String marca, double precio, LocalDate fechaAlta,
                                 String descripcion, int numeroTeclas, String tipoSwitch){
        TecladoSplit nuevoTeclado = new TecladoSplit(codigo, nombre, marca, precio, fechaAlta, descripcion, numeroTeclas, tipoSwitch);
        listaGadgets.add(nuevoTeclado);
    }

    /** Añade una placa Raspberry a la lista. */
    public void altaPlacaRaspberry(String codigo, String nombre, String marca, double precio, LocalDate fechaAlta,
                                   String descripcion, int ramGB, int numeroPuertosUSB) {
         listaGadgets.add(new PlacaRaspberry(codigo, nombre, marca, precio, fechaAlta, descripcion, ramGB, numeroPuertosUSB));
    }

    /** Añade una multiherramienta digital a la lista. */
    public void altaMultiherramienta(String codigo, String nombre, String marca, double precio, LocalDate fechaAlta,
                                     String descripcion, int autonomiaHoras, boolean tieneNFC){
        listaGadgets.add(new MultiherramientaDigital(codigo, nombre, marca, precio, fechaAlta, descripcion, autonomiaHoras, tieneNFC));
    }
    /** Guarda los artículos en el archivo XML indicado. */
    public void exportar(File fichero) throws ParserConfigurationException, TransformerException{
        // Prepara las herramientas para crear el documento XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Crea el documento en memoria con <xml> como raíz.
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);
        // Añade <Gadgets> dentro de la raíz <xml>.
        Element raiz = documento.createElement("Gadgets");
        documento.getDocumentElement().appendChild(raiz);

        // Recorre los artículos y crea sus etiquetas XML.
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
        // Origen: los datos que se van a leer,  documento XML en memoria.
        // Source es una interfaz (tipo general), DOMSource es una clase que la implementa.
        // Simil con herencia: Gadget articulo = new TecladoSplit();
        Source source = new DOMSource(documento);

        // Destino: el File indica en qué archivo se escribirá el XML.
        // Result es una interfaz (tipo general), StreamResult es la clase concreta del destino.
        Result resultado = new StreamResult(fichero);

        // newInstance() obtiene la fábrica; newTransformer() obtiene la herramienta.
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        // Aquí se escribe el documento en el archivo: se crea o se sobrescribe.
        // Va fuera del for para guardar el árbol completo con todos los gadgets.
        transformer.transform(source, resultado);
    }
    /** Lee el XML y sustituye la lista de artículos. */
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Lee el archivo XML y carga su árbol en memoria.
        Document documento = builder.parse(fichero);

        // La importación sustituye la lista anterior.
        listaGadgets = new ArrayList<>();

        // Recoge las etiquetas del XML, sin los nodos de texto.
        NodeList listaElementos = documento.getElementsByTagName("*");
        for (int i = 0; i < listaElementos.getLength(); i++) {
            // item(i) devuelve un Node, se utiliza como Element.
            Element nodoGadget = (Element) listaElementos.item(i);
            Gadget nuevoGadget = null;

            // Reconoce el tipo de artículo y rellena sus campos propios.
            if (nodoGadget.getTagName().equals("TecladoSplit")) {
                TecladoSplit nuevoTeclado = new TecladoSplit();
                // Busca numeroTeclas, lee su texto y lo convierte en un entero.
                nuevoTeclado.setNumeroTeclas(Integer.parseInt(nodoGadget.getElementsByTagName("numeroTeclas").item(0).getTextContent()));
                nuevoTeclado.setTipoSwitch(nodoGadget.getElementsByTagName("tipoSwitch").item(0).getTextContent());
                // Las dos variables apuntan al mismo teclado.
                nuevoGadget = nuevoTeclado;

            } else if (nodoGadget.getTagName().equals("PlacaRaspberry")) {
                PlacaRaspberry nuevaPlaca = new PlacaRaspberry();
                nuevaPlaca.setRamGB(Integer.parseInt(nodoGadget.getElementsByTagName("ramGB").item(0).getTextContent()));
                nuevaPlaca.setNumeroPuertosUSB(Integer.parseInt(nodoGadget.getElementsByTagName("numeroPuertosUSB").item(0).getTextContent()));
                nuevoGadget = nuevaPlaca;

            } else if (nodoGadget.getTagName().equals("MultiherramientaDigital")) {
                MultiherramientaDigital nuevaMultiherramienta = new MultiherramientaDigital();
                nuevaMultiherramienta.setAutonomiaHoras(Integer.parseInt(nodoGadget.getElementsByTagName("autonomiaHoras").item(0).getTextContent()));
                nuevaMultiherramienta.setTieneNFC(nodoGadget.getElementsByTagName("tieneNFC").item(0).getTextContent().equals("true"));
                nuevoGadget = nuevaMultiherramienta;
            }

            // Los artículos reciben los campos comunes.
            if (nuevoGadget != null) {
                nuevoGadget.setCodigo(nodoGadget.getElementsByTagName("codigo").item(0).getTextContent());
                nuevoGadget.setNombre(nodoGadget.getElementsByTagName("nombre").item(0).getTextContent());
                nuevoGadget.setMarca(nodoGadget.getElementsByTagName("marca").item(0).getTextContent());
                nuevoGadget.setPrecio(Double.parseDouble(nodoGadget.getElementsByTagName("precio").item(0).getTextContent()));
                nuevoGadget.setFechaAlta(LocalDate.parse(nodoGadget.getElementsByTagName("fechaAlta").item(0).getTextContent()));
                nuevoGadget.setDescripcion(nodoGadget.getElementsByTagName("descripcion").item(0).getTextContent());

                // Añade el artículo completo a la lista de la aplicación.
                listaGadgets.add(nuevoGadget);
            }
        }

    }

}

