package model;
/**
 * This abstract class represents Part objects and each object contains a number of descriptive attribute fields. There are setter
 * and getter functions for each of the class' fields that must be utilized by subclasses in order to access these
 * fields.
 * @author Greg Farrell
 * @version 1.0
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Part class constructor
     * @param id as an int
     * @param name as a String
     * @param price as a double
     * @param stock as an int
     * @param min as an int
     * @param max as an int
     * */
    public Part(int id, String name, double price, int stock, int min, int max) {
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
    public void setMax(int max) { this.max = max; }

    /** Gets the ID field.
     * @return the id as an int
     */
    public int getId() { return id;}

    /** Gets the name field.
     * @return the name as a String
     */
    public String getName() { return name; }

    /** Gets the price field.
     * @return the price as a double
     */
    public double getPrice() { return price; }

    /** Gets stock field.
     * @return the stock as an int
     */
    public int getStock() { return stock; }

    /** Gets minimum field.
     * @return the min as an int
     */
    public int getMin() { return min; }

    /** Gets maximum field.
     * @return the max as an int
     */
    public int getMax() { return max; }
}