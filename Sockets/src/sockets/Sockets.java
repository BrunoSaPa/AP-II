/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sockets;

/**
 *
 * @author brunosanchezpadilla
 */

import java.io.IOException;
import sockets.Cliente;

public class Sockets {

    public static void main(String[] args) throws IOException
    {
        Cliente cli = new Cliente(); 

        System.out.println("Iniciando cliente\n");
        cli.startClient(); 
    }
    
}
