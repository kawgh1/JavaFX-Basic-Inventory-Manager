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
public class InhousePart extends Part {
    
     private int machineID;
    
    public boolean isInhousePart(){
        
        return true;
    }
    
    
      public InhousePart(int partID, String partName, int inStock, double price, int max, int min, int machineID){
        
           setPartID(partID);
           setPartName(partName);
            setInStock(inStock);
           setPrice(price);
           setMax(max);
           setMin(min);
           setMachineID(machineID);
       
    }
      
    public InhousePart(){
        
        this(0, "", 0, 0.0, 0, 0, 0);
    }
    
   

    /**
     * @return the machineID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * @param machineID the machineID to set
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
}
