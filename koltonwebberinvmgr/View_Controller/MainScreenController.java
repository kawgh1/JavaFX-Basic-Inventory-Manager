/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koltonwebberinvmgr.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import koltonwebberinvmgr.Model.InhousePart;
import koltonwebberinvmgr.Model.Inventory;
import koltonwebberinvmgr.Model.Part;
import koltonwebberinvmgr.Model.Product;




/**
 * FXML Controller class
 *
 * @author K
 */
public class MainScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    @FXML
    private TextField PartsSearchField;
    
     @FXML
     TableView<Product> MainProductsTable;
   
    @FXML
     TableView<Part> MainPartsTable;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PartPriceCostPerUnit;

    
    @FXML
    private Button ModifyParts;

    @FXML
    private TextField ProductsSearchField;

    @FXML
    private TableColumn<Product, Integer> ProductID;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;

    @FXML
    private TableColumn<Product, Double> ProductPricePerUnit;

    @FXML
    private Button ModifyProducts;

    
    ///////////////////////////////////////////////////////////////////////////////////
    Inventory inventory1 = new Inventory(); // Main Parts Talble inventory change later
    
    Inventory inventory2 = new Inventory(); // Main Products Table inventory change later
    private Object InhousePart;
    private Object OutsourcedPart;
    
   
    public static Part partToModify;
    public static Product productToModify;
    
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MainPartsTable.setItems(inventory1.getAllParts());
    
        // Column references
        //setvalue factory specify what object to get from the table

        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));

        PartName.setCellValueFactory(new PropertyValueFactory<>("partName"));

        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));

        PartPriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));


        MainPartsTable.getSortOrder().add(PartID);

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////

        MainProductsTable.setItems(inventory2.getAllProducts());

        ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));

        ProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));

        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));

        ProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        MainProductsTable.getSortOrder().add(ProductID);
        
    }    
    
    
  
     @FXML
    void AddProductsButtonHandler(ActionEvent event) throws IOException{
        
        
        Parent root = FXMLLoader.load(getClass().getResource("AddProductsScreen.fxml"));
        
        Scene add_products_page_scene = new Scene(root);
      
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        app_stage.setScene(add_products_page_scene);
      
        app_stage.show();
        
      
    }

