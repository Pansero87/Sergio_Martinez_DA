package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.json.JSONObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.StringWriter;

public class XmlToJsonConverter {

		public static void main(String[] args) {
			try {
				// Ruta del archivo XML que deseas convertir
				String archivoXML = "/ruta/al/archivo.xml";

				// Ruta del archivo JSON de salida
				String archivoJSON = "/ruta/de/salida/archivo.json";

				// Crea un DocumentBuilderFactory
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				// Lee el archivo XML en un objeto Document
				Document doc = builder.parse(new File(archivoXML));

				// Convierte el documento XML a JSON
				String json = convertDocumentToJson(doc);

				// Escribe el JSON resultante en un archivo
				writeJsonToFile(json, archivoJSON);

				System.out.println("XML convertido a JSON y guardado en " + archivoJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String convertDocumentToJson(Document doc) throws Exception {
			Element root = doc.getDocumentElement();
			return elementToJson(root).toString();
		}

		private static JSONObject elementToJson(Element element) {
			JSONObject json = new JSONObject();
			NodeList children = element.getChildNodes();

			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);

				if (child instanceof Element) {
					Element childElement = (Element) child;
					String nodeName = childElement.getNodeName();
					Object nodeValue = "";

					if (childElement.hasChildNodes()) {
						// Si el elemento tiene hijos, recursivamente convierte los hijos a JSON
						nodeValue = elementToJson(childElement);
					} else {
						// Si el elemento no tiene hijos, toma su texto como valor
						nodeValue = childElement.getTextContent();
					}

					// Agrega el par nombre-valor al objeto JSON
					json.put(nodeName, nodeValue);
				}
			}

			return json;
		}

		private static void writeJsonToFile(String json, String filePath) {
			try (FileWriter fileWriter = new FileWriter(filePath)) {
				fileWriter.write(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

