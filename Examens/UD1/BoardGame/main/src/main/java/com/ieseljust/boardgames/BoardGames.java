package com.ieseljust.boardgames;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;

/*
 * @author Sergio Martinez Bataller
 */

public class BoardGames {

    /**
     * The main function for the Java program.
     *
     * @param args an array of command-line arguments
     * @return void
     */
    public static void main(String[] args) {
        try {
            // Path to the XML file
            File xmlFile = new File("/home/sergio/Escritorio/BoardGame/BoardGamesBGG.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList itemList = doc.getElementsByTagName("item");

            Scanner scanner = new Scanner(System.in);
            String userInput;

            // open the file to write the results
            FileWriter fileWriter = new FileWriter("/home/sergio/Escritorio/BoardGame/results.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (true) {
                System.out.print("Enter the name of the game to search (or 'quit' to exit): ");
                userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Bye Bye!");
                    break;
                }

                boolean gameFound = false;

                for (int i = 0; i < itemList.getLength(); i++) {
                    Node itemNode = itemList.item(i);
                    if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemElement = (Element) itemNode;
                        String name = itemElement.getElementsByTagName("name").item(0).getTextContent();

                        if (name.equalsIgnoreCase(userInput)) {
                            String objectID = itemElement.getAttribute("objectid");
                            String minPlayers = itemElement.getElementsByTagName("stats").item(0).getAttributes()
                                    .getNamedItem("minplayers").getTextContent();
                            String maxPlayers = itemElement.getElementsByTagName("stats").item(0).getAttributes()
                                    .getNamedItem("maxplayers").getTextContent();

                            System.out.println("Game found:");
                            System.out.println("Name: " + name);
                            System.out.println("ObjectID: " + objectID);
                            System.out.println("Minimum Players: " + minPlayers);
                            System.out.println("Maximum Players: " + maxPlayers);
                            gameFound = true;

                            // write the results to the file
                            bufferedWriter.write("Game found:\n");
                            bufferedWriter.write("Name: " + name + "\n");
                            bufferedWriter.write("ObjectID: " + objectID + "\n");
                            bufferedWriter.write("Minimum Players: " + minPlayers + "\n");
                            bufferedWriter.write("Maximum Players: " + maxPlayers + "\n");
                            bufferedWriter.write("\n");
                            break;
                        }
                    }
                }

                if (!gameFound) {
                    System.out.println("Game not found.");
                    bufferedWriter.write("Game not found for query: " + userInput + "\n\n");
                }
            }
            // close the BufferedWriter
            bufferedWriter.close();

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
