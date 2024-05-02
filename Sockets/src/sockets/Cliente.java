/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

/**
 *
 * @author brunosanchezpadilla
 */
import java.io.DataOutputStream;
import java.io.IOException;
import sockets.Conexion;

public class Cliente extends Conexion
{
    public Cliente() throws IOException{super("cliente");} 
    byte var[];

    public void startClient() 
    {
        try
        {
            String Mensaje = "Bruno";
            cs.getOutputStream().write(Mensaje.getBytes());
            System.out.println("Mensaje enviado al server: " + Mensaje);
            var = new byte[100];
            cs.getInputStream().read(var);
            String obj;
            obj = new String(var);
            System.out.println("Mensaje recibido del server: " + obj);
           
            
            cs.close();
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
