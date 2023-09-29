package json;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import json.Modul;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main programa = new Main();

		// fill the ArrayList
		programa.creaCurs();

		JSONObject cursJSON = programa.creaJSON();

		programa.escriuJSON("MisModulos.json", cursJSON);

	}

	private ArrayList Curs;

	private void creaCurs() {
		// Aquest métode inicializa l'objecte "Curs" de la classe JSONLib
		// que no és més que un vector de mòduls

		this.Curs = new ArrayList();

		// Definim els vectors per inicialitzar dades
		String[] moduls = { "Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies",
				"Programació Multimédia i dispositiud mòbils", "Sistemes de Gestió Empresarial",
				"Empresa i iniciativa emprenedora" };
		int[] hores = { 6, 3, 6, 5, 5, 3 };
		double[] notes = { 8.45, 9.0, 8.0, 7.34, 8.2, 7.4 };

		// Recorrem els vectors, creant els objectes
		// de tipus Modul i guardant-los en Curs
		for (int i = 0; i < moduls.length; i++) {
			Modul m = new Modul(moduls[i], hores[i], notes[i]);
			this.Curs.add(m);
		}
	}

	private JSONObject creaJSON() {

		// root element "Curs"
		JSONObject curs = new JSONObject();

		// who is a JSONArray
		JSONArray jsarray = new JSONArray();

		// we populate the array with individual modules
		for (Modul m : this.Curs) {
			JSONObject modul_json = m.getModulJSON();
			jsarray.put(modul_json);
		}

		// Create the curs element with the array
		curs.put("curs", jsarray);

		return (curs);
	}

	private void escriuJSON(String filename, JSONObject jso) {
		try {
			FileWriter file = new FileWriter(filename);

			file.write(jso.toString(4)); // 4 són els espais d'indentació
			file.close();
		} catch (IOException e) {
			System.out.println("Error, no es pot crear el fitxer " + filename);
		}
	}

}
