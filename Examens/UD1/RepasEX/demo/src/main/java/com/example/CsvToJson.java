package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvToJson {
	public static void main(String[] args) {

		// Path of the CSV file you want to read and convert
		String archivoCSV = "/home/sergio/Escritorio/ExAd/sales.csv";
		String archivoJSON = "/home/sergio/Escritorio/ExAd/marcadores.json";

		try {
			// Create a FileReader to read the CSV file
			FileReader fileReader = new FileReader(archivoCSV);

			// Create a BufferedReader to read the file line by line
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String linea;

			// Read CSV header (column names)
			String[] columnas = bufferedReader.readLine().split(",");

			// Create a JSONArray to store the JSON objects

			JSONArray jsonArray = new JSONArray();

			while ((linea = bufferedReader.readLine()) != null) {
				String[] valores = linea.split(",");
				JSONObject jsonObject = new JSONObject();

				// Create a JSON object for each row

				for (int i = 0; i < columnas.length; i++) {
					jsonObject.put(columnas[i], valores[i]);
				}

				// Add the JSON object to the JSONArray
				jsonArray.put(jsonObject);
			}

			// Create a container JSON object
			JSONObject jsonContainer = new JSONObject();
			jsonContainer.put("sales", jsonArray);

			// Write the JSON object to a file
			FileWriter fileWriter = new FileWriter(archivoJSON);
			fileWriter.write(jsonContainer.toString(4));
			fileWriter.close();

			System.out.println("CSV converted to JSON and saved to " + archivoJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
