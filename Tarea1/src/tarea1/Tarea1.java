/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea1;


import java.util.Scanner;

/**
 *
 * @author BRUNO
 */
public class Tarea1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
/*
        // Leer los dos números como Strings
        System.out.println("Ingrese el primer numero: ");
        String numero1 = scanner.nextLine();
        System.out.println("Ingrese el segundo numero: ");
        String numero2 = scanner.nextLine();

        // Determinar la longitud máxima de los arrays
        int maxLength = Math.max(numero1.length(), numero2.length());
        int arrayLength = (maxLength + 8) / 9; // Ajustar la longitud para bloques de hasta 9 dígitos

        // Convertir los números en bloques y almacenarlos en arrays
        int[] bloquesNum1 = convertirEnBloques(numero1, arrayLength);
        int[] bloquesNum2 = convertirEnBloques(numero2, arrayLength);

        // Sumar los bloques correspondientes manejando el acarreo
        int[] sumaBloques = sumarBloquesConAcarreo(bloquesNum1, bloquesNum2, arrayLength);   
        
        // Convertir la suma de bloques a un String representando el número final
        String sumaFinal = convertirBloquesAString(sumaBloques);
        System.out.println(sumaFinal);*/
        
        
        System.out.println("Ingrese la posicion del numero de Fibonacci a calcular: ");
        int posicion = scanner.nextInt();

        if (posicion == 0) {
            System.out.println("El numero de Fibonacci es: 0");
            return;
        } else if (posicion == 1) {
            System.out.println("El numero de Fibonacci es: 1");
            return;
        }

        int[] fibonacciPrevio = new int[30];
        fibonacciPrevio[29] = 0; 
        int[] fibonacciActual = new int[30]; 
        fibonacciActual[29] = 1;

        int[] fibonacciSiguiente;
        

            
        for (int i = 2; i <= posicion; i++) {
            fibonacciSiguiente = sumarBloquesConAcarreo(fibonacciActual, fibonacciPrevio, 30);

            // Preparar para el siguiente ciclo
            System.out.println("fibo siguiente");
            for(int j: fibonacciSiguiente){
                System.out.print(j + ",");
            }
            System.out.println();
                        System.out.println("fibo previo");
            for(int j: fibonacciPrevio){
                System.out.print(j + ",");
            }
            System.out.println();
            System.out.println("fibo actual");
            for(int j: fibonacciActual){
                System.out.print(j + ",");
            }
            System.out.println("terminado bloque "+i);
            
            fibonacciPrevio = fibonacciActual;
            fibonacciActual = fibonacciSiguiente;
        }
        

        String resultadoFibonacci = convertirBloquesAString(fibonacciActual);
        
        System.out.println("El numero de Fibonacci en la posicion " + posicion + " es: " + resultadoFibonacci);
    }

    private static int[] convertirEnBloques(String numero, int arrayLength) {
        int[] bloques = new int[arrayLength];
        int start = numero.length();
        for (int i = arrayLength - 1; i >= 0; i--) {
            int end = start;
            start = Math.max(end - 9, 0);
            if (start < end) {
                bloques[i] = Integer.parseInt(numero.substring(start, end));
            }
        }
        return bloques;
    }

    private static int[] sumarBloquesConAcarreo(int[] bloquesNum1, int[] bloquesNum2, int arrayLength) {
        int[] sumaBloques = new int[arrayLength];
        int acarreo = 0;

        for (int i = arrayLength - 1; i >= 0; i--) {
            int suma = bloquesNum1[i] + bloquesNum2[i] + acarreo;
            if (suma > 999999999) {
                acarreo = 1;
                suma -= 1000000000;
            } else {
                acarreo = 0;
            }
            sumaBloques[i] = suma;
        }

        if (acarreo > 0) {
            sumaBloques[0] = acarreo;
        }

        return sumaBloques;
    }

private static String convertirBloquesAString(int[] bloques) {
    StringBuilder sb = new StringBuilder();
    boolean ceroalaizuierda = true; 

    for (int i = 0; i < bloques.length; i++) {
        if (bloques[i] == 0 && ceroalaizuierda) {
            if (i == bloques.length - 1 && sb.length() == 0) {
                sb.append("0");
            }
            continue;
        }
        ceroalaizuierda = false; 
        if (sb.length() == 0) {
            sb.append(bloques[i]);
        } else {
            sb.append(String.format("%09d", bloques[i]));
        }
    }

    return sb.toString();
}

}
