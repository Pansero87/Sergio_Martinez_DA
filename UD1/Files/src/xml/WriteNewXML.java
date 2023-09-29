package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteNewXML {
    public static void main(String[] args) {
        try {
            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Crear un nuevo documento XML
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz "Curso"
            Element rootElement = doc.createElement("Curso");
            doc.appendChild(rootElement);

            // Crear varios módulos
            for (int i = 1; i <= 3; i++) {
                Element modulo = doc.createElement("Modulo");
                rootElement.appendChild(modulo);

                // Elemento "Nombre"
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode("Módulo " + i));
                modulo.appendChild(nombre);

                // Elemento "Horas"
                Element horas = doc.createElement("Horas");
                horas.appendChild(doc.createTextNode(Integer.toString(10 * i)));
                modulo.appendChild(horas);

                // Elemento "Calificacion"
                Element calificacion = doc.createElement("Calificacion");
                calificacion.appendChild(doc.createTextNode("Aprobado"));
                modulo.appendChild(calificacion);
            }

            // Transformar el documento en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            File xmlFile = new File("tes1t.xml");
            StreamResult result = new StreamResult(new FileOutputStream(xmlFile));

            // Guardar el archivo XML
            transformer.transform(source, result);

            System.out.println("Documento XML creado correctamente en 'curso.xml'.");

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
