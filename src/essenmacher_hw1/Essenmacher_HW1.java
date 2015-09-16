/*
 * Name: Phil Essenmacher
 * Homework: 1
 * Course, semester: CS 421
 * Instructor: Cho
 * Date Finished: 9.15.15
 */
package essenmacher_hw1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Phil Essenmacher
 */
public class Essenmacher_HW1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {    
        //Creating Panes
        BorderPane rootViewPane = new BorderPane();
        GridPane rootGrid = new GridPane();
        TabPane rootTab = new TabPane();
        Tab orderTab = new Tab("Order Pizza");
        Tab viewTab = new Tab("View Orders");
        viewTab.setClosable(false);
        orderTab.setClosable(false);
        
        //Creating table for orders
        ObservableList<Pizza> orderList = FXCollections.observableArrayList();
        TableView<Pizza> orderTable = new TableView<Pizza>();
        TableColumn<Pizza, String> flavorColumn = new TableColumn<Pizza, String>("Flavor");
        flavorColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("flavor"));
        TableColumn<Pizza, String> sizeColumn = new TableColumn<Pizza, String>("Size");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("size"));
        TableColumn<Pizza, String> toppingsColumn = new TableColumn<Pizza, String>("Toppings");
        toppingsColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("toppings"));
        TableColumn<Pizza, String> priceColumn = new TableColumn<Pizza, String>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("price"));
        TableColumn<Pizza, String> nameColumn = new TableColumn<Pizza, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("lastName"));
        orderTable.getColumns().setAll(flavorColumn, sizeColumn, toppingsColumn,
                priceColumn, nameColumn);
        
        //Creating elements for the name row of the  grid
        HBox hboxLastName = new HBox();
        Label lblLastName = new Label("Last name:");
        TextField txtLastName = new TextField();
        txtLastName.setPrefColumnCount(20);
        
        //Creating elements for the toppings row of the grid
        HBox hboxToppings = new HBox();
        ComboBox<String> cmbFlavor = new ComboBox<>();
        ComboBox<String> cmbSize = new ComboBox<>();
        ComboBox<String> cmbToppings = new ComboBox<>();
        Label lblTotalPrice = new Label("Price");
        
        cmbFlavor.getItems().addAll("Pepperoni", "Hawaiian", "Veggie", 
                "Meat", "Special");
        cmbSize.getItems().addAll("Small", "Medium", "Large");
        cmbToppings.getItems().addAll("Extra Cheese", "Green Pepper", "Onion",
                "Mushroom", "Black Olive", "Tomato", "Jalapeno Peppers");
        
        //Creating HBox for order row of grid
        HBox hboxOrder = new HBox();
        
        //Creating button for ordering
        Button btnOrder = new Button("Place Order");
        
        btnOrder.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Pizza pizza = new Pizza(cmbFlavor.getValue(), 
                        cmbSize.getValue(), cmbToppings.getValue(),
                        txtLastName.getText());
                
                String orderMessage;
                orderMessage = ("Thank you for your order of a "
                        + pizza.getSize() + " " + pizza.getFlavor() 
                        + " with " + pizza.getToppings() + ". Your total is $" 
                        + pizza.getPrice() + "\n" + "The name on your order is "
                        + pizza.getLastName() + ".");
                
                lblTotalPrice.setText("$" + pizza.getPrice().toString());
                
                //Adding pizza to the orderList
                orderList.add(pizza);
                
                //Adding list of pizzas to the order table
                orderTable.setItems(orderList);
                
                //Confirmation alert after ordering pizza
                Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION, 
                orderMessage);
                confirmationBox.setTitle("Thanks for your order!");
                confirmationBox.show();
                
                //Clearing out the order screen
                txtLastName.setText("");
                cmbFlavor.setValue("");
                cmbSize.setValue("");
                cmbToppings.setValue("");
            }
        });
        
  
        //Adding elements to the HBox for name row
        hboxLastName.getChildren().add(lblLastName);
        hboxLastName.getChildren().add(txtLastName);
        
        //Adding elements to the HBox for toppings row
        hboxToppings.getChildren().addAll(cmbFlavor, cmbSize, cmbToppings,
                lblTotalPrice);
        
        //Adding elements to HBox for order row
        hboxOrder.getChildren().add(btnOrder);
        
        //Adding the HBoxes to the GridPane
        rootGrid.add(hboxLastName, 0, 0);
        rootGrid.add(hboxToppings, 0, 1);
        rootGrid.add(hboxOrder, 0, 2);       
        
        //Adding the GridPane to the orderTab
        orderTab.setContent(rootGrid);
        
        //Adding the orderTable to the viewTab
        rootViewPane.setCenter(orderTable);
        viewTab.setContent(rootViewPane);
        
        //Adding the tabs to the Tab Pane
        rootTab.getTabs().add(orderTab);
        rootTab.getTabs().add(viewTab);
        
        //Creating main scene and adding stylesheet
        Scene scene = new Scene(rootTab, 500, 300);
        String css = Essenmacher_HW1.class.getResource("pizzaOrderStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.setTitle("Order Some Pizza!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
