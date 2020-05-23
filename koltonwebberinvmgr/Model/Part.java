/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.Model;

/**
 *
 * @author K
 */
public abstract class Part {
    
    private int partID;
    private String partName;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    
    private String companyName;
    private int machineID;
  
    

    
   
    /**
     * @return the partID
     */
    public int getPartID() {
        return partID;
    }

    /**
     * @param partID the partID to set
     */
    public  void setPartID(int partID) {
        this.partID = partID;
    }

    /**
     * @return the partName
     */
    public String getPartName() {
        return partName;
    }

    /**
     * @param partName the name to set
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the inStock
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    public int getMachineID(){
        return machineID;
    }
    
    public String getCompanyName(){
        
        return companyName;
    }

   
    
}
