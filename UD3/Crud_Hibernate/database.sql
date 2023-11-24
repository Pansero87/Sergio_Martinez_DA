DROP DATABASE IF EXISTS OrchestraDB;

CREATE DATABASE OrchestraDB;

USE OrchestraDB;


CREATE TABLE Orchestra (
    OrchestraID INT PRIMARY KEY AUTO_INCREMENT,
    OrchestraName VARCHAR(255) UNIQUE
    
);


CREATE TABLE Musician (
    MusicianID INT PRIMARY KEY AUTO_INCREMENT,
    MusicianName VARCHAR(255),
    Instrument VARCHAR(50),
    idOrchestra INT,
    FOREIGN KEY (idOrchestra) REFERENCES Orchestra(OrchestraID)
    
);


CREATE TABLE OrchestraMusician (
    OrchestraID INT,
    MusicianID INT,
    PRIMARY KEY (OrchestraID, MusicianID),
    FOREIGN KEY (OrchestraID) REFERENCES Orchestra(OrchestraID),
    FOREIGN KEY (MusicianID) REFERENCES Musician(MusicianID)
);

