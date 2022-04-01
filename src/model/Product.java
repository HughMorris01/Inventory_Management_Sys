package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is a concrete class and is intended to be instantiated.
 * The class is supposed to represent Product objects, which have a number of descriptive attributes, as well as
 * observable list of associated parts.
 * */
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Product class constructor
     * @param id as an int
     * @param name as a String
     * @param price as a double
     * @param stock as an int
     * @param min as an int
     * @param max as an int
     * */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Sets the ID field.
     * @param id the id to set
     */
    public void setId(int id) { this.id = id; }

    /** Sets the name field.
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /** Sets the price field.
     * @param price the price to set
     */
    public void setPrice(double price) { this.price = price; }

    /** Sets the stock field.
     * @param stock the stock to set
     */
    public void setStock(int stock) { this.stock = stock; }

    /** Sets the minimum field.
     * @param min the min to set
     */
    public void setMin(int min) { this.min = min; }

    /** Sets the maximum field.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /** Gets the ID field.
     * @return the id as an int
     */
    public int getId() { return id; }

    /** Gets the name field.
     * @return the name as a String
     */
    public String getName() { return name; }

    /** Gets the price field.
     * @return the price as a double
     */
    public double getPrice() { return price; }

    /** Gets the stock field.
     * @return the stock as an int
     */
    public int getStock() { return stock; }

    /** Gets the maximum field.
     * @return the max as an int
     */
    public int getMax() { return max; }

    /** Gets the minimum field.
     * @return the min as an int
     */
    public int getMin() { return min; }

    /** This method adds a Part to the object's observable list of Parts
     * @param part the part to be added
     * */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** This method deletes a part from the object's observable list of parts
     * @param selectedAssociated part to be removed
     * @return boolean returns true of the remove() call was successful
     * */
    public boolean deleteAssociatedPart(Part selectedAssociated) {
        boolean wasRemoved = associatedParts.remove(selectedAssociated);
        return wasRemoved;
    }

    /** This method returns the object's observable list of parts
     * @return associated parts list
     * */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}