package numberLines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NumberLines {

	public static void main(String[] args) throws Exception {

		// Input and output
		BufferedReader fin;
		PrintWriter fout;

		// Line Counter
		int num_linia;

		// Readde line
		String linia;

		// Check args
		if (args.length != 2) {
			System.out.println("Nombre d'arguments erroni. Sintaxi:\n numberLines fitxer eixida");
			return;
		}
		// Create decorators
		fin = new BufferedReader(new FileReader(args[0]));
		fout = new PrintWriter(new FileWriter(args[1]));

		num_linia = 1;

		do {
			linia = fin.readLine();
			if (linia != null) {
				fout.println(num_linia + ". " + linia);
			}
			num_linia++;
		} while (linia != null);

		System.out.println("Run succes");
		// Close all
		fin.close();
		fout.close();

	}

}