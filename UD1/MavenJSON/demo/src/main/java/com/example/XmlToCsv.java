package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XmlToCsv {
    public static void main(String[] args) {

        String xmlFilePath = "/home/sergio/Documentos/AD/Sergio_Martinez_DA/UD1/MavenJSON/demo/pedidos.xml";
        String csvFilePath = "/home/sergio/Documentos/AD/Sergio_Martinez_DA/UD1/MavenJSON/demo/test.csv";
        try {
            // Parsear el archivo XML y obtener una lista de filas
            List<String[]> rows = parseXmlFile(xmlFilePath);
            // Escribir las filas en el archivo CSV de salida
            writeRowsToCsv(rows, csvFilePath);
            System.out.println("Archivo XML convertido a CSV con éxito y guardado en " + csvFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> parseXmlFile(String filePath) throws Exception {
        List<String[]> rows = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                NodeList childNodes = element.getChildNodes();
                List<String> values = new ArrayList<>();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        values.add(childNode.getTextContent());
                    }
                }
                rows.add(values.toArray(new String[0]));
            }
        }
        return rows;
    }

    private static void writeRowsToCsv(List<String[]> rows, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : rows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }
}