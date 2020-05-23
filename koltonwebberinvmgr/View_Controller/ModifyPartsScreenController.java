/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import koltonwebberinvmgr.Model.Part;
import koltonwebberinvmgr.Model.InhousePart;
import koltonwebberinvmgr.Model.Inventory;
import static koltonwebberinvmgr.View_Controller.MainScreenController.partToModify;




/**
 * FXML Controller class
 *
 * @author K
 */
public class ModifyPartsScreenController implements Initializable {
    
    
    @FXML
    private Label MachineID1;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    @FXML
    private RadioButton InHouseModRadio;
    
    @FXML
    private ToggleGroup group1;

     @FXML
    private TextField PartNameField;
     @FXML
     private TextField PartIDField;
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

    @FXML
    private RadioButton OutsourcedModRadio;
    
    /////////////////////
    
    @FXML
    static  TableView<Part> MainPartsTable;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PartPriceCostPerUnit;
    
    private  Part part;
    private ObservableList<Part> data3;
    
 
    Inventory inventory1 = new Inventory();
        
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
 
    // this method is just for scene transitition, not part of TableView operations
public void setPart(Part part){
        this.part = part;
         
        PartIDField.setText(Integer.toString(part.getPartID()));
        PartNameField.setText(part.getPartName());
        
       PartInvField.setText(Integer.toString(part.getInStock()));
    
    PartPriceField.setText(Double.toString(part.getPrice()));

    PartMaxField.setText(Integer.toString(part.getMax()));

     PartMinField.setText(Integer.toString(part.getMin()));
     
    MachineIDField.setText(Integer.toString(part.getMachineID()));
     
     }
  
     @FXML
     void CancelButtonHandler(ActionEvent event) throws IOException {
        
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
     void InHouseRadioModHandler(ActionEvent event) {
        
        MachineID1.setText("Machine ID");

    }

    @FXML
     void OutsourcedRadioModHandler(ActionEvent event) throws IOException{
             
              Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("System Information");
                    alert.setContentText("Unable to change an In-House Part into an Outsourced Part. A new Outsourced Part must be created.");
                    alert.showAndWait();
                   
          }
        

    @FXML
    void SaveButtonHandler(ActionEvent event) throws IOException {
        
      
        String ID = PartIDField.getText();
        String partName = PartNameField.getText();
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
        
       
        
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.initModality(Modality.NONE);
         alert.setTitle("Confirmation Dialog");
         alert.setHeaderText("Ready?");
         alert.setContentText("Update this part's value with the new input values?");
         alert.showAndWait();
        
        
        if(alert.getResult() == ButtonType.CANCEL){
                        
                      alert.close();
                      return;
                        
                        
                    }
        
        Part part1 = new InhousePart();
        part1.setPartID(Integer.parseInt(ID));
        part1.setPartName(partName);
        part1.setInStock(Integer.parseInt(inStock));
        part1.setPrice(Double.parseDouble(cost));
        part1.setMax(Integer.parseInt(max));
        part1.setMin(Integer.parseInt(min));
        ((InhousePart) part1).setMachineID(Integer.parseInt(machineID));
        
         inventory1.addPart(part1);
         
         inventory1.deletePart(partToModify);
         
        
        if(alert.getResult() == ButtonType.OK){
            
            
                      alert.close();
                       
        }
             
             
             Stage stage;
             Parent root;
             stage=(Stage) SaveButton.getScene().getWindow();
             FXMLLoader loader=new FXMLLoader(getClass().getResource(
                     "MainScreen.fxml"));
             root = loader.load();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
             MainScreenController controller = loader.getController();
              controller.MainPartsTable.setItems(inventory1.getAllParts());
              // controller.MainPartsTable.getSortOrder().add(PartID);
               controller.MainPartsTable.scrollTo(partToModify);
   
         
    }
    
    

}

    
