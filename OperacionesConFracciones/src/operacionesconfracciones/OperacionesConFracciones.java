/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package operacionesconfracciones;
import java.util.HashMap;
/**
 *
 * @author BRUNO
 */
public class OperacionesConFracciones {

    /**
     * @param args the command line arguments
     */
    private static HashMap<String, Integer> numeradorMap;
    private static HashMap<String, Integer> denominadorMap;

    static {

        numeradorMap = new HashMap<>();
        numeradorMap.put("un", 1);
        numeradorMap.put("uno", 1);
        numeradorMap.put("dos", 2);
        numeradorMap.put("tres", 3);
        numeradorMap.put("cuatro", 4);
        numeradorMap.put("cinco", 5);
        numeradorMap.put("seis", 6);
        numeradorMap.put("siete", 7);
        numeradorMap.put("ocho", 8);
        numeradorMap.put("nueve", 9);
        numeradorMap.put("diez", 10);
        numeradorMap.put("once", 11);
        numeradorMap.put("doce", 12);
        numeradorMap.put("trece", 13);
        numeradorMap.put("catorce", 14);
        numeradorMap.put("quince", 15);
        numeradorMap.put("dieciseis", 16);
        numeradorMap.put("diecisiete", 17);
        numeradorMap.put("dieciocho", 18);
        numeradorMap.put("diecinueve", 19);
        numeradorMap.put("veinte", 20);
        numeradorMap.put("veintiuno", 21);
        numeradorMap.put("veintidos", 22);
        numeradorMap.put("veintitres", 23);
        numeradorMap.put("veinticuatro", 24);
        numeradorMap.put("veinticinco", 25);
        numeradorMap.put("veintiseis", 26);
        numeradorMap.put("veintisiete", 27);
        numeradorMap.put("veintiocho", 28);
        numeradorMap.put("veintinueve", 29);
        numeradorMap.put("treinta", 30);
        numeradorMap.put("cuarenta", 40);
        numeradorMap.put("cincuenta", 50);
        numeradorMap.put("sesenta", 60);
        numeradorMap.put("setenta", 70);
        numeradorMap.put("ochenta", 80);
        numeradorMap.put("noventa", 90);



        denominadorMap = new HashMap<>();
        denominadorMap.put("medio", 2);
        denominadorMap.put("tercios", 3);
        denominadorMap.put("tercio", 3);
        denominadorMap.put("cuartos", 4);
        denominadorMap.put("quintos", 5);
        denominadorMap.put("sextos", 6);
        denominadorMap.put("septimos", 7);
        denominadorMap.put("octavos", 8);
        denominadorMap.put("novenos", 9);
        denominadorMap.put("decimos", 10);
        denominadorMap.put("unavos", 1);
        denominadorMap.put("dosavos", 2);
        denominadorMap.put("tresavos", 3);
        denominadorMap.put("cuatroavos", 4);
        denominadorMap.put("cincoavos", 5);
        denominadorMap.put("seisavos", 6);
        denominadorMap.put("sieteavos", 7);
        denominadorMap.put("ochoavos", 8);
        denominadorMap.put("nueveavos", 9);
        denominadorMap.put("onceavos", 11);
        denominadorMap.put("doceavos", 12);
        denominadorMap.put("treceavos", 13);
        denominadorMap.put("catorceavos", 14);
        denominadorMap.put("quinceavos", 15);
        denominadorMap.put("dieciseiseavos", 16);
        denominadorMap.put("diecisieteavos", 17);
        denominadorMap.put("dieciochoavos", 18);
        denominadorMap.put("diecinueveavos", 19);
        denominadorMap.put("veinti", 20);
        denominadorMap.put("treintai", 30);
        denominadorMap.put("cuarentai", 40);
        denominadorMap.put("cincuentai", 50);
        denominadorMap.put("sesentai", 60);
        denominadorMap.put("setentai", 70);
        denominadorMap.put("ochentai", 80);
        denominadorMap.put("ochentai", 80);

    }

    public static void main(String[] args) {
        // Ejemplo de input
        String input = "setenta y cuatro cuarentaitresavos menos catorce diecinueveavos";
        Fraccion resultado = procesarInput(input);
        System.out.println(fraccionAString(resultado));
    }

private static Fraccion procesarInput(String input) {
    
        String operacionInput = input.replace(" mas ", " + ")
                                 .replace(" menos ", " - ")
                                 .replace(" por ", " * ")
                                 .replace(" entre ", " / ");

    String operacion = "";
    if (operacionInput.contains(" + ")) {
        operacion = "+";
    } else if (operacionInput.contains(" - ")) {
        operacion = "-";
    } else if (operacionInput.contains(" * ")) {
        operacion = "*";
    } else if (operacionInput.contains(" / ")) {
        operacion = "/";
    }

    String[] partes = operacionInput.split(" \\+ | - | \\* | / ");
    Fraccion res = new Fraccion(0, 1); // Fracción inicial neutra para la suma

    for (String parte : partes) {
        String[] palabras = parte.split(" ");
        int numerador = 0;
        int denominador = 1; // Valor inicial para evitar división por cero

        // Procesar el numerador
        if (palabras[1].equals("y")) { // Para numeradores compuestos
            numerador = numeradorMap.get(palabras[0]) + numeradorMap.get(palabras[2]);
        } else { // Para numeradores simples
            numerador = numeradorMap.get(palabras[0]);
        }

        // Procesar el denominador
        int denominadorStartIndex = palabras[1].equals("y") ? 3 : 1;
        String denominadorStr = palabras[denominadorStartIndex];

        // Caso para denominadores simples directos
        if (denominadorMap.containsKey(denominadorStr)) {
            denominador = denominadorMap.get(denominadorStr);
        } else {
            // Intentar dividir el denominador compuesto
            for (String key : denominadorMap.keySet()) {
                if (denominadorStr.startsWith(key)) {
                    int denominadorParte1 = denominadorMap.get(key);
                    String denominadorParte2Str = denominadorStr.substring(key.length());
                    // Asegura que la segunda parte del denominador también sea válida
                    if (denominadorMap.containsKey(denominadorParte2Str)) {
                        int denominadorParte2 = denominadorMap.get(denominadorParte2Str);
                        // Calcula el denominador combinado
                        denominador = denominadorParte1 + denominadorParte2;
                        break; // Sale del bucle una vez que encuentra una coincidencia válida
                    }
                }
            }
        }
        if (parte == partes[0]) {
                res = res.sumar(new Fraccion(numerador, denominador));
        } else {
        switch (operacion) {
        case "+" -> res = res.sumar(new Fraccion(numerador, denominador));
        case "-" -> res = res.restar(new Fraccion(numerador, denominador));
        case "*" -> res = res.multiplicar(new Fraccion(numerador, denominador));
        case "/" -> res = res.dividir(new Fraccion(numerador, denominador));
    }
        }
    }

    return res;
}




    private static String fraccionAString(Fraccion fraccion) {
        return fraccion.numerador + "/" + fraccion.denominador;
    }
    
}
