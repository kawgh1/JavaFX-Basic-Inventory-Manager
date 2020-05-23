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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import koltonwebberinvmgr.Model.Inventory;
import koltonwebberinvmgr.Model.Part;
import koltonwebberinvmgr.Model.Product;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author K
 */
public class AddProductsScreenController implements Initializable {
    
    

    
    @FXML
    private TableView<Part> ProductsTable1;
    
    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PartPriceCostPerUnit;
    
    /////////////////////////////////////////////////////////////
    
    @FXML
    private TableView<Part> ProductsTable2;
    
     @FXML
    private TableColumn<Part, Integer> PartID1;

    @FXML
    private TableColumn<Part, String> PartName1;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel1;

    @FXML
    private TableColumn<Part, Double> PartPriceCostPerUnit1;
    
    //////////////////////////////////////////////////////////////
    
     @FXML
    private TextField ProductNameField;

    @FXML
    private TextField ProductInvField;

    @FXML
    private TextField ProductPriceField;

    @FXML
    private TextField ProductMaxField;

    @FXML
    private TextField ProductMinField;
    
//////////////////////////////////////////////////////////
    @FXML
    private TextField ProductSearchField;
    
    @FXML
    private TextField PartsSearchField;

   
    
    /////////////////////////////////////////////////////////
    
    Inventory inventory1 = new Inventory(); // Main Parts Talble inventory to draw from
    
    Inventory inventory5 = new Inventory();  // used for ProductsTable2, on screen open contains no parts
    
    // data 2 is an inbetween List for ProductsTable2 to copy from
    private static ObservableList<Part> data2 = FXCollections.observableArrayList();
    

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         ProductsTable1.setItems(inventory1.getAllParts());
        
         PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
               
         PartName.setCellValueFactory(new PropertyValueFactory<>("partName"));
   
         PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      
         PartPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
            
         // Don't need to initialize data in ProductsTable2 because it draws all its parts from ProductTable1 above
           
         // the parts data in ProductsTable2 is then used to attach to a newly created Product on the left side of screen
           
         ProductsTable2.setItems(null);
           
         PartID1.setCellValueFactory(new PropertyValueFactory<>("partID"));
               
         PartName1.setCellValueFactory(new PropertyValueFactory<>("partName"));
   
         PartInventoryLevel1.setCellValueFactory(new PropertyValueFactory<>("inStock"));
         
         PartPriceCostPerUnit1.setCellValueFactory(new PropertyValueFactory<>("price"));
            
           
           
           //System.out.println(inventory5.getAllParts());
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
    void CancelButton1Handler(ActionEvent event) throws IOException {
        
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
        Scene main_screen = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_screen);
        app_stage.show();

    }
    
    @FXML
    void DeleteProductsButtonHandler(ActionEvent event) {
    if(ProductsTable2.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Parts list to modify.");
                    alert.showAndWait();
                    
                    return;
          }
    
    Part part = ProductsTable2.getSelectionModel().getSelectedItem();
       
        
        data2.remove(part);
        
        
        
        ProductsTable2.setItems(data2);
    }

    @FXML
    void PartsSearchButtonHandler(ActionEvent event) {
        
        String searchItem = ProductSearchField.getText();
        boolean found = false;
        try {
                    int partID = Integer.parseInt(searchItem);
                    for( Part p : ProductsTable1.getItems()){
                
                                if(p.getPartID() == partID){
                                    
                                    

                                    System.out.println("PartID " + partID + " has been found!");
                                    found = true;
                                    ProductsTable1.getSelectionModel().select(p);
                                    ProductsTable1.scrollTo(p);
                                    
                                    System.out.println(ProductsTable1.getSelectionModel().getSelectedCells());
                                   
                                   
                                }
            }
            
            if (found == false){
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Part not found.");
                    alert.setContentText("Please try again using a different parameter.");
                    alert.showAndWait();
                    
            }
            
        }
        catch(NumberFormatException e) {
            //e.printStackTrace();
            
                    for (Part p : ProductsTable1.getItems()){

                                if( p.getPartName().equalsIgnoreCase(searchItem)){
                                    System.out.println("Part " + p.getPartName() + " has been found!");
                                    found = true;
                                    ProductsTable1.getSelectionModel().select(p);
                                    ProductsTable1.scrollTo(p);
                                    System.out.println(ProductsTable1.getSelectionModel().getSelectedCells());
                                   
                                }
                    }
                    
          if (found == false){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Part not found.");
                    alert.setContentText("Please try again using a different parameter.");
                    alert.showAndWait();
          }          
        }

    }

    @FXML
    void SaveButton1Handler(ActionEvent event) throws IOException {
        
        // first, generate random non-repeating ProductID number
         do {
                   n = rnd.nextInt(1000);
                } while(containsRepeatableNumber(n));


         Inventory inventory7 = new Inventory();
   
       
        String ID = String.valueOf(n);
        String product = ProductNameField.getText();
        String inStock = ProductInvField.getText();
        String price = ProductPriceField.getText();
        String min = ProductMinField.getText();
        String max = ProductMaxField.getText();
        ObservableList<Part> associatedParts = ProductsTable2.getItems();
        
         if(ProductNameField.getText().equals("") ||
                ProductInvField.getText() .equals("") ||
                ProductPriceField.getText() .equals("") ||
                ProductMinField.getText() .equals("")  ||
                ProductMaxField.getText() .equals("")) {
                
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("One or more required fields have been left blank.");
                alert.setContentText("Please enter all required fields to add a product.");
                alert.showAndWait();
                return;

                }
         
         if(Integer.parseInt(ProductInvField.getText()) < 0 ||
                 Integer.parseInt(ProductInvField.getText()) > 100){
             
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Part Inventory must be between 0 and 100.");
                alert.setContentText("Please enter all required fields to add a part.");
                alert.showAndWait();
                return;


                 }
        
        inventory7.addProduct(new Product(Integer.parseInt(ID), product, Integer.parseInt(inStock), Double.parseDouble(price),
                                                                Integer.parseInt(min), Integer.parseInt(max),  associatedParts    ));
        

    // reset screen fields to blank for more adding
        
      ProductNameField.setText("");
         ProductInvField.setText("");
         ProductPriceField.setText("");
         ProductMaxField.setText("");
         ProductMinField.setText("");
        
        // change to yes/no pop-up screen for "if finished adding products?"
        // if yes, return to MainScreen.fxml
        // if no, return to AddProductsScreen.fxml

        Parent root =  FXMLLoader.load(getClass().getResource("YesNoAddProductsScreen.fxml"));
        Scene yes_no_add_parts_screen = new Scene(root);
       
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        app_stage.setScene(yes_no_add_parts_screen);
      
        app_stage.show();
      
    }
    
    ///////////////////
    
     @FXML
    void AddProductsButtonHandler(ActionEvent event) throws IOException{

       
        ////////////////////// Need to find a way to adjust the Part Invetory when taking from main inventory to create a product
         
         if(ProductsTable1.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Parts list to modify.");
                    alert.showAndWait();
                    
                    return;
          }
         
        
        Part part = ProductsTable1.getSelectionModel().getSelectedItem();
       
        
        data2.add(part);
        
        
        
        ProductsTable2.setItems(data2);
        ProductsTable2.getSelectionModel().select(part);
    
}

    
}
