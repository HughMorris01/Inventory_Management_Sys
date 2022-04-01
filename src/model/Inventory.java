package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is a static class containing static fields and methods used to keep track of all the inventory in the program.
 * This class is not meant to be instantiated.
 * @author Greg Farrell
 * @version 1.0
 * */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This method is used to add Parts to the static observable list of Parts.
     * @param newPart Part to be added to the observable list.
     * */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    /** This method is used to add Products to the static observable list of Products.
     * @param newProduct Part to be added to the observable list.
     * */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This method is used to get the static observable list of Parts.
     * @return a list of all parts
     * */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /** This method is used to get the static observable list of Products.
     * @return a list of all products
     * */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** This method is used to traverse the allParts list to search for a matching ID.
     * @param partId an int representing an ID to search for
     * @return a Part that matches the ID being searched for
     * */
    public static Part lookupPart(int partId) {
        for (Part thisPart : allParts) {
            if (thisPart.getId() == partId) {
                return thisPart;
            }
        }
        return null;
    }

    /** This method is used to traverse the allParts list to create a new ObservableList with all the matching results.
     * @param partName a String representing a name or partial name to search for
     * @return an observable list that contains the search results
     * @see Part#getName()
     * */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for (Part thisPart : allParts) {
            if (thisPart.getName().contains(partName)) {
                tempList.add(thisPart);
            }
        }
        return tempList;
    }

    /** This method is used to traverse the allProducts list to search for a matching ID.
     * @param productId an int representing an ID to search for
     * @return a Product that matches the ID being searched for
     * */
    public static Product lookupProduct(int productId) {
        Product product;
        for (Product thisProduct : allProducts) {
            if (thisProduct.getId() == productId) {
                product = thisProduct;
                return product;
            }
        }
        return null;
    }

    /** This method is used to traverse the allProducts list to create a new ObservableList with all the matching results.
     * @param productName a String representing a name or partial name to search for
     * @return an observable list that contains the search results
     * @see Product#getName()
     * */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for (Product thisProduct : allProducts) {
            if (thisProduct.getName().contains(productName)) {
                tempList.add(thisProduct);
            }
        }
        return tempList;
    }

    /** This method replaces a Part in the allParts list.
     * @param index index of the part to be replaced
     * @param selectedPart the new part to be inserted
     * */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** This method replaces a Product in the allProducts list.
     * @param index index of the part to be replaced
     * @param selectedProduct the new part to be inserted
     * */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /** This method deletes a Part from the allParts list.
     * @param selectedPart Part to be deleted from the list
     * @return boolean returns true if the selected part was successfully removed from the list
     * */
    public static boolean deletePart(Part selectedPart){
        return getAllParts().remove(selectedPart);
    }
    /** This method deletes a Product from the allProducts list.
     * @param selectedProduct Product to be deleted from the list
     * @return boolean returns true if the selected product was successfully removed from the list
     * */
    public static boolean deleteProduct(Product selectedProduct){
        return getAllProducts().remove(selectedProduct);
    }

    // Dummy data to populate the tables on the Inventory Main Screen
    static {
        Part dummyData1 = new InHouse(1001,"Widgets", 10.99, 12, 1, 15, 2025);
        Part dummyData2 = new InHouse(1002,"Lasers", 14.99, 27, 0, 35, 2035);
        Part dummyData3 = new InHouse(1003,"Rocks", 2.99, 10000, 1000, 30000, 2030);
        Part dummyData4 = new Outsourced(1004, "Space Dust", 99.99, 1200, 100, 15000, "Tesla");
        Part dummyData5 = new Outsourced(1005, "Water", 5.99, 347, 20, 500, "SpaceX");
        addPart(dummyData1);
        addPart(dummyData2);
        addPart(dummyData3);
        addPart(dummyData4);
        addPart(dummyData5);

        Product dummyData6 = new Product(5001,"Stone Boats", 1.99, 8, 0, 20);
        Product dummyData7 = new Product(5002,"X-Ray Visors", 999.99, 1, 0, 10);
        Product dummyData8 = new Product(5003,"Ice Sculptures", 20.99, 215, 1, 1000);
        addProduct(dummyData6);
        addProduct(dummyData7);
        addProduct(dummyData8);
    }

    // Initialize the part ID series at 1006, the 5 dummy parts took 1001 - 1005
    private static int uniquePartId = 1006;
    private static void incrementUniquePartId() { uniquePartId += 1; }

    /** This method creates a new unique ID when a new Part is created.
     * @return int the newly created ID
     * */
    public static int getNextUniquePartId() {
        int currId = uniquePartId;
        incrementUniquePartId();
        return currId;
    }

    // Initialize the product ID series at 5004, the 3 dummy parts took 5001 - 5004
    private static int uniqueProductId = 5004;
    private static void incrementUniqueProductId() { uniqueProductId += 1; }

    /** This method creates a new unique ID when a new Product is created.
     * @return int the newly created ID
     * */
    public static int getNextUniqueProductId() {
        int currId = uniqueProductId;
        incrementUniqueProductId();
        return currId;
    }
}
