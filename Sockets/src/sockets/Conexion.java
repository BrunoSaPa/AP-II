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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexion
{
    private final int PUERTO = 6543; 
    private final String HOST = "192.168.137.99"; 
    protected String mensajeServidor; 
    protected ServerSocket ss; 
    protected Socket cs; 
    protected DataOutputStream salidaServidor, salidaCliente;

    public Conexion(String tipo) {
        try {
            cs = new Socket(HOST, PUERTO); // Intentar establecer la conexión.
        } catch (UnknownHostException e) {
            System.out.println("No se puede conectar con el servidor: Dirección IP del servidor no encontrada.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida al intentar conectar con el servidor.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}