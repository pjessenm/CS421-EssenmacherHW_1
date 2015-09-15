package essenmacher_hw1;

import java.math.BigDecimal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phile
 */
public class Pizza {
    private String flavor, size, toppings, lastName;
    
    //Flavor prices
    private final static double PEP = 5.00;
    private final static double HAW = 7.00;
    private final static double VEG = 6.00;
    private final static double MEAT = 7.00;
    private final static double SPEC = 8.00;
        
    //Size prices
    private final static double MED = 2.00;
    private final static double LARGE = 4.00;
        
    //Topping prices
    private final static double TOPSMALL = 0.50;
    private final static double TOPMED = 1.00;
    private final static double TOPLARGE = 1.50;
    
    //Sales tax
    private final static double TAX_RATE = 1.06;

    
    Pizza(String inFlavor, String inSize, String inToppings, 
            String inLastName){
        flavor = inFlavor;
        size = inSize;
        toppings = inToppings;
        lastName = inLastName;
    }
    
    public String getFlavor(){
        return flavor;
    }
    
    public String getSize(){
        return size;
    }
    
    public String getToppings(){
        return toppings;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public BigDecimal getPrice(){
        double pizzaPrice = 0;
        
        switch(flavor)
        {
            case "Pepperoni": 
                pizzaPrice += PEP;
                break;
            case "Hawaiian":
                pizzaPrice += HAW;
                break;
            case "Veggie":
                pizzaPrice += VEG;
                break;
            case "Meat":
                pizzaPrice += MEAT;
                break;
            case "Special":
                pizzaPrice += SPEC;
                break;
        }
            
        //Adds additional fees for different size pizzas,
        //including topping fees
        switch(size)
        {
            case "Medium":
                pizzaPrice += MED + TOPMED;
                break;
            case "Large":
                pizzaPrice += LARGE + TOPLARGE;
                break;
            //If the pizza is small
            default:
                pizzaPrice += TOPSMALL;
                break;
        }
        
        //Adding tax to the price
        pizzaPrice *= TAX_RATE;
        
        //Truncating the number to match currency format.    
        BigDecimal truncatedPrice = 
                new BigDecimal(pizzaPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
            
        return truncatedPrice;
        
    }
}
