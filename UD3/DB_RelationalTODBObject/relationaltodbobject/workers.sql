CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(100),
    phone VARCHAR(20),
    phone1 VARCHAR(20),
    email VARCHAR(30),
    dni VARCHAR(9),
    nom VARCHAR(25),
    cognoms VARCHAR(50),
    poblacio VARCHAR(20),
    cp INT,
    provincia VARCHAR(20),
    pais VARCHAR(20)
);


CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(100),
    phone VARCHAR(20),
    phone1 VARCHAR(20),
    email VARCHAR(30),
    dni VARCHAR(9),
    nom VARCHAR(25),
    cognoms VARCHAR(50),
    poblacio VARCHAR(20),
    cp INT,
    provincia VARCHAR(20),
    pais VARCHAR(20)
    nom_usuari VARCHAR(25),
    password VARCHAR(10)
) ENGINE=InnoDB;


