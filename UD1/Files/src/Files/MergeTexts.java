package Files;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MergeTexts {

	public static void main(String[] args) {

		File dir; // source dir

		// Collection of files form tht dir
		File[] files;

		// Readed characters
		int characters;

		String rutaRead = ("/home/sergio/Escritorio/Files");
		String rutaWrite = ("/home/sergio/Escritorio/Files/txt.1");

		// Input and Output Streams
		FileReader fr = null;
		FileWriter fW = null;

		try {
			// We get the list of Files
			dir = new File(rutaRead);
			files = dir.listFiles();

			fW = new FileWriter(rutaWrite);
			fW.close();

			// Re-open it
			fW = new FileWriter(rutaWrite, true);
			for (int i = 0; i < files.length; i++) {

				fr = new FileReader(rutaRead + "/" + files[i].getName());
				System.out.println("Merging " + rutaRead + "/" + files[i].getName());

				do {
					characters = fr.read();
					if (characters != -1) {
						fW.write(characters);

					}

				} while (characters != -1);
				fr.close();
			}
			fW.close();

		} catch (Exception exc) {
			// Catch all the exception (we coud improve it)
			System.out.println("Input/Output error: " + exc);
		}

	}

}
