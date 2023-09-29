package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadNewXml {
    public static void main(String[] args) {
        try {
            File inputFile = new File("tes1t.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("Modulo");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element modulo = (Element) nodeList.item(i);
                String nombre = modulo.getElementsByTagName("Nombre").item(0).getTextContent();
                String horas = modulo.getElementsByTagName("Horas").item(0).getTextContent();
                String calificacion = modulo.getElementsByTagName("Calificacion").item(0).getTextContent();

                System.out.println("Módulo " + (i + 1) + ":");
                System.out.println("Nombre: " + nombre);
                System.out.println("Horas: " + horas);
                System.out.println("Calificación: " + calificacion);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
