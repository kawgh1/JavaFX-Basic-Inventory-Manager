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
public class OutsourcedPart extends Part {
    
    private String companyName;
    
    public boolean isInhousePart(){
        
        return false;
    }
    
    public OutsourcedPart(int partID, String partName, int inStock, double price, int max, int min, String companyName){
        
           setPartID(partID);
           setPartName(partName);
            setInStock(inStock);
           setPrice(price);
           setMin(min);
           setMax(max);
           setCompanyName(companyName);
            
        
    }
    public OutsourcedPart(){
        
        this(0,"",0,0.0, 0, 0, "");
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
