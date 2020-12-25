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
public class Platinum extends Rank{
    private double fee = 0.00;



        @Override
    public double Fee(){
        return fee;
        
    }
        @Override
        public void changeRank(Customer c){
         if (c.getBalance()<10000){
            c.setRank(new Silver());
          
         }
        else if(c.getBalance()<=20000)
            c.setRank(new Gold());
        else 
            c.setRank(new Platinum());
    }
}
