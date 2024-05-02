/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hilos;
import hilos.Cuenta;

/**
 *
 * @author brunosanchezpadilla
 */
public class Hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Thread x1,x2, x3;
       x1 = new Thread(new Cuenta(1));
       x2 = new Thread(new Cuenta(2));
       x3 = new Thread(new Cuenta(3));
       
       x1.start();
       x2.start();
       x3.start();
    }
    
}
