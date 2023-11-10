package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class FIndJSON {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        System.out.println("Enter the directory path to search in:");
        String directoryToFind = read.nextLine();
        // System.out.println("Year:");
        // int year = read.nextInt();

        File directory = new File("/home/sergio/Escritorio/jsonmc/" + directoryToFind);
        // File directoryMovies = new File("/home/sergio/Escritorio/jsonmc/" +
        // directoryToFind + "/" + year);

        findWithOneArg(directory);
    }

    public static void findWithOneArg(File directory) {
        Scanner read = new Scanner(System.in);

        if (directory.exists() && directory.isDirectory()) {
            System.out.println("The directory exists");
            List<JSONObject> jsonList = new ArrayList<JSONObject>();
            findJSON(directory, jsonList);
            System.out.println("Wich parameter: name, birthname, birthdate, birthplace");
            String parameter = read.nextLine();
            for (JSONObject json : jsonList) {
                System.out.print(json.opt(parameter) + "\t");
                // System.out.print(json.opt("birthdate") + "\n");
                System.out.println();

            }

        } else {
            System.out.println("The directory does not exist");
        }
    }

    public static void findJSON(File directory, List<JSONObject> jsonList) {

        if (directory != null) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        // Lee el contenido del archivo JSON
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            content.append(line);
                        }
                        br.close();

                        // Convierte el contenido en un objeto JSON y agrega a la lista
                        JSONObject jsonObject = new JSONObject(content.toString());
                        jsonList.add(jsonObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }
}