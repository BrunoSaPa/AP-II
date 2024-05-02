/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilos;

/**
 *
 * @author brunosanchezpadilla
 */

public class Cuenta implements Runnable{
    
    public int id;
    
    public Cuenta(int id){
    this.id = id;
    }
    
    @Override
    public void run(){
        String Color;
        if(id == 1){
            Color = "\u001B[32m";
        }
        else if (id == 2){
            Color =  "\u001B[34m";
       }
        else{
        Color = "\u001B[0m";
        }
    for(int i = 0; i<1000; i++)
        System.out.println(Color + "id:" + id + " - " + i + "\u001B[0m");
    }
    
}
