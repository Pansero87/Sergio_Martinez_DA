package xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document; // No importar swing
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModulesXML {

	public Document OpenXMl(String name)
			throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {
		// Create an instance of DocumentBuilderFactory
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// Using the DocumentBuilderFactory instance we create a
		// DocumentBuilder
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// And we use DocumentBuilder's "parse" method to get the documentAS
		Document doc = dBuilder.parse(new File(name));
		return doc;

	}

	public void printModules(Element root) {
		NodeList moduls = root.getElementsByTagName("modul");

		for (int i = 0; i < moduls.getLength(); i++) {
			Element el = (Element) moduls.item(i);

			System.out.println(el.getNodeName() + " " + (i + 1));

			// And we show the value of the different tags
			System.out.println("Nom: " + el.getElementsByTagName("nom").item(0).getFirstChild().getNodeValue());
			System.out.println("Hores: " + el.getElementsByTagName("hores").item(0).getFirstChild().getNodeValue());
			System.out.println(
					"Qualificació: " + el.getElementsByTagName("qualificacio").item(0).getFirstChild().getNodeValue());
			System.out.println();

		}

	}

	public static void main(String[] args)
			throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Use: java ReadXML <name_of_file.xml>");
			return;
		}

		ModulesXML dom = new ModulesXML();
		Document doc = dom.OpenXMl(args[0]);
		Element root = doc.getDocumentElement();

		dom.printModules(root);

	}
}
