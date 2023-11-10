package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONtoCSVlow {
    public static void main(String[] args) {
        // Ruta del archivo JSON que deseas leer y convertir
        String archivoJSON = "/home/sergio/Escritorio/ExAd/marcador.json";
        String archivoCSV = "/home/sergio/Escritorio/ExAd/marcador.csv";

        try {
            // Crear un FileReader para leer el archivo JSON
            FileReader fileReader = new FileReader(archivoJSON);

            // Crear un BufferedReader para leer el archivo línea por línea
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                stringBuilder.append(linea);
            }

            // Crear un objeto JSONObject a partir del contenido del archivo JSON
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            // Obtener el arreglo de marcadores
            JSONArray marcadoresArray = jsonObject.getJSONArray("marcadores");

            // Crear un FileWriter para escribir en el archivo CSV
            FileWriter fileWriter = new FileWriter(archivoCSV);

            // Escribir el encabezado CSV (nombres de las columnas)
            fileWriter.write("latitude,longitude,city,description\n");

            // Escribir los datos del JSON en el archivo CSV
            for (int i = 0; i < marcadoresArray.length(); i++) {
                JSONObject marcador = marcadoresArray.getJSONObject(i);
                StringBuilder row = new StringBuilder();
                row.append(marcador.getDouble("latitude")).append(",");
                row.append(marcador.getDouble("longitude")).append(",");
                row.append(marcador.getString("city")).append(",");
                row.append(marcador.getString("description")).append("\n");
                fileWriter.write(row.toString());
            }

            // Cerrar el FileWriter
            fileWriter.close();

            System.out.println("JSON convertido a CSV y guardado en " + archivoCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
