package com.ieseljust;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageConnections {

    String server;
    String port;
    String user;
    String pass;
    String dbname;

    public ManageConnections() {

    }

    public ManageConnections(String server, String port, String user, String pass, String dbname) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;

    }

    /**
     * Connects to the database and returns a Connection object.
     *
     * @return a Connection object representing the connection to the database
     */
    public Connection connectDatabase() {

        String conn = "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.dbname
                + "?useUnicode=false&allowMultiQueries=true&characterEncoding=UTF-8&" + "user=" + this.user
                + "&password=" + this.pass;

        try {
            Connection connection = DriverManager.getConnection(conn);
            System.out.println("Connected to database: " + this.dbname);
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
