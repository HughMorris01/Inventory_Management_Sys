package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is the controller file for the AddProductScreen FXML document and not meant to be instantiated.
 * The class collects data from the text fields and tables, uses those to create a new Product object and then adds
 * it to the static observable list of Products in the Inventory class.
 * @author Greg Farrell
 * @version 1.0
 */
public class AddProductScreenController implements Initializable {
    /** Part tableview */
    public TableView<Part> partTable;
    /** Part ID column */
    public TableColumn<Part, Integer> partIdCol;
    /** Part name column */
    public TableColumn<Part, String> partNameCol;
    /** Part stock column */
    public TableColumn<Part, Integer> partInventoryCol;
    /** Part price column */
    public TableColumn<Part, Double> partPriceCol;
    /** Associated part tableview */
    public TableView<Part> associatedPartsTable;
    /** Associated part ID column */
    public TableColumn<Part, Integer> associatedPartIDCol;
    /** Associated part name column */
    public TableColumn<Part, String> associatedPartNameCol;
    /** Associated part stock column */
    public TableColumn<Part, Integer> associatedPartInventoryCol;
    /** Associated part price column */
    public TableColumn<Part, Double> associatedPartPriceCol;

    /** Name text field */
    public TextField nameTextField;
    /** Minimum text field */
    public TextField minTextField;
    /** Maximum text field */
    public TextField maxTextField;
    /** Stock text field */
    public TextField inventoryTextField;
    /** Price text field */
    public TextField priceTextField;
    /** Part search text field */
    public TextField partSearchTextField;

    private ObservableList associatedParts = FXCollections.observableArrayList();

    /** This method is called by the FXMLLoader.load() call contained in the toAddProduct() method of the
     * InventoryMainScreeController class. It initializes and populates two tables, one containing all the parts within
     * the static observable list of Parts in the Inventory class and the other with the associated Parts (initially
     * empty) of this Product.
     * @param resourceBundle An unreferenced ResourceBundle object passed automatically
     * @param url An unreferenced URL object passed automatically
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(Inventory.getAllParts());

        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.setItems(associatedParts);
    }

    /** This method is an event handler for the Cancel button that sends the program back to the main screen.
     * The method loads the FXML document for the main inventory screen, passes that to a new scene and then sets the
     * stage with the new scene.
     * @param actionEvent Passed from the On Action event listener in the ModifyProductScreen FXML document
     * @throws IOException The FXMLLoader.load() call will throw this exception if the FXML document can't be found.
     */
    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/InventoryMainScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Inventory Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    /** This method is the event handler for the Add button under the parts table.
     * The method adds a selected Part from the table to the observable list that temporarily holds the associated
     * parts.
     * @param actionEvent Passed from the On Action event listener in the ModifyProductScreen FXML document
     * @see Product#addAssociatedPart(Part)
     */
    public void addPart(ActionEvent actionEvent) {
        Part part = partTable.getSelectionModel().getSelectedItem();
        associatedParts.add(part);
    }

    /** This method is the event handler for the Remove Associated Part button.
     * The method first creates an alert box to verify that the user does wish to remove the associated part then
     * it deletes the selected part from the temporary observable list. A final alert window then confirms the removal
     * has been completed.
     * @param actionEvent Passed from the On Action event listener in the ModifyProductScreen FXML document
     * @see Product#deleteAssociatedPart(Part)
     */
    public void removePart(ActionEvent actionEvent) {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you wish to remove this associated part?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent()  && result.get() == ButtonType.OK) {
            if (associatedParts.remove(part)) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Deletion Confirmation");
                alert1.setContentText("Associated part has been removed from this product.");
                alert1.show();
            }
        }
    }

    /** This method is the event handler for the Save button.
     * The method first grabs all the data from the text fields and performs validation checks and exception
     * handling for dealing with invalid input. The Product() constructor is then called to create a new Product object
     * using that data. The associated parts observable list is then looped through to add all of the associated parts
     * to the new Products associated parts list. Finally, the Product is added to the static observable list of
     * Products in the Inventory class.
     * @param actionEvent Passed from the On Action event listener in the ModifyProductScreen FXML document
     * @see Product#Product(int, String, double, int, int, int)
     * @see Product#addAssociatedPart(Part)
     */
    public void saveProduct(ActionEvent actionEvent) {
        int id;
        String name;
        double price;
        int stock;
        int min;
        int max;

        try {
                // Create a new product ID and grab all the data entered in the text fields
                id = Inventory.getNextUniqueProductId();
                name = nameTextField.getText();
                price = Double.parseDouble(priceTextField.getText());
                stock = Integer.parseInt(inventoryTextField.getText());
                min = Integer.parseInt(minTextField.getText());
                max = Integer.parseInt(maxTextField.getText());

            // Verify that the value entered for Maximum is greater than Minimum
            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setContentText("Minimum must be less than or equal to Maximum.");
                alert.show();
                return;
            }

            // Verify that the value of stock entered is between the Minimum and Maximum amounts
            if ((min > stock) || (stock > max)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setContentText("Inventory must be between Minimum and Maximum.");
                alert.show();
                return;
            }
                // Create new product object
                Product newProduct = new Product (id, name, price, stock, min, max);

                // Loop through the associatedParts list and add each item to the newProduct observable list
                for (int i = 0; i < associatedParts.size(); i++) {
                    newProduct.addAssociatedPart((Part) associatedParts.get(i));
                }
                Inventory.getAllProducts().add(newProduct);

            // Redirect back to the main inventory screen
            Parent root = FXMLLoader.load(getClass().getResource("/view/InventoryMainScreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("Inventory Main Screen");
            stage.setScene(scene);
            stage.show();

        }
        catch (NumberFormatException | IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid values in the input fields");
            alert.setTitle("Invalid Input Type");
            alert.setContentText(exception.getLocalizedMessage());
            alert.show();
        }
    }

    /** This method is an event handler for the Search text field above the Parts table.
     * The method grabs the input entered in the Search box and then calls the static overloaded method lookupPart()
     * from the Inventory class to search for matches within the Inventory class' static observable list of parts. If
     * the input entered is text, a new observable list is populated with all matches containing the input and the list
     * is used to repopulate the table with the search results. If no text matches are found, lookupPart() is called
     * again to check to see if there is an exact int match with a Part ID and, if so, the Parts table is repopulated
     * with the one matching result. If no matches are found for either the text or the int searches then an alert is
     * created advising that no results were found. When the handler is called on a blank search box, the table will
     * repopulate with the full list of Parts from the static observable list in the Inventory class.
     * @see Inventory#lookupPart(String)
     * @see Inventory#lookupPart(int)
     * @param actionEvent Passed from the On Action event listener in the ModifyProductScreen FXML document.
     * */
    public void searchParts(ActionEvent actionEvent) {
        try {
            String tempString = partSearchTextField.getText();
            ObservableList<Part> foundParts = Inventory.lookupPart(tempString);

            if (foundParts.size() == 0) {
                int tempInt = Integer.parseInt(partSearchTextField.getText());
                Part tempPart = Inventory.lookupPart(tempInt);

                if (tempPart != null) {
                    foundParts.add(tempPart);
                }
            }
            if (foundParts.isEmpty()) {
                partSearchTextField.setText("");
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Invalid Input");
                alert1.setContentText("No results found!");
                alert1.show();
                return;
            }
            partTable.setItems(foundParts);
            partSearchTextField.setText("");
        }
        catch (NumberFormatException exception) {
            partSearchTextField.setText("");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Invalid Input");
            alert1.setContentText("No results found!");
            alert1.show();
        }
    }
}
