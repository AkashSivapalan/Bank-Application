 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author 16477
 */

import java.util.Scanner;
import java.io.*;
import java.io.File;

/* Javadoc

@ Akash Sivapalan
@ 500897795

The responsibility of this class is to add and delete customer files.
This class is immutable all the variables are private and are strings

    Requires: Takes in strings for usernames and passwords
    Modifies: Nothing is modified
    Effect: Based on the username and password information entered by the user, a customer file is either created or deleted from that 
    data depending on the user's intereactions with the manager GUI
    


Abstraction Funstion:
AF(c)= a customer to add such that
c.getUsername() = username
c.getPassword() = password
c.getBalance() = 100 

Rep Invariant
RI(c) = (username.txt.exists() == false) 



*/

public class Manager {
    private String user,pass,role;
 
    
    
    public Manager(String user,String pass,String role){
        /*
        Requires: Takes in 3 strings from the login interface, the username, password, and role 
        Modifies: Set the value for the variables user,pass,and role on the object
        Effect: Chages the value of user,pass, and role of the object
        
        */
        this.user=user;
        this.pass=pass;
        this.role=role;
        
        
    }
    
    public boolean repOk(String username,String password){  // Rep Invariant
        String fileName;
        PrintWriter pw = null;
        Writer fw;
        
        File customerFile = new File(username +".txt");
        
        if (customerFile.exists()){
                return false;
            
        }
        
        return true;
}
    
    public String toString(String user,String pass){  // Abstraction function
        
        return user + "\n"+ pass +"\n"+ 100;
    }
    
    public String getUser(){
        /*
        Requires: Nothing is required
        Modifies: Nothing is modified
        Effect: Returns the value of the user string variable
        
        */
        return this.user;
               
    }
    
    public String getPass(){
                /*
        Requires: Nothing is required
        Modifies: Nothing is modified
        Effect: Returns the value of the pass string variable
        
        */
        return this.pass;
               
    }
    public String getRole(){
                /*
        Requires: Nothing is required
        Modifies: Nothing is modified
        Effect: Returns the value of the role string variable
        
        */
        return this.role;
               
    }
    
    public void AddCustomer(String user,String pass){
                /*
        Requires: Takes in two strings, a username and password entered by the user in the textbox on the manager portal interface
        Modifies: Nothing is modified
        Effect: Creates a textfile with information for a new customer in the program. The text file includes the username, password, and starting balance of $100 
        
        */
        String fileName;
        PrintWriter pw = null;
        Writer fw;
        
        File customerFile = new File(user +".txt");
        
        if (customerFile.exists()){
                login Login=new login();
                Login.Alert("Customer Username Already Exists", "Error");
            
        }
        
        else{
            try{
                fw = new FileWriter(user+".txt",true);
                pw = new PrintWriter(fw);
            } catch(IOException e){
                System.out.println("Error");
                e.printStackTrace(System.err);
            }
            

            pw.println(user);
            pw.println(pass);
            pw.println("100");
            pw.close();
                login Login=new login();
                Login.Alert("Customer Added", "Success");
        }
        
    }

    public void deleteCustomer(String user,String pass){
                /*
        Requires: Takes in two strings, a username and password entered by the user in the textbox on the manager portal interface
        Modifies: A customer file is deleted if the all the correct information for the username and password entered by the managers exist in the customers file
        Effect: Checks if the file exists with the username and then checks is the password is the same. Then it deletes fail if it is true
        
        */
        String fileName;
        boolean x=false;
        File customerFile = new File(user +".txt");
        int i = 0;
        if(customerFile.exists()){
        try{
            Scanner input = new Scanner(customerFile);
        
            while(input.hasNext()){
                
                String s = input.next();
          
                if(i==1)
                    x=s.equals(pass);
                i++;
            }
          input.close();   
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
                }

        }
        
       
        
        if(x==true){
            login Login=new login();
            if(customerFile.delete()){
                
                Login.Alert("Customer Deleted", "Success");
            }
        }
        else{
                login Login=new login();
                Login.Alert("Customer Does Not Exist", "Error");
        }
    }
    }
