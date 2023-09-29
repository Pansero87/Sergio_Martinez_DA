package xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXML {

    public static void main(String[] args) {
        try {
            String desktopPath = System.getProperty("user.home") + "/Escritorio/";
            String xmlFilePath = desktopPath + "Moduls.xml";
            System.out.println("Reading the File XML: " + xmlFilePath);

            // Crear un DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear un DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leer el documento XML
            Document document = builder.parse(xmlFilePath);
            System.out.println("File XML readed correctly.\n");

            // Obtener la raíz del documento
            Element curso = document.getDocumentElement();

            // Obtener una lista de nodos "modul"
            NodeList modulos = curso.getElementsByTagName("modul");

            // Recorrer la lista de módulos
            for (int i = 0; i < modulos.getLength(); i++) {
                Node modulo = modulos.item(i);

                if (modulo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) modulo;

                    // Obtener el nombre del módulo
                    String nombre = elemento.getElementsByTagName("nom").item(0).getTextContent();

                    // Obtener las horas del módulo
                    String horas = elemento.getElementsByTagName("hores").item(0).getTextContent();

                    // Obtener la calificación del módulo
                    String calificacion = elemento.getElementsByTagName("qualificacio").item(0).getTextContent();

                    System.out.println("Módulo: " + nombre);
                    System.out.println("Horas: " + horas);
                    System.out.println("Calificación: " + calificacion);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
