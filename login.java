/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;

/**
 *
 * Akash Sivapalan
 * 500897795
 */
public class login extends Application {
    private Customer customer;
    private Manager manager;
    
    @Override
    public void start(Stage primaryStage) {                      //opens login portal
        manager= new Manager("admin","admin","Manager");
       
        
        Button login = new Button("Login");
        Button Exit = new Button("Exit");
        Label username = new Label("Username: ");
        TextField usernameInput = new TextField();
        Label password = new Label("Password: ");
        TextField passwordInput = new TextField();  
        Label role = new Label("Role: ");
        TextField roleInput = new TextField();  
        
        GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY,Insets.EMPTY))); 
 
        
        root.addRow(1,username,usernameInput);
        root.addRow(2,password,passwordInput);
        root.addRow(3,role,roleInput);
        root.addRow(4,login);
        root.addRow(5,Exit);
        
        
                Scene scene = new Scene(root,700, 500);
                primaryStage.setTitle("Login Portal");
                primaryStage.setScene(scene);
                primaryStage.show(); 
                
                
                
        login.setOnAction(new EventHandler<ActionEvent>() {    //logs into either manager or customer when correct account information is entered
            
            @Override
            public void handle(ActionEvent event) {
                String usernameIn = usernameInput.getText();
                String passwordIn = passwordInput.getText();
                String roleIn = roleInput.getText();
                
                if(usernameIn.equals(manager.getUser())&& passwordIn.equals(manager.getPass())&&roleIn.equalsIgnoreCase(manager.getRole())){  //opens manager portal
                   Alert("A manager has logged in","Welcome");
                  
    
                   root.getChildren().clear();
                   
                    Button AddCustomer = new Button("Add Customer");
                    Button DeleteCustomer = new Button("Delete Customer");
                    Button Logout = new Button("Logout");
                    Label username = new Label("Username: ");
                    TextField usernameInput = new TextField();
                    Label password = new Label("Password ");
                    TextField passwordInput = new TextField();  

        
                    GridPane root = new GridPane();
                    root.setBackground(new Background(new BackgroundFill(Color.MAGENTA, CornerRadii.EMPTY,Insets.EMPTY))); 
        
                    root.addRow(1,username,usernameInput);
                    root.addRow(2,password,passwordInput);
                    root.addRow(3,AddCustomer);
                    root.addRow(4,DeleteCustomer);
                    root.addRow(5,Logout);
                    
                     Scene scene = new Scene(root, 700, 500);
                    primaryStage.setTitle("Manager Portal");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                
            AddCustomer.setOnAction(new EventHandler<ActionEvent>() {   // adds customer if not already added and username isn't taken by another account
            
            @Override
            public void handle(ActionEvent event) {
                String usernameIn = usernameInput.getText();
                String passwordIn = passwordInput.getText();
                
                if(usernameIn.equals("")|| passwordIn.equals("")) 
                    Alert("Enter a customer's account", "Error");
                else
                    manager.AddCustomer(usernameIn,passwordIn);
                
            }
        });

            DeleteCustomer.setOnAction(new EventHandler<ActionEvent>() {           // delete customer if it exists
            
            @Override
            public void handle(ActionEvent event) {
                String usernameIn = usernameInput.getText();
                String passwordIn = passwordInput.getText();
       
                
                if(usernameIn.equals("")|| passwordIn.equals("")) {
                       Alert("Enter a customer's account", "Error");
                }
                else
                    manager.deleteCustomer(usernameIn,passwordIn);
                
            }
        });
            
        Logout.setOnAction(new EventHandler<ActionEvent>() {   // logs out of manager account, returns to login portal
            
            @Override
            public void handle(ActionEvent event) {
                login LoginPortal = new login();
                LoginPortal.start(primaryStage);
                Alert("You have been logged out","Goodbye Manager");
                
                  
            }
        });     
            
                    
                
                }
                else if(roleIn.equalsIgnoreCase("Manager")){
                   Alert("Manager account does not exist","Error");
                   
                }
                
                else if(roleIn.equalsIgnoreCase("Customer")){                         
                    if(Customer.check(usernameIn,passwordIn)){                              // checks if information is right to log into a customer account
                        Alert( usernameIn + " has logged in!", "Welcome" + usernameIn);

                   
                   customer = new Customer(usernameIn);
                   
                   root.getChildren().clear();
                   
                    Button Order = new Button("Purchase");                                  // creates customer portal
                    Button Deposit = new Button("Deposit");
                    Button Withdraw = new Button("Withdraw");
                    Button display = new Button("Display Balance");
                    Button logoutCustomer = new Button("Logout");
                    Label orderlbl = new Label("Order: ");
                    TextField orderInput = new TextField();
                    Label depositlbl= new Label("Deposit: ");
                    TextField depositInput = new TextField();  
                    Label withdrawlbl= new Label("Withdraw: ");
                    TextField withdrawInput = new TextField(); 

                    

        
                    GridPane root = new GridPane();
                    root.setBackground(new Background(new BackgroundFill(Color.TEAL, CornerRadii.EMPTY,Insets.EMPTY))); 
        
                    root.addRow(1,depositlbl,depositInput,Deposit);
                    root.addRow(2,withdrawlbl,withdrawInput,Withdraw);
                    root.addRow(3,orderlbl,orderInput,Order);
                    root.addRow(4,display);
                    root.addRow(5,logoutCustomer);
                    
                    Scene scene = new Scene(root, 700, 500);
                    primaryStage.setTitle("Customer Portal");
                    primaryStage.setScene(scene);
                    primaryStage.show();

                    Deposit.setOnAction(new EventHandler<ActionEvent>() {                         // deposits money into customer's bank balance
                    @Override
                    public void handle(ActionEvent event) {
                            try{
                            String deposit = depositInput.getText();
                            double balance = customer.Deposit(Double.parseDouble(deposit));
                            Alert("Your Balance: $" + balance, "Deposit Transaction Occured");
                   
                            customer.SaveData();
                            
                            }catch(Exception e){
                                Alert("Invalid Input","Error");
                            }finally{
                                depositInput.clear();
                            }
                            }
                    });  

                    display.setOnAction(new EventHandler<ActionEvent>() {                          // displays current bank balance
                    @Override
                    public void handle(ActionEvent event) {
                            Alert("$" + customer.getBalance(),"Your Balance");
                        }
                    });  
  
                    Withdraw.setOnAction(new EventHandler<ActionEvent>() {                     // Withdraws money from bank balance if possible to withdraw that much money
                    @Override
                    public void handle(ActionEvent event) {
                            try{
                            double withdrawal = Double.parseDouble(withdrawInput.getText());
                            
                            if(customer.VerifyFunds(withdrawal)){
                                double balance = customer.Withdraw(withdrawal);
                                Alert("Your Balance: $" + balance, "Withdrawal Transaction Occured");

                                customer.SaveData();
                                
                            }
                            else {
                                    Alert("Funds required for this transaction is unavailable", "Error");
                                    }
                            }catch(Exception e){
                                Alert("Invalid Input","Error");
                            }finally{
                                withdrawInput.clear();
                            }
                        }
                    });
                    
                    Order.setOnAction(new EventHandler<ActionEvent>() {                                                 // for customer to make purchases and applies fees depending on customers rankning
                    @Override
                    public void handle(ActionEvent event) {
                            try{
                            double order = Double.parseDouble(orderInput.getText());
                            double price = order+customer.getFee();
                            
                            if((customer.VerifyFunds(price))&&(order>=50)){
                                double balance = customer.MakeOrder(order);
                                Alert("Your Balance: $" + balance, "Online Transaction Occured");
                              
                                customer.SaveData();
                                
                            }
                            else if(order<50){
                                    Alert("Enter an order of $50 or more", "Error");
                            }
                            else if((customer.VerifyFunds(price))==false){
                                Alert("Funds required for this transaction are unavailable", "Error");
                            }
                            }catch(Exception e){
                                Alert("Invalid Input","Error");
                            }finally{
                                orderInput.clear();
                            }
                                
                            
                                
                        }
                    });
                    
                    logoutCustomer.setOnAction(new EventHandler<ActionEvent>() {                  // logs out of customer
                    @Override
                    public void handle(ActionEvent event) {
                            login LoginPortal = new login();
                            LoginPortal.start(primaryStage);
                            Alert("You have been logged out","Goodbye Customer");
                         
                        }
                    });     
            
                            
                    }
                    else{
                        Alert("Customer does not exist","Error");
                    }
                }
                    

            }
        });
        
        Exit.setOnAction(new EventHandler<ActionEvent>() {                 // exits program
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                  
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                    // starts program
        launch(args);
    }
    
    public void Alert(String message,String Title){               // opens a message box depending on what action has occured for confirmation, updates, and errors
                   Label loggedIn = new Label(message);
                   loggedIn.setWrapText(true);
                   
                   
                   
                   Alert a = new Alert(Alert.AlertType.INFORMATION);
                   a.setHeaderText("");
                   a.setTitle(Title);
                   a.getDialogPane().setContent(loggedIn);
                   a.show(); 
}
}
