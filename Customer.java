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

public class Customer {
    private String user,pass;
    private double balance;
    private Rank myRank=new Silver();
    
    public void setRank(Rank r){

     myRank =r;
    
    }
    
    public void changeRank(){
        myRank.changeRank(this);
    }

    
    public Customer(String username){
        String fileName;
        boolean x=false;
        File customerFile = new File(username +".txt");
        int i =0;
        
        this.user=username;
        
        if(customerFile.exists()){
        try{
            Scanner input = new Scanner(customerFile);
        
            while(input.hasNext()){
                
                String s = input.next();

                if(i==1)
                    this.pass= s;
                else if(i==2)
                    this.balance= Double.parseDouble(s);
                i++;
            }
          input.close();   
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
                }
        if (balance<10000)
            myRank= new Silver();
        else if(balance<=20000)
            myRank=new Gold();
        else
            myRank=new Platinum();
            
    
        }
        
    }
    
    public static  boolean check(String user,String pass){
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
        
        
        return x;
    }
    
    public double Deposit(double deposit){
        balance = balance+deposit;
        changeRank();
        return balance;
    }
    
    
    public double Withdraw(double withdrawal){
        
        balance = balance-withdrawal;
        changeRank();
        return balance;
        
    }
    
    public boolean VerifyFunds(double funds){
        if (balance>= funds){
            return true;
        }
        return false;
    }
    
    public double getBalance(){
        
        return balance;
        
    }
    
    public double getFee(){
        changeRank();
        return myRank.Fee();
    }
    
    public double MakeOrder(double price){
   
        changeRank();
        balance = balance -price-myRank.Fee();
        changeRank();
        return balance;
    }
    public void SaveData(){
        String fileName;
        PrintWriter pw = null;
        Writer fw;
        
        File customerFile = new File(user +".txt");
        
        if(customerFile.delete()){
            try{
                fw = new FileWriter(user+".txt",true);
                pw = new PrintWriter(fw);
            } catch(IOException e){
                System.out.println("Error");
                e.printStackTrace(System.err);
            }
            

            pw.println(user);
            pw.println(pass);
            pw.println(balance);
            pw.close();
            
        }
        changeRank();
    }

}
