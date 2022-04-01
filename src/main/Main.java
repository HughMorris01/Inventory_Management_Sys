package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This class creates an inventory management application for keeping track of Parts and Products on hand
 * at a small manufacturing firm. The class contains the program's main() method and is not intended to be instantiated.
 * FUTURE ENHANCEMENTS: The inventory totals for products should have methods and logic implemented so that the amounts
 * correspond with the inventory totals for parts.
 * @author Greg Farrell
 * @version 1.0
 * */
public class Main extends Application {

    /** This method starts the inventory management application and loads the main FXML screen.
     * @throws Exception FXMLLoader.load() will throw and exception if it cannot locate the FXML document.
     * @param stage The initial stage for loading the first scene.
     * */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/InventoryMainScreen.fxml"));
        stage.setTitle("Inventory Main Screen");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    /** The Javadoc folder is located in the main directory, "/C482 Assessment Project/Javadoc".
     *  This is the main method that begins the program.
     * @param args command-line arguments.
     * */
    public static void main(String[] args) {
        launch(args);
    }
}
