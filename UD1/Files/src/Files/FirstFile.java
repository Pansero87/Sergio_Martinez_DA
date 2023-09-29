package Files;

import java.io.File;

public class FirstFile {

	public static void main(String[] args) {
		// Relative Paths
		File newfile = new File("FirstFile.txt");

		// Absolute Paths
		File newfile1 = new File("/home/sergio/Escritorio/Files/test.txt");

		solvedExercise();

	}

	public static void solvedExercise() {
		String ruta = "/home/sergio/Escritorio/Files/test.txt";
		File f = new File(ruta);

		// Comprobem que existica el fitxer
		if (f.exists()) {
			if (f.isFile()) {
				System.out.println("El tamaño es de " + f.length());
				System.out.println("Puede ejecutarse: " + f.canExecute());
				System.out.println("Puede leerse: " + f.canRead());
				System.out.println("Puede escribirse: " + f.canWrite());
			} else {
				// Si es un directori guarda el nom en un array
				String[] losArchivos = f.list();
				System.out.println("El directorio " + ruta + " contiene:");
				for (String archivo : losArchivos) {
					System.out.println("\t" + archivo);
				}
			}

		} else {
			System.out.println("El fichero o ruta no existe");
		}

	}

}
