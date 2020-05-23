/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author K
 */
public class Product extends Inventory{
    
private int productID;
private String productName;
private double price;
private int inStock;
private int min;
private int max;

private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();


public void addAssociatedPart(Part part){
    
    associatedParts.add(part);
    
}

public void setAssociatedParts(ObservableList<Part> associatedParts){
    
    Product.associatedParts = associatedParts;
}

public ObservableList<Part> getAssociatedParts(){
    
    return associatedParts;
}

public boolean removeAssociatedPart(int partNumber){
    
    associatedParts.remove(partNumber);
    return true;
    
}

public boolean hasList(Product product){
    
    if(this.getAssociatedParts() == null){
        
        return false;
    } else{
    return true;
    }
}


/// Unused method
public Part lookupAssociatedPart(int partNumber){
    Part part = null;
    return part;
    
}




/////////////////////////////////


    public Product(){
        
        this(0, "", 0, 0.0, 0, 0);
    }
    
    
    public Product(int productID, String productName, int inStock, double price, int max, int min ){
        
           setProductID(productID);
           setProductName(productName);
            setInStock(inStock);
           setPrice(price);
           setMax(max);
           setMin(min);
           
    }

 public Product(int productID, String productName, int inStock, double price, int max, int min, ObservableList<Part> associatedParts){
        
           setProductID(productID);
           setProductName(productName);
            setInStock(inStock);
           setPrice(price);
           setMax(max);
           setMin(min);
           setAssociatedParts(associatedParts);
           
    }

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
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

   
    
    
}
