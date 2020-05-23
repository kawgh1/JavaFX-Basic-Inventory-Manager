/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import koltonwebberinvmgr.Model.InhousePart;
import koltonwebberinvmgr.Model.Inventory;

/**
 * FXML Controller class
 *
 * @author K
 */
public class AddPartsScreenController implements Initializable {
    
    
    
   

    @FXML
    private Label MachineID;

    @FXML
    private TextField PartNameField;
     @FXML
    private TextField PartInvField;
     
     @FXML
    private TextField PartPriceField;

    @FXML
    private TextField PartMaxField;

    @FXML
    private TextField PartMinField;

    @FXML
    private TextField MachineIDField;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
/**
 ***random int n is for generating a random, non-repeatable Part ID number 
- see SaveButtonHandler(ActionEvent event) method at bottom***
 */
     final Random rnd = new Random();
        int n;

 /**
 ***this boolean is for generating a random, non-repeatable Part ID number 
- see SaveButtonHandler(ActionEvent event) method at bottom***
 */
            boolean containsRepeatableNumber(final int n) {
                final boolean digits[] = new boolean[10];
                for(char c : String.valueOf(n).toCharArray()) {
                    final int i = c-'0';
                    if(digits[i])

                        return true;
                    digits[i] = true;
                }
                return false;
            }

    
     @FXML
    private void CancelButtonHandler(ActionEvent event) throws IOException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Are you sure you want to cancel?");
                    alert.setContentText("Please confirm.");
                    alert.showAndWait();
                    
                    if(alert.getResult() == ButtonType.CANCEL){
                        
                      alert.close();
                      return;
                        
                        
                    }
        
        
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene main_screen_scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_screen_scene);
        app_stage.show();
        
       
}

    @FXML
    private void InHouseRadioHandler(ActionEvent event) {
        
        MachineID.setText("Machine ID");

    }

    @FXML
  private  void OutsourcedRadioHandler(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("AddPartsScreenOut.fxml"));
        Scene add_parts_out_scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        app_stage.setScene(add_parts_out_scene);
        
        
        app_stage.show();
        
    }

    @FXML
    void SaveButtonHandler(ActionEvent event) throws IOException {
        //generate non-repeating partID number
    do {
            n = rnd.nextInt(1000);
        } while(containsRepeatableNumber(n));


    Inventory inventory1 = new Inventory();
   
       
        String ID = String.valueOf(n);
        String part = PartNameField.getText();
        String inStock = PartInvField.getText();
        String cost = PartPriceField.getText();
        String min = PartMinField.getText();
        String max = PartMaxField.getText();
        String machineID = MachineIDField.getText();
        
         if(PartNameField.getText().equals("") ||
                PartInvField.getText() .equals("") ||
                PartPriceField.getText() .equals("") ||
                PartMinField.getText() .equals("")  ||
                PartMaxField.getText() .equals("")  ||
                MachineIDField.getText() .equals("") ) {
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("One or more required fields have been left blank.");
                alert.setContentText("Please enter all required fields to add a part.");
                alert.showAndWait();
                return;
                }
         
         
         if(Integer.parseInt(PartInvField.getText()) < 0 ||
                 Integer.parseInt(PartInvField.getText()) > 100){
             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Part Inventory must be between 0 and 100.");
                alert.setContentText("Please enter all required fields to add a part.");
                alert.showAndWait();
                return;


                 }

        inventory1.addPart(new InhousePart(Integer.parseInt(ID), part, Integer.parseInt(inStock), Double.parseDouble(cost),
                                                                Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(machineID)));
        
    // reset screen fields to blank for more adding
        
      PartNameField.setText("");
        PartInvField.setText("");
        PartPriceField.setText("");
        PartMaxField.setText("");
        PartMinField.setText("");
        MachineIDField.setText("");
        
        // change to yes/no pop-up screen for "if finished adding parts?"
        // if yes, return to MainScreen.fxml
        // if no, return to AddPartsScreen.fxml

        Parent root =  FXMLLoader.load(getClass().getResource("YesNoAddPartScreen.fxml"));
        Scene yes_no_add_parts_screen = new Scene(root);
        
        
        
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        app_stage.setScene(yes_no_add_parts_screen);
      
        app_stage.show();
    
    

    }
}

    
