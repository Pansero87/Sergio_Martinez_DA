# Program Explanation

The program consists of two SQL files. One file creates the Library database with its corresponding tables. The other file, Inserts, inserts users and books into the database.

In the Model folder, you will find the entities mapped in Hibernate for the database. In the ModelODB folder, the entities Books and Users are mapped to create objects for the ODB database.

In the main file, there is a menu. When the "import" option is selected, it calls the methods `Exports.recordBooksToODB()` and `Exports.recordUsersToODB()`. These methods access the users and books tables, save them in a list, and then instantiate new objects with the ODB models. Finally, the objects are exported to the libraryODB database.

The purpose of the program is to export data from a relational database to an object database.

I have tried to use the JavaFX library to create a menu for importing the database. However, I have not been able to import the libraries correctly.
