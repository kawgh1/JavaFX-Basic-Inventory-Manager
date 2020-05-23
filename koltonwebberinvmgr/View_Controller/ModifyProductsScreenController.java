/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import koltonwebberinvmgr.Model.Inventory;
import koltonwebberinvmgr.Model.Part;
import koltonwebberinvmgr.Model.Product;
import static koltonwebberinvmgr.View_Controller.MainScreenController.productToModify;

/**
 * FXML Controller class
 *
 * @author K
 */
public class ModifyProductsScreenController implements Initializable {

      @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    @FXML
    private TextField ProductIDField;

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

    @FXML
    private TableView<Part> PartsTable;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PartPricePerUnit;

    @FXML
     TableView<Part> PartsTable1;

    @FXML
    private TableColumn<Part, Integer> PartID1;

    @FXML
    private TableColumn<Part, String> PartName1;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel1;

    @FXML
    private TableColumn<Part, Double> PartPricePerUnit1;

    @FXML
    private TextField PartsSearchField;
    
    @FXML
     TableView<Product> MainProductsTable;
    
    @FXML
    TableColumn<Product, Integer> ProductID;
    
    @FXML
    private TableColumn<Product, String> ProductName;
    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;
    @FXML
    private TableColumn<Product, Double> ProductPricePerUnit;

    @FXML
    private Button PartsSearch;
    
    private Product product;

    
    
     Inventory inventory1 = new Inventory(); 
      Inventory inventory2 = new Inventory(); 
     
      private static ObservableList<Part> data2 = FXCollections.observableArrayList();
      private static ObservableList<Part> data3 = FXCollections.observableArrayList();
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                  PartsTable.setItems(inventory1.getAllParts());
                
                  PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        
                  PartName.setCellValueFactory(new PropertyValueFactory<>("partName"));
   
                  PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      
            
                  PartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
            
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
                  PartsTable1.setItems(null);
           
                  PartID1.setCellValueFactory(new PropertyValueFactory<>("partID"));
        
                  PartName1.setCellValueFactory(new PropertyValueFactory<>("partName"));
   
                  PartInventoryLevel1.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      
                  PartPricePerUnit1.setCellValueFactory(new PropertyValueFactory<>("price"));
        // TODO
    }    
    
    public void setProduct(Product product){
        this.product = product;
         
         ProductIDField.setText(Integer.toString(product.getProductID()));
         ProductNameField.setText(product.getProductName());
        
         ProductInvField.setText(Integer.toString(product.getInStock()));
    
         ProductPriceField.setText(Double.toString(product.getPrice()));

         ProductMaxField.setText(Integer.toString(product.getMax()));

         ProductMinField.setText(Integer.toString(product.getMin()));
       
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
        Scene main_screen = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_screen);
        app_stage.show();

    }

    @FXML
    void PartsSearchButtonHandler(ActionEvent event) {
        
        String searchItem = PartsSearchField.getText();
        boolean found = false;
        try {
                    int partID = Integer.parseInt(searchItem);
                    for( Part p : PartsTable.getItems()){
                
                                if(p.getPartID() == partID){
                                    
                                    

                                    System.out.println("PartID " + partID + " has been found!");
                                    found = true;
                                    PartsTable.getSelectionModel().select(p);
                                    
                                    PartsTable.scrollTo(p);
                                    System.out.println(PartsTable.getSelectionModel().getSelectedCells());
//                                    
                                    
                                   
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
            
                    for (Part p : PartsTable.getItems()){

                                if( p.getPartName().equalsIgnoreCase(searchItem)){
                                    System.out.println("Part " + p.getPartName() + " has been found!");
                                    found = true;
                                    PartsTable.getSelectionModel().select(p);
                                    
                                    
                                    PartsTable.scrollTo(p);
                                    System.out.println(PartsTable.getSelectionModel().getSelectedCells());
                                    
                                    
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
    void SaveButtonHandler(ActionEvent event) throws IOException {
        
        Inventory inventory7 = new Inventory();
        
        String ID = ProductIDField.getText();
        String product1 = ProductNameField.getText();
        String inStock = ProductInvField.getText();
        String price = ProductPriceField.getText();
        String min = ProductMinField.getText();
        String max = ProductMaxField.getText();
        ObservableList<Part> associatedParts = PartsTable1.getItems();
        
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
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Ready?");
        alert.setContentText("Update this product's values with the new input values?");
        alert.showAndWait();
        
        
        if(alert.getResult() == ButtonType.CANCEL){
                        
                      alert.close();
                      return;
                        
                        
                    }
        
        Product product2 = new Product();
        product2.setProductID(Integer.parseInt(ID));
        product2.setProductName(product1);
        product2.setInStock(Integer.parseInt(inStock));
        product2.setPrice(Double.parseDouble(price));
        product2.setMax(Integer.parseInt(max));
        product2.setMin(Integer.parseInt(min));
        product2.setAssociatedParts(associatedParts);
        
         inventory2.addProduct(product2);
         
         inventory2.removeProduct(productToModify);
        
        
        
        
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
             //controller.MainProductsTable.getSortOrder().add(ProductID);



/*
The line above // is the error. Thinking to remove all the sorts from Controllers
and doing to sort on the MainScreen at initialization


*/


              controller.MainProductsTable.setItems(inventory2.getAllProducts());
               
               controller.MainProductsTable.getSelectionModel().select(product2);
               controller.MainProductsTable.scrollTo(product2);
               
       
    }
    
     @FXML
    void DeleteProductsButtonHandler(ActionEvent event) {
        
        PartsTable1.setItems(productToModify.getAssociatedParts());
        
        if(PartsTable1.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Parts list to modify.");
                    alert.showAndWait();
                    
                    return;
          }
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Remove selected part from current product?");
                    alert.setContentText("Please confirm.");
                    alert.showAndWait();
                    
                    if(alert.getResult() == ButtonType.CANCEL){
                        
                      alert.close();
                      return;
                        
                        
                    }
    Part part = PartsTable1.getSelectionModel().getSelectedItem();
       
        
        productToModify.getAssociatedParts().remove(part);
        
        
        
        PartsTable1.setItems(productToModify.getAssociatedParts());

    }
    
    @FXML
    void AddProductsButtonHandler(ActionEvent event) {
        
          if(PartsTable.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Parts list to modify.");
                    alert.showAndWait();
                    
                    return;
          }
         
        
        Part part = PartsTable.getSelectionModel().getSelectedItem();
       
        
        productToModify.getAssociatedParts().add(part);
        
        
        
        PartsTable1.setItems(productToModify.getAssociatedParts());
       
    }

}
