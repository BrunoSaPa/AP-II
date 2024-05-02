/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buttons;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author brunosanchezpadilla
 */
public class Buttons extends JFrame {

    //Elementos
     private  JButton boton1, boton2, boton3, boton4, boton5, boton6;

    private GroupLayout gl;
    
    
    public Buttons()
    {
        super();
        configurar();
    }

    private void configurar() {
                  //Tamaño
        this.setSize(1000, 650); //setSize(largo(px), ancho(px))

        //Titulo
        this.setTitle("Botones");

        //Se deternga el programa una vez se cierra la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //DISPOSE_ON_CLOSE ==> da control a las ventanas secundarias
        //EXIT_ON_CLOSE ==> se cierra el programa
        //Contextualizar el lenguaje. ESpecificar exáctamente que áreas rellenar.
        this.getContentPane().setBackground(Color.decode("#63513d")); //Se le asigna como background al Panel de trabajo (pane: el área que se obser)
        
        this.setResizable(true);

       //Botón es un objeto, El objeto se agrega al frame

       
       //BOTONES
       
       //Primer botón
        boton1 = new JButton("Boton1");  //Asigna su referenca
       this.add(boton1);  //Se agrega
       
       //Segundo botón
       boton2 = new JButton("Boton2");
       this.add(boton2);
        //Tercer botón
       boton3 = new JButton("Boton3");   
       this.add(boton3);
       
        //Cuarto botón
       boton4 = new JButton("Boton4");
       this.add(boton4);
       
       //Quinto boton
       boton5 = new JButton("Boton5");
       this.add(boton5);
       
       //Sexto Boton
       boton6 = new JButton("Boton6");
       this.add(boton6);
       
       
         //LAYOUT
       
       gl = new GroupLayout(this.getContentPane());
       
       gl.setHorizontalGroup
        (
                gl.createSequentialGroup()
                    .addGroup
                (
                        gl.createParallelGroup()
                            .addComponent(boton1)
                            .addComponent(boton4)
                )
                    .addGroup
                    (
                       gl.createParallelGroup()
                            .addComponent(boton2)
                            .addComponent(boton5) 
                    )
                        .addGroup
                    (
                       gl.createParallelGroup()
                            .addComponent(boton3)
                    )
        
        );
       
       
       gl.setVerticalGroup
        (
                        gl.createParallelGroup()
                                .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(boton1)
                                        .addComponent(boton4)
                                )
                                .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(boton2)
                                        .addComponent(boton5)
                                )
                                        .addComponent(boton3, 10, 100, 300)
        );
       /*
       gl.setVerticalGroup
        (
                gl.createSequentialGroup()
                    .addGroup
                (
                        gl.createParallelGroup()
                            .addComponent(boton1)
                            .addComponent(boton2)
                            .addComponent(boton3, 10, 100, 300)                       
                )
                    .addGroup
                    (
                       gl.createParallelGroup()
                            .addComponent(boton4)   
                            .addComponent(boton5)
                    )
        
        );*/
       
       
       //Establecer el layout
       this.setLayout(gl);
       pack();
             
    }
    
    
       public static void main(String[] args) {
        Buttons ventana = new Buttons();
        
        // Hacer visible la ventana
        ventana.setVisible(true);
    }
}
