/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kolton Webber
 * 
 */

package koltonwebberinvmgr;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import koltonwebberinvmgr.Model.InhousePart;
import koltonwebberinvmgr.Model.Inventory;
import koltonwebberinvmgr.Model.OutsourcedPart;
import koltonwebberinvmgr.Model.Product;



public class KoltonWebberINVMGR extends Application {
    
  
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // Pre-populating data into Main Parts Table for data manipulation
         Inventory inventory1 = new Inventory();
         
        //data
        inventory1.addPart(new InhousePart(0, "Brake Pad",10, 20.0, 0, 0, 0));
        inventory1.addPart(new InhousePart(1, "Front End", 3, 200.0, 0, 0, 0));
        inventory1.addPart(new InhousePart(2, "Brake Disc", 8, 80.0, 0, 0, 0));
        inventory1.addPart(new InhousePart(3, "Headlight", 7, 35.0, 0, 0, 0 ));
        inventory1.addPart(new InhousePart(4, "Headlight Assembly",3,  95.0, 0, 0, 0));
         inventory1.addPart(new OutsourcedPart(5, "Brakelight", 5, 45.0, 0, 0, "Jones Parts Co."));
         
         //Pre-populating data in Main Products Table for data manipulation
         Inventory inventory2 = new Inventory();
         
         //data
         
         // Initial generic products with no associated Parts
          inventory2.addProduct(new Product(0, "Heater Coil",10, 15.95, 0, 0));
        inventory2.addProduct(new Product(1, "RXP Gas Additive", 3, 7.99, 0, 0));
        inventory2.addProduct(new Product(2, "Transmission", 8, 2500.0, 0, 0));
       inventory2.addProduct(new Product(5, "Master Brake Cylinder",10, 15.95, 0, 0));
        inventory2.addProduct(new Product(4, "Water Pump",10, 15.95, 0, 0));
        inventory2.addProduct(new Product(3, "Radiator",10, 15.95, 0, 0));
        
        
        launch(args);
        
        
        
    }
    
}
