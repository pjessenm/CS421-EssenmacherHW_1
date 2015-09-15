/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essenmacher_hw1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author phile
 */
public class Essenmacher_HW1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {    
        //Creating Panes
        GridPane rootGrid = new GridPane();
        TabPane rootTab = new TabPane();
        Tab orderTab = new Tab("Order Pizza");
        Tab viewTab = new Tab("View Orders");
        viewTab.setClosable(false);
        orderTab.setClosable(false);
        
        //Creating elements for the name row of the grid
        HBox hboxLastName = new HBox();
        Label lblLastName = new Label("Last name:");
        TextField txtLastName = new TextField();
        txtLastName.setPrefColumnCount(20);
        
        //Creating elements for the toppings row of the grid
        HBox hboxToppings = new HBox();
        ComboBox<String> cmbFlavor = new ComboBox<String>();
        ComboBox<String> cmbSize = new ComboBox<String>();
        ComboBox<String> cmbToppings = new ComboBox<String>();
        Label lblTotalPrice = new Label("Price");
        
        cmbFlavor.getItems().addAll("Pepperoni", "Hawaiian", "Veggie", 
                "Meat", "Special");
        cmbSize.getItems().addAll("Small", "Medium", "Large");
        cmbToppings.getItems().addAll("Extra Cheese", "Green Pepper", "Onion",
                "Mushroom", "Black Olive", "Tomato", "Jalapeno Peppers");
        
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
                
                //Confirmation alert after ordering pizza
                Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION, 
                orderMessage);
                confirmationBox.setTitle("Thanks for your order!");
                confirmationBox.show();
            }
        });
        
  
        //Adding elements to the HBox for name row
        hboxLastName.getChildren().add(lblLastName);
        hboxLastName.getChildren().add(txtLastName);
        
        //Adding elements to the HBox for toppings row
        hboxToppings.getChildren().addAll(cmbFlavor, cmbSize, cmbToppings,
                lblTotalPrice);
        
        //Adding the HBoxes to the GridPane
        rootGrid.add(hboxLastName, 0, 0);
        rootGrid.add(hboxToppings, 0, 1);
        rootGrid.add(btnOrder, 0, 2);
        
        //Adding the GridPane to the orderTab
        orderTab.setContent(rootGrid);
        
        //Adding the tabs to the Tab Pane
        rootTab.getTabs().add(orderTab);
        rootTab.getTabs().add(viewTab);
        
        Scene scene = new Scene(rootTab, 500, 300);
        
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
