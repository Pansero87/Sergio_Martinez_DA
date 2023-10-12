package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RedJSONanidated {
	public static void main(String[] args) {
		// Ruta del archivo JSON que deseas leer
		String archivoJSON = "/home/sergio/Escritorio/ExAd/pedidos.json";

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

			// Crear un objeto JSONObject a partir del archivo JSON
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());

			// Acceder al arreglo "PEDIDOS"
			JSONArray pedidos = jsonObject.getJSONArray("PEDIDOS");

			// Iterar a través de los pedidos
			for (int i = 0; i < pedidos.length(); i++) {
				JSONObject pedido = pedidos.getJSONObject(i);

				// Acceder a los campos dentro de cada pedido
				System.out.println("Pedido " + (i + 1));
				String codigo = pedido.getString("codigo");
				String descrip = pedido.getString("descrip");
				String precio = pedido.getString("precio");
				String cantidad = pedido.getString("cantidad");
				String ubica = pedido.getString("ubica");

				System.out.println("Código: " + codigo);
				System.out.println("Descripción: " + descrip);
				System.out.println("Precio: " + precio);
				System.out.println("Cantidad: " + cantidad);
				System.out.println("Ubicación: " + ubica);

				if (pedido.has("PROMOS")) {
					JSONArray promos = pedido.getJSONArray("PROMOS");
					for (int j = 0; j < promos.length(); j++) {
						JSONObject promo = promos.getJSONObject(j);
						String promocion = promo.getString("promocion");
						String prueba = promo.getString("prueba");
						String prueba2 = promo.getString("prueba2");

						System.out.println("Promoción: " + promocion);
						System.out.println("Prueba: " + prueba);
						System.out.println("Prueba2: " + prueba2);
					}
				}

			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
