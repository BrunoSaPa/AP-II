/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basededatos.bd;

/**
 *
 * @author brunosanchezpadilla
 */
public class bd {
    
    public bd(){
    try{
    Class.forName("com.mysql.cd.jdbc.driver");
    DriverManager.getConnection()
    }
    catch(ClassNotFoundException e){
    }
    }
}
