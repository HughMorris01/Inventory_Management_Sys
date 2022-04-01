package controller;

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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is the controller file for the InventoryMainScreen FXML document and not meant to be instantiated.
 * The class loads tables displaying all the Parts and Products on hand, which are contained in static observable lists
 * in the Inventory class. It also contains buttons redirecting the program to the Add Part form, Modify Part form,
 * Add Product form and Modify Product form, as well as Delete buttons that remove Parts and Products directly from
 * their associated observable lists.
 * @author Greg Farrell
 * @version 1.0
 * */
public class InventoryMainScreenController implements Initializable {

    /** Parts tableview */
    public TableView<Part> partTable;
    /** Products tableview */
    public TableView<Product> productTable;
    /** Parts ID column */
    public TableColumn<Part, Integer> partIDCol;
    /** Parts Name column */
    public TableColumn<Part, String> partNameCol;
    /** Parts Stock column */
    public TableColumn<Part, Integer> partInventoryCol;
    /** Parts Price column */
    public TableColumn<Part, Double> partPriceCol;
    /** Products ID column */
    public TableColumn<Product, Integer> productIDCol;
    /** Products Name column */
    public TableColumn<Product, String> productNameCol;
    /** Products Stock column */
    public TableColumn<Product, Integer> productInventoryCol;
    /** Products Price column */
    public TableColumn<Product, Double> productPriceCol;
    /** Search parts text field */
    public TextField partSearchTextField;
    /** Search products text field */
    public TextField productSearchTextField;

