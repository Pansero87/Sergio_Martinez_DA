package app;

import ExportToODB.Exports;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * The main method is the entry point of the program.
	 * It displays a menu to the user and performs actions based on their choice.
	 * The user can choose to export data to ODB or exit the program.
	 */

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Mi Aplicación");

		BorderPane root = new BorderPane();

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("Archivo");
		MenuItem exportItem = new MenuItem("Exportar");
		exportItem.setOnAction(this::exportButtonClick);

		fileMenu.getItems().add(exportItem);
		menuBar.getMenus().add(fileMenu);

		root.setTop(menuBar);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void exportButtonClick(ActionEvent event) {
		Exports.recordBooksToODB();
		Exports.recordUsersToODB();
	}

}
