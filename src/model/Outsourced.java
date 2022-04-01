package model;

/** This class is an extension of the Part class and the primary difference is the addition of the companyName field.
 * @author Greg Farrell
 * @version 1.0
 * */
public class Outsourced extends Part {

    private String companyName;

    /** This is the Outsourced class' constructor. It takes all the same parameters as the Part class and passes them
     * to the super() constructor and then also takes the companyName parameter and sets the companyName field equal to
     * it.
     * @param id part ID as an in
     * @param name part name as a String
     * @param price as a double
     * @param stock inventory as an int
     * @param min minimum inventory as an int
     * @param max maximum inventory as an int
     * @param companyName outside company's name as a String
     * @see Outsourced#setCompanyName(String)
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /** This method sets the companyName field
     * @param companyName company name as a String
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** This method returns the company name
     * @return a String value representing the outsourcing company name
     * */
    public String getCompanyName() {
        return companyName;
    }
}


