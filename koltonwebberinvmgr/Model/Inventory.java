/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import koltonwebberinvmgr.Model.Part;
import koltonwebberinvmgr.Model.Product;


/**
 *
 * @author K
 */
public class Inventory {
    
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    public void sort(){
        allParts.sort(null);
    }
    
    public ObservableList<Product> getAllProducts(){
        
        return allProducts;
    }
    
     public ObservableList<Part> getAllParts(){
        
        return allParts;
    }
    
    public void addProduct(Product product){
        allProducts.add(product);
        
    }
    
    public boolean  removeProduct(int productNumber){
        allProducts.remove(productNumber);
        return true;
    }
    
    public boolean  removeProduct(Product product){
        allProducts.remove(product);
        return true;
    }
    
   public void addPart(Part part){
        allParts.add(part);
        
    }
    
   public boolean deletePart(Part part){
      allParts.remove(part);
      return true;
}
        
    
    ////// Unused methods
  
  /*
  These methods were incorporated into Search and Modify Button functionalities
  */
   public Part lookupPart(int partNumber){
        Part part = null;
        
       return part; 
        
        
   }
    
  public void updatePart(int partID, Part part){
      
       
   }
  
   public void updateProduct(int productNumber, Product product){
        
        
    }
    
  public Product loookupProduct(int productNumber){
        Product product = null;
        
       return product; 
    }
  
}
