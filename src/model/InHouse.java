package model;

/** This class is an extension of the Part class and the primary difference is the addition of the machineId field.
 * @author Greg Farrell
 * @version 1.0
 * */
public class InHouse extends Part{

    private int machineId;

    /** This is the InHouse class' constructor. It takes all the same parameters as the Part class and passes them
     * to the super() constructor and then also takes the machineId parameter and sets the machineId field equal to
     * it.
     * @param id part ID as an in
     * @param name part name as a String
     * @param price as a double
     * @param stock inventory as an int
     * @param min minimum inventory as an int
     * @param max maximum inventory as an int
     * @param machineId machine ID as an int
     * @see InHouse#setMachineId(int)
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);
    }

    /** This method sets the machineId field
     * @param machineId machine ID as an int
     * */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /** This method returns the machineId
     * @return an int value representing the machine ID
     * */
    public int getMachineId() {
        return this.machineId;
    }

}
