package com.example;

import org.json.JSONObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JasonToXmlLow {
	public static void main(String[] args) {
		// Path of the JSON file you want to read and convert
		String fileJSON = "/home/sergio/Escritorio/ExAd/aamir-khan.json";
		String fileXML = "/home/sergio/Escritorio/ExAd/aamir-khan.xml";

		try {

			// Path of the JSON file you want to read and convert
			FileReader fileReader = new FileReader(fileJSON);

			// Create a BufferedReader to read the file line by line
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuilder stringBuilder = new StringBuilder();
			String linea;

			while ((linea = bufferedReader.readLine()) != null) {
				stringBuilder.append(linea);
			}

			// Create a JSONObject from the contents of the JSON file
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());

			// Create a Document XML
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// Create a root element
			Element rootElement = doc.createElement("person");
			doc.appendChild(rootElement);

			// Add elements to XML document
			rootElement.appendChild(createElement(doc, "name", jsonObject.getString("name")));
			rootElement.appendChild(createElement(doc, "birthname", jsonObject.getString("birthname")));
			rootElement.appendChild(createElement(doc, "birthdate", jsonObject.getString("birthdate")));
			rootElement.appendChild(createElement(doc, "birthplace", jsonObject.getString("birthplace")));

			// Write the XML document to a file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileWriter(fileXML));
			transformer.transform(source, result);

			System.out.println("JSON converted  XML and saved to " + fileXML);
		} catch (IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private static Element createElement(Document doc, String name, String value) {
		Element element = doc.createElement(name);
		element.appendChild(doc.createTextNode(value));
		return element;
	}
}
