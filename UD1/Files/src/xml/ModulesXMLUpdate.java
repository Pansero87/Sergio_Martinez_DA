package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class fileXML {
	/**
	 * @author Sergio Martínez Bataller
	 */

	/**
	 * Opens an XML file and returns its Document representation.
	 * 
	 * @param name The name of the XML file to open.
	 * @return A Document object representing the XML content.
	 * @throws IOException                  If there is an IO error.
	 * @throws SAXException                 If there is a SAX error.
	 * @throws ParserConfigurationException If there is a parser configuration
	 *                                      error.
	 * @throws FileNotFoundException        If the file is not found.
	 */

	public Document OpenXML(String name)
			throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

		// Create an instance of DocumentBuilderFactory
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document dom = null;

		try {

			dom = dBuilder.parse(name);

		} catch (IOException e) {

		}
		return dom;
	}

	/**
	 * Prints module information from the given XML Document.
	 * 
	 * @param root The root element of the XML Document.
	 */
	public void printModules(Element root) {
		NodeList modules = root.getElementsByTagName("module");
		for (int i = 0; i < modules.getLength(); i++) {
			Element e = (Element) modules.item(i);

			System.out.println("Nom Modul: " + e.getElementsByTagName("name").item(0).getTextContent());
			System.out.println("Hores: " + e.getElementsByTagName("hours").item(0).getTextContent());
			System.out.println("Alumne: " + e.getElementsByTagName("student").item(0).getTextContent());

			System.out.println("");
		}

		// I put this out of the loop, because it gave problems
		Element timeElement = (Element) root.getElementsByTagName("time").item(0);
		System.out.println("Time: " + timeElement.getTextContent());
	}

	/**
	 * Creates an XML Document with module information and writes it to a file.
	 * 
	 * @param file The File object to write to.
	 * @throws SAXException                 If there is a SAX error.
	 * @throws ParserConfigurationException If there is a parser configuration
	 *                                      error.
	 * @throws TransformerException         If there is a transformer error.
	 * @throws IOException                  If there is an IO error.
	 */

	public void writeModules(File file) throws SAXException, ParserConfigurationException,
			TransformerConfigurationException, FileNotFoundException, TransformerException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		// Create a new XML document
		Document doc = dBuilder.newDocument();
		try {

			// create a new XML element called "course" using createElement() and set it as
			// the root element of the XML document (doc) using appendChild()
			Element rootElement = doc.createElement("course");
			doc.appendChild(rootElement);

			// Create and add modules
			addModule(doc, rootElement, "Accés a dades", 8, "Sergio");
			addModule(doc, rootElement, "PMDM", 8, "Sergio");
			addModule(doc, rootElement, "SGE", 6, "Sergio");

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Element currentTime = doc.createElement("time");
			currentTime.appendChild(doc.createTextNode(dtf.format(now)));
			rootElement.appendChild(currentTime);

		} catch (Exception e) {
			System.out.println(e);
		}

		saveToFile(doc, file);
	}

	/**
	 * Adds a new course module to an XML document.
	 *
	 * @param doc           The XML document to which the module will be added.
	 * @param parentElement The parent element to which the module element will be
	 *                      appended.
	 * @param moduleName    The name of the course module.
	 * @param moduleHours   The number of hours for the course module.
	 */
	private void addModule(Document doc, Element parentElement, String moduleName, int moduleHours, String student) {
		// Creates a new XML element called "module" to represent a course module
		Element module = doc.createElement("module");

		// Creating Child Elements for the Module: name, hours
		Element name = doc.createElement("name");
		Element hours = doc.createElement("hours");
		Element nameStudent = doc.createElement("student");

		name.appendChild(doc.createTextNode(moduleName));
		hours.appendChild(doc.createTextNode(Integer.toString(moduleHours)));
		nameStudent.appendChild(doc.createTextNode(student));

		// Appending Child Elements to the "module" Element:
		module.appendChild(name);
		module.appendChild(hours);
		module.appendChild(nameStudent);

		// Appending the "module" Element to the Parent Element
		parentElement.appendChild(module);
	}

	/**
	 * Saves an XML Document to a file.
	 * 
	 * @param doc  The XML Document to save.
	 * @param file The File object to save to.
	 * @throws TransformerException If there is a transformer error.
	 * @throws IOException          If there is an IO error.
	 */

	public void saveToFile(Document doc, File file) {

		Transformer trans;
		try {
			trans = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(file));
			trans.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

public class ModulesXMLUpdate {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException,
			FileNotFoundException, TransformerException {

		// An instance of the fileXML class, named dom, is created. This instance will
		// be used to interact with XML files and modules.
		fileXML dom = new fileXML();

		// If the first arg is read, create a new Document object, calls method OpenXML.
		// This methos ies expected to open and parse the XML File and return a document
		// representation
		if (args[0].equalsIgnoreCase("read")) {
			Document doc = dom.OpenXML(args[1]);
			dom.printModules(doc.getDocumentElement());
		}
		// If the arguments is write, this line creates a new File. If files exists,
		// call the method writeModules
		if (args[0].equalsIgnoreCase("write")) {
			File file = new File(args[1]);
			if (!file.exists()) {
				file.createNewFile();
			}
			dom.writeModules(file);
		}
	}

}