-- Create Database

DROP DATABASE IF EXISTS Library;
CREATE DATABASE Library;

-- Usar the database
USE Library;

-- Table: Books
CREATE TABLE Books (
    ID_Book INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255),
    Author VARCHAR(255),
    Publication_Year INT
);

-- Table: Users
CREATE TABLE Users (
    ID_User INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255),
    Email VARCHAR(255),
    UNIQUE (Email)
);

-- Table: Loans (One-to-Many relationship with Users and Books)
CREATE TABLE Loans (
    ID_Loan INT PRIMARY KEY AUTO_INCREMENT,
    ID_Book INT,
    ID_User INT,
    Loan_Date DATE,
    Return_Date DATE,
    FOREIGN KEY (ID_Book) REFERENCES Books(ID_Book),
    FOREIGN KEY (ID_User) REFERENCES Users(ID_User)
);

-- Table: Categories
CREATE TABLE Categories (
    ID_Category INT PRIMARY KEY AUTO_INCREMENT,
    Category_Name VARCHAR(255),
    UNIQUE (Category_Name)
);

-- Table: Books_Categories (Many-to-Many relationship)
CREATE TABLE Books_Categories (
    ID_Book INT AUTO_INCREMENT,
    ID_Category INT,
    PRIMARY KEY (ID_Book, ID_Category),
    FOREIGN KEY (ID_Book) REFERENCES Books(ID_Book),
    FOREIGN KEY (ID_Category) REFERENCES Categories(ID_Category)
);
