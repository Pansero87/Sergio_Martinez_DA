package com.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadJSON {
	public static void main(String[] args) {
		// Ruta del archivo JSON que deseas leer
		String archivoJSON = "/home/sergio/Escritorio/ExAd/aamir-khan.json";
		String archivoXML = "/home/sergio/Escritorio/ExAd/aamir-khan.xml";

		try {
			// Crear un FileReader para leer el archivo
			FileReader fileReader = new FileReader(archivoJSON);

			// Crear un BufferedReader para leer el archivo línea por línea
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuilder stringBuilder = new StringBuilder();
			String linea;

			while ((linea = bufferedReader.readLine()) != null) {
				stringBuilder.append(linea);
			}

			// Crear un objeto JSONObject a partir del contenido del archivo
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());

			// Acceder a los datos del JSON
			String name = jsonObject.getString("name");
			String birthname = jsonObject.getString("birthname");
			String birthdate = jsonObject.getString("birthdate");
			String birthplace = jsonObject.getString("birthplace");

			System.out.println("Name: " + name);
			System.out.println("NameE: " + birthname);
			System.out.println("Birthdate: " + birthdate);
			System.out.println("Birthplace: " + birthplace);

			// Puedes seguir accediendo a otros campos del JSON según sea necesario
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
