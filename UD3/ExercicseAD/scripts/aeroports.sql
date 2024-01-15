-- Create the database
CREATE DATABASE aeroports;

-- Use the database
USE aeroports;

-- Create the tables
CREATE TABLE airports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE flights (
    id INT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(255),
    departure_airport_id INT,
    arrival_airport_id INT,
    departure_date DATETIME,
    arrival_date DATETIME,
    FOREIGN KEY (departure_airport_id) REFERENCES airports(id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airports(id)
);
