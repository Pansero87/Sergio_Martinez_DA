package com.ieseljust.movies;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Public class Movie
public class Movie {
    // Private atributes
    private int id;
    private String title;
    private String year;
    private String runtime;
    private List<String> genres;
    private String director;
    private String actors;
    private String plot;
    private String posterUrl;

    // Constructor for the class
    public Movie(int id, String title, String year, String runtime, List<Object> list, String director, String actors,
            String plot, String posterUrl) {

        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.posterUrl = posterUrl;
    }

    /**
     * Converts the object to a comma-separated values (CSV) string.
     *
     * @return A string representation of the object in CSV format.
     */

    public String toCSV() {
        StringBuilder csv = new StringBuilder();
        csv.append(id).append(',')
                .append(title).append(',')
                .append(year).append(',')
                .append(runtime).append(',')
                .append(genres != null ? String.join("|", genres) : "").append(';')
                .append(director).append(',')
                .append(actors).append(',')
                .append(plot).append(',')
                .append(posterUrl);
        return csv.toString();
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * The main function of the program.
     *
     * @param args the command line arguments
     */

    /*
     * @author Sergio Martíenz Bataller
     */

    public static void main(String[] args) {

        // Use absolute paths to ensure the program works correctly.
        String fileName = "/home/sergio/Escritorio/Movies/moviesdb.json";

        String csvFileName = "/home/sergio/Escritorio/Movies/movies.csv";

        List<Movie> moviesArrayList = readJSON(fileName);

        // Print the movies on a console using the printMovies method
        printMovies(moviesArrayList);

        saveToCSV(moviesArrayList, csvFileName); // Call the saveToCSV function
    }

    /**
     * Reads a JSON file and stores the data in an ArrayList of Movie objects.
     *
     * @param fileName the name of the JSON file to be read
     * @return the ArrayList of Movie objects containing the data from the JSON file
     */
    public static List<Movie> readJSON(String fileName) {
        List<Movie> moviesList = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            JSONObject json = new JSONObject(content);

            if (json.has("movies")) {
                JSONArray moviesArray = json.getJSONArray("movies");

                for (int i = 0; i < moviesArray.length(); i++) {
                    JSONObject movieObject = moviesArray.getJSONObject(i);
                    Movie movie = new Movie(
                            movieObject.getInt("id"),
                            movieObject.getString("title"),
                            movieObject.getString("year"),
                            movieObject.getString("runtime"),
                            movieObject.getJSONArray("genres").toList(),
                            movieObject.getString("director"),
                            movieObject.getString("actors"),
                            movieObject.getString("plot"),
                            movieObject.getString("posterUrl"));
                    moviesList.add(movie);

                }

            } else {
                System.err.println("The JSON file does not have the expected format.");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return moviesList;
    }

    /**
     * Prints the movies read from the JSON file.
     *
     * @param moviesList the list of movies to be printed
     */
    public static void printMovies(List<Movie> moviesList) {
        System.out.println("Movies read from the JSON file:");
        for (Movie movie : moviesList) {
            System.out.println("Movie ID: " + movie.getId());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Year: " + movie.getYear());
            System.out.println("Runtime: " + movie.getRuntime());
            System.out.println("Genres: " + movie.getGenres());
            System.out.println("Director: " + movie.getDirector());
            System.out.println("Actors: " + movie.getActors());
            System.out.println("Plot: " + movie.getPlot());
            System.out.println("Poster URL: " + movie.getPosterUrl());
            System.out.println();
        }

    }

    /**
     * Saves the list of movies to a CSV file.
     *
     * @param moviesList the list of movies to be saved
     * @param fileName   the name of the CSV file
     */

    public static void saveToCSV(List<Movie> moviesList, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write the header
            writer.write("ID,Title,Year,Runtime,Genres,Director,Actors,Plot,Poster URL\n");

            for (Movie movie : moviesList) {
                // Convert the Movie object to CSV format and write to the file
                writer.write(movie.toCSV() + "\n");
            }

            System.out.println("Movies saved to CSV file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
