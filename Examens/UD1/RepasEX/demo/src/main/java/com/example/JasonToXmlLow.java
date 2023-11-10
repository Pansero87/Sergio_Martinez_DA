package com.example;

import org.json.JSONArray;
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
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JasonToXmlLow {
	public static void main(String[] args) {
		// Path of the JSON file you want to read and convert
		String fileJSON = "/home/sergio/Escritorio/ExAd/goldfinger.json";
		String fileXML = "/home/sergio/Escritorio/ExAd/gold.xml";

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
			Element rootElement = doc.createElement("movies");
			doc.appendChild(rootElement);

			// Add elements to XML document
			rootElement.appendChild(createElement(doc, "name", jsonObject.getString("name")));
			rootElement.appendChild(createElement(doc, "year", String.valueOf(jsonObject.getInt("year"))));

			// rootElement.appendChild(createElement(doc, "year",
			// jsonObject.getString("year")));
			// rootElement.appendChild(createElement(doc, "runtine",
			// jsonObject.getString("runtime")));

			rootElement.appendChild(createElement(doc, "runtime", String.valueOf(jsonObject.getInt("runtime"))));

			JSONArray categoriesArray = jsonObject.getJSONArray("categories");
			StringBuilder categoriesStringBuilder = new StringBuilder();

			for (int i = 0; i < categoriesArray.length(); i++) {
				categoriesStringBuilder.append(categoriesArray.getString(i));
				if (i < categoriesArray.length() - 1) {
					categoriesStringBuilder.append(", ");
				}
			}

			rootElement.appendChild(createElement(doc, "categories", categoriesStringBuilder.toString()));

			// rootElement.appendChild(createElement(doc, "categories",
			// jsonObject.getString("categories")));
			rootElement.appendChild(createElement(doc, "release-date", jsonObject.getString("release-date")));

			// rootElement.appendChild(createElement(doc, "release-date",
			// String.valueOf(jsonObject.getInt("release-date"))));

			rootElement.appendChild(createElement(doc, "director", jsonObject.getString("director")));
			// rootElement.appendChild(createElement(doc, "writer",
			// jsonObject.getString("writer")));

			JSONArray writerArray = jsonObject.getJSONArray("categories");
			StringBuilder writerStringBuilder = new StringBuilder();

			for (int i = 0; i < writerArray.length(); i++) {
				writerStringBuilder.append(writerArray.getString(i));
				if (i < writerArray.length() - 1) {
					writerStringBuilder.append(", ");
				}
			}

			rootElement.appendChild(createElement(doc, "writer", writerStringBuilder.toString()));

			// rootElement.appendChild(createElement(doc, "actors",
			// jsonObject.getString("actors")));

			JSONArray actorsArray = jsonObject.getJSONArray("categories");
			StringBuilder actorsStringBuilder = new StringBuilder();

			for (int i = 0; i < actorsArray.length(); i++) {
				writerStringBuilder.append(actorsArray.getString(i));
				if (i < actorsArray.length() - 1) {
					actorsStringBuilder.append(", ");
				}
			}

			rootElement.appendChild(createElement(doc, "writer", actorsStringBuilder.toString()));

			rootElement.appendChild(createElement(doc, "storyline", jsonObject.getString("storyline")));

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
