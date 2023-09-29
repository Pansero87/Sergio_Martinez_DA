package numberLines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveNumberLines {

	public static void main(String[] args) throws IOException {

		// Eixida i entrada
		BufferedReader fin;
		PrintWriter fout;

		// Comprobem Arguments
		if (args.length != 2) {
			System.out.println("Nombre d'arguments erroni. Sintaxi:\n numberLines fitxer eixida");
			return;
		}

		fin = new BufferedReader(new FileReader(args[0]));
		fout = new PrintWriter(new FileWriter(args[1]));

		String linea;
		while ((linea = fin.readLine()) != null) {
			// Cridem al métode pe eliminar els numeros
			linea = removeNumbers(linea);
			fout.println(linea);
		}

		// Tancar els fitxers
		fin.close();
		fout.close();

		System.out.println("Números eliminats");
	}

	// Métode per eliminar números
	private static String removeNumbers(String input) {
		// Utitzem replaceFirst amb na expresió per a levar els números al principi
		return input = input.replaceFirst("^\\d+\\s*", ""); // He trobat expressions per a llevar els números
		// No elimina el .

	}
}
