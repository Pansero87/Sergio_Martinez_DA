package com.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONtoXML {
    public static void main(String[] args) {
        String archivoJSON = "/home/sergio/Escritorio/ExAd/pedidos.json";
        String archivoXML = "/home/sergio/Escritorio/ExAd/pedidos.xml";
        try {
            // Leer el archivo JSON
            FileReader fileReader = new FileReader(archivoJSON);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                stringBuilder.append(linea);
            }

            // Crear un objeto JSONObject a partir del archivo JSON
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            // Crear un objeto StringBuilder para construir el XML
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<PEDIDOS>\n");

            // Acceder al arreglo "PEDIDOS"
            JSONArray pedidos = jsonObject.getJSONArray("PEDIDOS");

            // Iterar a través de los pedidos
            for (int i = 0; i < pedidos.length(); i++) {
                JSONObject pedido = pedidos.getJSONObject(i);

                // Iniciar un nuevo pedido
                xmlBuilder.append("\t<Pedido>\n");

                // Acceder a los campos dentro de cada pedido
                String codigo = pedido.getString("codigo");
                agregarElementoXML(xmlBuilder, "codigo", codigo);

                String descrip = pedido.getString("descrip");
                agregarElementoXML(xmlBuilder, "descrip", descrip);

                String precio = pedido.getString("precio");
                agregarElementoXML(xmlBuilder, "precio", precio);

                String cantidad = pedido.getString("cantidad");
                agregarElementoXML(xmlBuilder, "cantidad", cantidad);

                String ubica = pedido.getString("ubica");
                agregarElementoXML(xmlBuilder, "ubica", ubica);

                if (pedido.has("PROMOS")) {
                    JSONArray promos = pedido.getJSONArray("PROMOS");

                    // Iniciar un nuevo elemento para las promociones
                    xmlBuilder.append("\t\t<PROMOS>\n");

                    for (int j = 0; j < promos.length(); j++) {
                        JSONObject promo = promos.getJSONObject(j);

                        String promocion = promo.getString("promocion");
                        agregarElementoXML(xmlBuilder, "promocion", promocion);

                        String prueba = promo.getString("prueba");
                        agregarElementoXML(xmlBuilder, "prueba", prueba);

                        String prueba2 = promo.getString("prueba2");
                        agregarElementoXML(xmlBuilder, "prueba2", prueba2);
                    }

                    // Cerrar el elemento de promociones
                    xmlBuilder.append("\t\t</PROMOS>\n");
                }

                // Cerrar el pedido
                xmlBuilder.append("\t</Pedido>\n");
            }

            // Cerrar el elemento raíz
            xmlBuilder.append("</PEDIDOS>");

            // Escribir el resultado en el archivo XML
            FileWriter fileWriter = new FileWriter(archivoXML);
            fileWriter.write(xmlBuilder.toString());
            fileWriter.close();

            System.out.println("Archivo XML generado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void agregarElementoXML(StringBuilder xmlBuilder, String nombre, String valor) {
        xmlBuilder.append("\t\t<").append(nombre).append(">").append(valor).append("</").append(nombre).append(">\n");
    }
}