@FXML
    private void AddPartsButtonHandler(ActionEvent event) throws IOException {
       
        
        Parent root = FXMLLoader.load(getClass().getResource("AddPartsScreen.fxml"));
        Scene add_parts_page_scene = new Scene(root);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        app_stage.setScene(add_parts_page_scene);
        app_stage.show();
       
    }
    
       @FXML
    void ModifyPartsButtonHandler(ActionEvent event) throws IOException{
        if(MainPartsTable.getSelectionModel().getSelectedItem() == null){
              
            Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("No item selected.");
                alert.setContentText("Please select an item from the Parts list to modify.");
                alert.showAndWait();

                return;
        }
          
        System.out.println(MainPartsTable.getSelectionModel().getSelectedItem().getClass());
        
         if (MainPartsTable.getSelectionModel().getSelectedItem() instanceof InhousePart){ 
             
            Part part=MainPartsTable.getSelectionModel().getSelectedItem();
             
             
             
             Stage stage;
             Parent root;
             stage=(Stage) ModifyParts.getScene().getWindow();
             FXMLLoader loader=new FXMLLoader(getClass().getResource(
                     "ModifyPartsScreen.fxml"));
             root = loader.load();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
             ModifyPartsScreenController controller = loader.getController();
             controller.setPart(part);
             partToModify = MainPartsTable.getSelectionModel().getSelectedItem();
            }
         
          else{      
             
            Part part =MainPartsTable.getSelectionModel().getSelectedItem();
            Stage stage; 
            Parent root;
            stage=(Stage) ModifyParts.getScene().getWindow();
            FXMLLoader loader=new FXMLLoader(getClass().getResource(
                    "ModifyPartsScreenOut.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            ModifyPartsScreenOutController controller = loader.getController();
            controller.setPart(part);
            partToModify = MainPartsTable.getSelectionModel().getSelectedItem();
         }
         }


   

    @FXML
    void DeletePartsButtonHandler(ActionEvent event) {
        
        if(MainPartsTable.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Parts table to remove.");
                    alert.showAndWait();
                    
                    return;
          }
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Remove selected item from the Parts table?");
                    alert.setContentText("This action cannot be undone.");
                    alert.showAndWait();
                    
                    if(alert.getResult() == ButtonType.CANCEL){
                        
                      alert.close();
                      return;
                        
                        
                    }

          ObservableList<Part> data2 = MainPartsTable.getItems();
          data2.remove(MainPartsTable.getSelectionModel().getSelectedItem());
          
          MainPartsTable.setItems(data2);
         
    }

    @FXML
    void DeleteProductsButtonHandler(ActionEvent event) {
        
          if(MainProductsTable.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Products table to remove.");
                    alert.showAndWait();
                    
                    return;
          }
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Remove selected item from the Products table?");
                    alert.setContentText("This action cannot be undone.");
                    alert.showAndWait();
                    
                    if(alert.getResult() == ButtonType.CANCEL){
                        
                      alert.close();
                      return;
                        
                        
                    }

          ObservableList<Product> data3 = MainProductsTable.getItems();
          data3.remove(MainProductsTable.getSelectionModel().getSelectedItem());
          
          MainProductsTable.setItems(data3);

    }

    
    @FXML
    void ExitButtonHandler(ActionEvent event)  {
            Platform.exit();
    }

 

    @FXML
    void ModifyProductsButtonHandler(ActionEvent event) throws IOException {
       
        if(MainProductsTable.getSelectionModel().getSelectedItem() == null){
              
              Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("No item selected.");
                    alert.setContentText("Please select an item from the Products list to modify.");
                    alert.showAndWait();
                    
                    return;
          }
          
          
        if (MainProductsTable.getSelectionModel().getSelectedItem().hasList(MainProductsTable.getSelectionModel().getSelectedItem()) == true){ 


            Product  product = MainProductsTable.getSelectionModel().getSelectedItem();
             
             
             
             Stage stage;
             Parent root;
             stage=(Stage) ModifyProducts.getScene().getWindow();
             FXMLLoader loader=new FXMLLoader(getClass().getResource(
                     "ModifyProductsScreen.fxml"));
             root = loader.load();
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
             ModifyProductsScreenController controller = loader.getController();
             controller.setProduct(product);
             
             controller.PartsTable1.setItems(product.getAssociatedParts());
             
            productToModify = MainProductsTable.getSelectionModel().getSelectedItem();
         
       
    }
    }

    @FXML
    void PartsSearchButtonHandler(ActionEvent event) {
        
        String searchItem = PartsSearchField.getText();
        boolean found = false;
        try {
                    int partID = Integer.parseInt(searchItem);
                    for( Part p : MainPartsTable.getItems()){
                
                                if(p.getPartID() == partID){
                                    
                                    

                                    System.out.println("PartID " + partID + " has been found!");
                                    found = true;
                                    MainPartsTable.getSelectionModel().select(p);
                                    MainPartsTable.scrollTo(p);
                                    
                                    
                                    System.out.println(MainPartsTable.getSelectionModel().getSelectedCells());
                                    
                                }
            }
            
            if (found == false){
                
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Part not found.");
                    alert.setContentText("Please try again using a different parameter.");
                    alert.showAndWait();
                    
            }
            
        }
        catch(NumberFormatException e) {
            //e.printStackTrace();
            
                    for (Part p : MainPartsTable.getItems()){

                                if( p.getPartName().equalsIgnoreCase(searchItem)){
                                    System.out.println("Part " + p.getPartName() + " has been found!");
                                    found = true;
                                    MainPartsTable.getSelectionModel().select(p);
                                    MainPartsTable.scrollTo(p);
                                    
                                    System.out.println(MainPartsTable.getSelectionModel().getSelectedCells());
                                    
                                }
                    }
                    
          if (found == false){
              Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Part not found.");
                    alert.setContentText("Please try again using a different parameter.");
                    alert.showAndWait();
          }          
        }
        

    }

    @FXML
    void ProductSearchButtonHandler(ActionEvent event) {
        
         String searchItem = ProductsSearchField.getText();
        boolean found = false;
        try {
                    int productID = Integer.parseInt(searchItem);
                    for( Product p : MainProductsTable.getItems()){
                
                                if(p.getProductID() == productID){
                                    
                                    

                                    System.out.println("ProductID " + productID + " has been found!");
                                    found = true;
                                    MainProductsTable.getSelectionModel().select(p);
                                    MainProductsTable.scrollTo(p);
                                    System.out.println(MainProductsTable.getSelectionModel().getSelectedCells());
                                    }
            }
            
            if (found == false){
                
                Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found.");
                    alert.setContentText("Please try again using a different parameter.");
                    alert.showAndWait();
                    
            }
            
        }
        catch(NumberFormatException e) {
            //e.printStackTrace();
            
                    for (Product p : MainProductsTable.getItems()){

                                if( p.getProductName().equalsIgnoreCase(searchItem)){
                                    System.out.println("Product " + p.getProductName() + " has been found!");
                                    found = true;
                                    MainProductsTable.getSelectionModel().select(p);
                                    MainProductsTable.scrollTo(p);
                                    System.out.println(MainProductsTable.getSelectionModel().getSelectedCells());
                                    
                                }
                    }
                    
          if (found == false){
              Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found.");
                    alert.setContentText("Please try again using a different parameter.");
                    alert.showAndWait();
          }          
        }
        

    }

}


