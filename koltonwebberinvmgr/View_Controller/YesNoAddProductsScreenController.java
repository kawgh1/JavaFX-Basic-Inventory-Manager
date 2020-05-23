/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import koltonwebberinvmgr.Model.Inventory;


/**
 * FXML Controller class
 *
 * @author K
 */
public class YesNoAddProductsScreenController implements Initializable {

     

    
    @FXML
    private Button NoButton;
    
      Inventory inventory2 = new Inventory();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
      @FXML
    void No(ActionEvent event) throws IOException {
        
        Stage stage;
             Parent root;
             stage=(Stage) NoButton.getScene().getWindow();
             FXMLLoader loader=new FXMLLoader(getClass().getResource(
                     "MainScreen.fxml"));
             root = loader.load();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
             MainScreenController controller = loader.getController();
              controller.MainProductsTable.setItems(inventory2.getAllProducts());


    }

    @FXML
    void Yes(ActionEvent event) throws IOException{
        
        Parent root =  FXMLLoader.load(getClass().getResource("AddProductsScreen.fxml"));
        Scene add_parts_page_scene = new Scene(root);
        
        
        
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        app_stage.setScene(add_parts_page_scene);
      
        app_stage.show();

    }
}
