package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVtoXML {
    public static void main(String[] args) {

        String csvFilePath = "/home/sergio/Escritorio/ExAd/sales.csv";
        String xmlFilePath = "/home/sergio/Escritorio/ExAd/sales.xml";

        try {
            // Leer el contenido del archivo CSV
            String[] headers = readCsvHeader(csvFilePath);
            String[][] data = readCsvData(csvFilePath);
            // Escribir el contenido CSV en formato XML en el archivo XML de salida
            writeCsvToXml(headers, data, xmlFilePath);
            System.out.println("Archivo CSV convertido a XML con éxito y guardado en " + xmlFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] readCsvHeader(String filePath) throws IOException {
        String headerLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            headerLine = reader.readLine();
        }
        return headerLine.split(",");
    }

    private static String[][] readCsvData(String filePath) throws IOException {
        String[] lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Leer todas las líneas del archivo CSV
            lines = reader.lines().toArray(String[]::new);
        }
        // Crear una matriz para almacenar los datos CSV
        String[][] data = new String[lines.length - 1][];
        for (int i = 1; i < lines.length; i++) {
            data[i - 1] = lines[i].split(",");
        }
        return data;
    }

    private static void writeCsvToXml(String[] headers, String[][] data, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir la etiqueta de inicio del documento XML
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.newLine();
            writer.write("<sales>");
            writer.newLine();
            // Escribir los datos como elementos XML
            for (String[] row : data) {
                writer.write("  <records>");
                writer.newLine();
                for (int i = 0; i < headers.length; i++) {
                    writer.write("    <" + headers[i] + ">" + row[i] + "</" + headers[i] + ">");
                    writer.newLine();
                }
                writer.write("  </records>");
                writer.newLine();
            }
            // Escribir la etiqueta de cierre del documento XML
            writer.write("</sales>");
        }
    }
}