package com.ieseljust.App;

import com.ieseljust.Model.Musician;

import java.util.Scanner;
import com.ieseljust.Controller.MusicDAO;
import com.ieseljust.Controller.OrquestaDAO;

import com.ieseljust.Model.Orchestra;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // while (true) {
            // System.out.println("Select an option:");

            // System.out.println("1. Create a new musician");
            // System.out.println("2. Show all musicians");
            // System.out.println("3. Show all orchests");
            // System.out.println("4. Create a new orchestra");
            // System.out.println("5. Exit");

            // int option = scanner.nextInt();

            // switch (option) {

            // case 1:
            // createNewMusician();

            // break;
            // case 2:
            // showMusicians();

            // break;
            // case 3:
            // showOrchests();

            // break;

            // case 4:
            // createNewOrchestra();

            // case 5:
            // System.out.println("Exiting the program.");
            // System.exit(0);
            // break;

            // default:
            // System.out.println("Invalid option. Please choose a valid option.");
            // }
            // }
            // }

        }

    }

    private static void createNewMusician() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the musician's name:");
            String musicianName = scanner.nextLine();
            System.out.println("what instrument do you pla :");
            String musicianInstrument = scanner.nextLine();

            MusicDAO musicDAO = new MusicDAO();

            Musician musician = new Musician(musicianName, musicianInstrument);
            musicDAO.insertMusic(musician);

            System.out.println("Musician created: " + musician.toString());
        }
    }

    private static void createNewOrchestra() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the orchestra's name:");
            String orchestraName = scanner.nextLine();

            OrquestaDAO orquestaDAO = new OrquestaDAO();
            Orchestra orchestra = new Orchestra(orchestraName);
            orquestaDAO.insertOrquesta(orchestra);

            System.out.println("Orchestra created: " + orchestra.toString());
        }
    }

    public static void showMusicians() {
        MusicDAO musicDAO = new MusicDAO();
        musicDAO.displayAllMusicians();
    }

    public static void showOrchests() {
        OrquestaDAO orquestaDAO = new OrquestaDAO();
        orquestaDAO.displayAllOrchestras();
    }

}
