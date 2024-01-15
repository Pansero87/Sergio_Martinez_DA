
CREATE TYPE contacto AS (
    direccion VARCHAR(100),
    phone VARCHAR(20),
    phone1 VARCHAR(20),
    email VARCHAR(30),
    dni VARCHAR(9)
);

CREATE TYPE persona AS (
    contact_info contacto,
    nom VARCHAR(25),
    cognoms VARCHAR(50),
    dni VARCHAR(9),
    poblacio VARCHAR(20),
    cp INT,
    provincia VARCHAR(20),
    pais VARCHAR(20)
);


CREATE TABLE clientes OF persona;



CREATE TABLE empleados (
    nom_usuari VARCHAR(25),
    password VARCHAR(10)
) INHERITS (clientes);







