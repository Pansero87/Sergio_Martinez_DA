package Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;

public class FileCopy {
	/*
	 * Class to test FileInputStream and FileOutputStream.
	 * 
	 * Copy byte to byte of files
	 * 
	 * Sintaxi: FileCopy sourceFile destinationFile.
	 */

	public static void main(String[] args) {

		// copiaFitxersBuffer();

		// Byte readed form source
		int bytes;
		// Bytes (effectively) writen to dest
		long bytesCopied = 0;

		// streams
		FileInputStream fis = null;
		FileOutputStream fos = null;

		// To provide information about source
		File f;

		// Are the argument ok?
		if (args.length != 2) {
			System.out.println("Nombre d'arguments erroni. Sintaxi:\n FileCopy fitxerOrigen fitxerdestí");
			return;

		}

		try {
			// Show source size
			f = new File(args[0]);
			System.out.println("Total: " + f.length() + " bytes");

			// Create streams
			fis = new FileInputStream(args[0]);
			fos = new FileOutputStream(args[1]);

			do {
				// Read one byte from source
				bytes = fis.read();
				// Write in destination
				if (bytes != -1) {
					fos.write(bytes);
					// Update number of bytes
					bytesCopied++;

					// Show progress (think alternatives as exercise)
					System.out.println("\rCopiats " + (bytesCopied - 1) + " bytes...");

				}
			} while (bytes != -1);
			System.out.println("Done it!");
		} catch (IOException exc) {
			System.out.println("Error d'entrada i eixida: " + exc);

		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException exc) {
				System.out.println("Error tancant el fitxer d'origen." + exc);
			}
			try {
				if (fos != null) {
					fos.close();
				}

			} catch (IOException exc) {
				System.out.println("Error tancant el fitxer destí " + exc);
			}
		}

	}

	public static void copiaFitxersBuffer() {

		// Byte readed form source
		int bytes;
		// Bytes (effectively) writen to dest
		long bytesCopied = 0;
		// Define buffer with 32 bytes
		byte[] buffer = new byte[32];

		// streams
		FileInputStream fis = null;
		FileOutputStream fos = null;

		// To provide information about source
		File f;

		// Are the argument ok?

		try {
			// Show source size
			f = new File("/home/sergio/Escritorio/Files/text1.tx");
			System.out.println("Total: " + f.length() + " bytes");

			// Create streams
			fis = new FileInputStream("/home/sergio/Escritorio/Files/text1.tx");
			fos = new FileOutputStream("/home/sergio/Escritorio/Files/text2.tx");

			while ((bytes = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytes);
				bytesCopied += bytes;
				System.out.println("\rCopied " + bytesCopied + " bytes...");

			}

		} catch (IOException exc) {
			System.out.println("Error d'entrada i eixida: " + exc);

		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException exc) {
				System.out.println("Error tancant el fitxer d'origen." + exc);
			}
			try {
				if (fos != null) {
					fos.close();
				}

			} catch (IOException exc) {
				System.out.println("Error tancant el fitxer destí " + exc);
			}
		}

	}

}