    /** This method is called by the FXMLLoader.load() call contained in the start() method of the Main class. The
     * method initializes the TableViews and TableColumns and then populates them with the Parts and Products from
     * their corresponding static observable lists in the Inventory class.
     * @param resourceBundle An unreferenced ResourceBundle object passed automatically
     * @param url An unreferenced URL object passed automatically
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(Inventory.getAllParts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(Inventory.getAllProducts());
    }

    /** This method is an event handler for the Add button under the Parts table. Upon clicking the button
     * this method loads the AddPartScreen FXML document and redirects the stage to load the Add Part screen.
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document
     * @throws IOException Exception gets thrown if load() cannot locate the FXML file
     * */
    public void toAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartScreen.fxml"));
        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Add Part Screen");
        stage.show();
    }

    /** This method is an event handler for the Add button under the Products table.
     * Upon clicking the button this method loads the AddProductScreen FXML document and
     * redirects the stage to load the Add Part screen.
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document
     * @throws IOException Exception gets thrown if load() cannot locate the FXML file
     * */
    public void toAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductScreen.fxml"));
        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Add Product Screen");
        stage.show();
    }

    /** This method is an event handler for the Modify button under the Parts table.
     * If a part is selected within the Parts table, clicking this button loads the ModifyPartScreen FXML document
     * and redirects the stage to load the Modify Part screen. A reference to the selected part, along with its index
     * in the static observable list in the Inventory class, are passed to static members within the
     * ModifyPartScreenController so that the selected part can be manipulated. If no part is selected, the method
     * catches the IOException so that the program doesn't crash.
     * @see Inventory#getAllParts()
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
     * */
    public void toModifyPart(ActionEvent actionEvent) {
        try {
            ModifyPartScreenController.tempPart = partTable.getSelectionModel().getSelectedItem();
            ModifyPartScreenController.tempPartIndex = Inventory.getAllParts().indexOf(ModifyPartScreenController.tempPart);

            Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartScreen.fxml"));
            Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Modify Part Screen");
            stage.show();
        }
        catch (IOException exception1) {
            //Ignore exception
        }
    }

    /** This method is an event handler for the Modify button under the Products table.
     * If a product is selected within the Products table, clicking this button loads the ModifyProductScreen FXML
     * document and redirects the stage to load the Modify Product screen. A reference to the selected product, along
     * with its index in the static observable list in the Inventory class, are passed to static members within the
     * ModifyProductScreenController so that the selected product can be manipulated. If no product is selected,
     * the method catches the IOException so that the program doesn't crash.
     * @see Inventory#getAllProducts()
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
     * */
    public void toModifyProduct(ActionEvent actionEvent) {
        try {
        ModifyProductScreenController.tempProduct = productTable.getSelectionModel().getSelectedItem();
        ModifyProductScreenController.tempProductIndex = Inventory.getAllProducts().indexOf(ModifyProductScreenController.tempProduct);

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProductScreen.fxml"));
        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Modify Product Screen");
        stage.show();
        }
        catch (IOException exception1) {
        System.out.println("Modify product error");
        }
    }

    /** This method is an event handler for the Delete button under the Parts table.
     * If a part is selected within the Parts table, the method will first generate an alert window to confirm the
     * user wishes to permanently delete the selected part and upon confirmation, the part will be removed from the static
     * observable list within the Inventory class that contains all the parts.
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
     * @see Inventory#deletePart(Part) 
     * */
    public void toDeletePart(ActionEvent actionEvent) {
        Part part = partTable.getSelectionModel().getSelectedItem();

        if(part == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Permanently delete selected part?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent()  && result.get() == ButtonType.OK) {
            if(Inventory.deletePart(part)) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Deletion Confirmation");
                alert1.setContentText("The selected part has been deleted.");
                alert1.show();
                }
        }
    }

    /** This method is an event handler for the Delete button under the Products table.
     * If a product is selected within the Products table, the method will first check to see if the product has
     * associated parts and if so, it will generate an alert window indicating the product cannot be deleted. If the
     * product has no associated parts then an alert window is created to confirm the user wishes to permanently delete
     * the selected product. Upon confirmation, the product will be removed from the static observable list in the
     * Inventory class that contains all the products.
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
     * @see Inventory#deleteProduct(Product)
     * */
    public void toDeleteProduct(ActionEvent actionEvent) {
        Product product = productTable.getSelectionModel().getSelectedItem();
        
        if(product.getAllAssociatedParts().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Permanently delete selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent()  && (result.get() == ButtonType.OK)) {
                if(Inventory.deleteProduct(product)) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Deletion Confirmation");
                    alert1.setContentText("The selected product has been deleted");
                    alert1.show();
                }
            }
        }
        else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Cannot Delete");
            alert1.setContentText("The selected item has associated parts and cannot be deleted");
            alert1.show();
        }
    }

    /** This method is an event handler for the Exit button in the bottom corner of the UI.
     * Upon clicking the button the stage gets grabbed and closed, exiting the program.
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
     * */
    public void closeProgram(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        stage.close();
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
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
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

    /** This method is an event handler for the Search text field above the Products table.
     * The method grabs the input entered in the Search box and then passes that data to the static overloaded method
     * lookupProduct() from the Inventory class to search for matches within the Inventory class' static observable list
     * of products. If the input entered is text, a new observable list is populated with all matches containing the input
     * and the list is used to repopulate the table with the search results. If no text matches are found, lookupProduct()
     * is called again to check to see if there is an exact int match with a Product ID and if so, the Products table is
     * repopulated with the one matching result. If no matches are found for either the text or the int searches then
     * an alert is created advising that no results were found. When the handler is called on a blank Search box, the
     * table will repopulate with the full static list of Products from Inventory class.
     * @see Inventory#lookupProduct(String)
     * @see Inventory#lookupProduct(int)
     * @param actionEvent Passed from the On Action event listener in the InventoryMainScreen FXML document.
     * */
    public void searchProducts(ActionEvent actionEvent) {
        try {
            String tempString = productSearchTextField.getText();
            ObservableList<Product> foundProducts = Inventory.lookupProduct(tempString);

            if (foundProducts.size() == 0) {
                int tempInt = Integer.parseInt(productSearchTextField.getText());
                Product tempProduct = Inventory.lookupProduct(tempInt);
                if (tempProduct != null) {
                    foundProducts.add(tempProduct);
                }
                if (foundProducts.isEmpty()) {
                    productSearchTextField.setText("");
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Invalid Input");
                    alert1.setContentText("No results found!");
                    alert1.show();
                    return;
                }
            }
            productTable.setItems(foundProducts);
            productSearchTextField.setText("");
        }
        catch (NumberFormatException exception) {
            productSearchTextField.setText("");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Invalid Input");
            alert1.setContentText("No results found!");
            alert1.show();
        }
    }
}


