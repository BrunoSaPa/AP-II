


package operacionesconfracciones;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author BRUNO
 */

public class OperacionesConFracciones {
    public static void main(String[] args) {
    //un medio mas tres quintos  res= 11/10
    //un medio menos un medio  res= 0/4
    //noventa y nueve noventainueveavos por noventa y nueve noventainueveavos    res=9801/9801
    //diez noventainueveavos entre treinta y tres sesentaiseisavos   res=660/3267
    //cero quintos entre tres novenos   
    //tres novenos entre cero quintos
    //tres cero mas dos sextos
    //un medio mas genial
        
        
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> palabrasANumeros = new HashMap<>();
        Map<String, Integer> palabrasAFracciones = new HashMap<>();
        DiccionarioPalabras(palabrasANumeros);
        inicializarFraccionesEspecificas(palabrasAFracciones);
        Fracciones(palabrasANumeros, palabrasAFracciones);
       
        System.out.println("Ingrese la fraccion");
        String expresion = scanner.nextLine().toLowerCase();
        String[] partes = expresion.split(" ");

        Fraccion resultado = null;
        String operacion = null;
        boolean error = false;

        for (int i = 0; i < partes.length && !error;) {
           

            if (palabrasANumeros.containsKey(partes[i]) || (i + 1 < partes.length && palabrasAFracciones.containsKey(partes[i + 1]))) {
                String numeroCompuesto = partes[i];

                while (i + 2 < partes.length && palabrasANumeros.containsKey(partes[i + 2])) {
                    numeroCompuesto += " " + partes[i + 1] + " " + partes[i + 2];
                    i += 2;
                }

                if (i + 1 < partes.length && palabrasAFracciones.containsKey(partes[i + 1])) {
                    numeroCompuesto += " " + partes[i + 1];
                    i++;
                }

                Fraccion fraccionActual = convertirPalabrasANumeros(numeroCompuesto, palabrasANumeros, palabrasAFracciones);

                if (fraccionActual == null) {
                    error = true;
                    continue;
                }

                if (resultado == null) {
                    resultado = fraccionActual;
                } else {
                    if (operacion != null) {
                        resultado = realizarOperacion(resultado, fraccionActual, operacion);
                        if (resultado == null) {
                            error = true;
                            continue;
                        }
                    } else {
                        System.out.println("No se reconoce como una operacion valida.");
                        error = true;
                        continue;
                    }
                }
                i++;
            } else {
                if (!partes[i].equals("mas") && !partes[i].equals("menos") && !partes[i].equals("por") && !partes[i].equals("entre") && !partes[i].equals("y")) {
                    System.out.println("No existe " + partes[i]);
                    error = true;
                    continue;
                }
                operacion = partes[i];
                i++;
            }

        }

        if (!error && resultado != null) {
            System.out.println(resultado.ResultadoPalabras(palabrasANumeros));
        } else if (!error) {
            System.out.println("No se pudo calcular un resultado.");
        }

        scanner.close();
    }

    private static void DiccionarioPalabras(Map<String, Integer> mapaPalabrasNumeros) {
        mapaPalabrasNumeros.put("cero", 0);
        mapaPalabrasNumeros.put("un", 1);
        mapaPalabrasNumeros.put("uno", 1);
        mapaPalabrasNumeros.put("dos", 2);
        mapaPalabrasNumeros.put("tres", 3);
        mapaPalabrasNumeros.put("cuatro", 4);
        mapaPalabrasNumeros.put("cinco", 5);
        mapaPalabrasNumeros.put("seis", 6);
        mapaPalabrasNumeros.put("siete", 7);
        mapaPalabrasNumeros.put("ocho", 8);
        mapaPalabrasNumeros.put("nueve", 9);
        mapaPalabrasNumeros.put("diez", 10);
        mapaPalabrasNumeros.put("once", 11);
        mapaPalabrasNumeros.put("doce", 12);
        mapaPalabrasNumeros.put("trece", 13);
        mapaPalabrasNumeros.put("catorce", 14);
        mapaPalabrasNumeros.put("quince", 15);
        mapaPalabrasNumeros.put("dieciseis", 16);
        mapaPalabrasNumeros.put("diecisiete", 17);
        mapaPalabrasNumeros.put("dieciocho", 18);
        mapaPalabrasNumeros.put("diecinueve", 19);
        mapaPalabrasNumeros.put("veinte", 20);
        mapaPalabrasNumeros.put("veintiuno", 21);
        mapaPalabrasNumeros.put("veintidos", 22);
        mapaPalabrasNumeros.put("veintitres", 23);
        mapaPalabrasNumeros.put("veinticuatro", 24);
        mapaPalabrasNumeros.put("veinticinco", 25);
        mapaPalabrasNumeros.put("veintiseis", 26);
        mapaPalabrasNumeros.put("veintisiete", 27);
        mapaPalabrasNumeros.put("veintiocho", 28);
        mapaPalabrasNumeros.put("veintinueve", 29);
        mapaPalabrasNumeros.put("treinta", 30);
         mapaPalabrasNumeros.put("treintaiuno", 31);
         mapaPalabrasNumeros.put("treintaidos", 32);
         mapaPalabrasNumeros.put("treintaitres", 33);
         mapaPalabrasNumeros.put("treintaicuatro", 34);
         mapaPalabrasNumeros.put("treintaicinco", 35);
         mapaPalabrasNumeros.put("treintaiseis", 36);
         mapaPalabrasNumeros.put("treintaisiete", 37);
         mapaPalabrasNumeros.put("treintaiocho", 38);
         mapaPalabrasNumeros.put("treintainueve", 39);
         mapaPalabrasNumeros.put("cuarenta", 40);
           mapaPalabrasNumeros.put("cuarentaiuno", 41);
         mapaPalabrasNumeros.put("cuarentaidos", 42);
         mapaPalabrasNumeros.put("cuarentaitres", 43);
         mapaPalabrasNumeros.put("cuarentaicuatro", 44);
         mapaPalabrasNumeros.put("cuarentaicinco", 45);
         mapaPalabrasNumeros.put("cuarentaiseis", 46);
         mapaPalabrasNumeros.put("cuarentaisiete", 47);
         mapaPalabrasNumeros.put("cuarentaiocho", 48);
         mapaPalabrasNumeros.put("cuarentainueve", 49);
         mapaPalabrasNumeros.put("cincuenta", 50);
         mapaPalabrasNumeros.put("cincuentaiuno", 51);
           mapaPalabrasNumeros.put("cincuentaidos", 52);
         mapaPalabrasNumeros.put("cincuentaitres", 53);
         mapaPalabrasNumeros.put("cincuentaicuatro", 54);
         mapaPalabrasNumeros.put("cincuentaicinco", 55);
         mapaPalabrasNumeros.put("cincuentaiseis", 56);
         mapaPalabrasNumeros.put("cincuentaisiete", 57);
         mapaPalabrasNumeros.put("cincuentaiocho", 58);
         mapaPalabrasNumeros.put("cicnuentainueve", 59);
         mapaPalabrasNumeros.put("sesenta", 60);
         mapaPalabrasNumeros.put("sesentaiuno", 61);
         mapaPalabrasNumeros.put("sesentaidos", 62);
           mapaPalabrasNumeros.put("sesentaitres", 63);
         mapaPalabrasNumeros.put("sesentaicuatro", 64);
         mapaPalabrasNumeros.put("sesentaicinco", 65);
         mapaPalabrasNumeros.put("sesentaiseis", 66);
         mapaPalabrasNumeros.put("sesentaisiete", 67);
         mapaPalabrasNumeros.put("sesentaiocho", 68);
         mapaPalabrasNumeros.put("sesentainueve", 69);
         mapaPalabrasNumeros.put("setenta", 70);
         mapaPalabrasNumeros.put("setentaiuno", 71);
         mapaPalabrasNumeros.put("setentaidos", 72);
         mapaPalabrasNumeros.put("setentaitres", 73);
           mapaPalabrasNumeros.put("setentaicuatro", 74);
         mapaPalabrasNumeros.put("setentaicinco", 75);
         mapaPalabrasNumeros.put("setentaiseis", 76);
         mapaPalabrasNumeros.put("setentaisiete", 77);
         mapaPalabrasNumeros.put("setentaiocho", 78);
         mapaPalabrasNumeros.put("setentainueve", 79);
         mapaPalabrasNumeros.put("ochenta", 80);
         mapaPalabrasNumeros.put("ochentaiuno", 81);
         mapaPalabrasNumeros.put("ochentaidos", 82);
         mapaPalabrasNumeros.put("ochentaitres", 83);
         mapaPalabrasNumeros.put("ochentaicuatro", 84);
        mapaPalabrasNumeros.put("ochentaicinco", 85);
         mapaPalabrasNumeros.put("ochentaiseis", 86);
         mapaPalabrasNumeros.put("ochentaisiete", 87);
         mapaPalabrasNumeros.put("ochentaiocho", 88);
         mapaPalabrasNumeros.put("ochentainueve", 89);
        mapaPalabrasNumeros.put("noventa", 90);
        mapaPalabrasNumeros.put("nocventaiuno", 91);
        mapaPalabrasNumeros.put("noventaidos", 92);
        mapaPalabrasNumeros.put("noventaitres", 93);
        mapaPalabrasNumeros.put("noventaicuatro", 94);
        mapaPalabrasNumeros.put("noventaicinco", 95);
        mapaPalabrasNumeros.put("noventaiseis", 96);
        mapaPalabrasNumeros.put("noventaisiete", 97);
        mapaPalabrasNumeros.put("noventaiocho", 98);
        mapaPalabrasNumeros.put("noventainueve", 99);
        mapaPalabrasNumeros.put("cien", 100);
        mapaPalabrasNumeros.put("ciento", 100);
        mapaPalabrasNumeros.put("doscientos", 200);
        mapaPalabrasNumeros.put("trescientos", 300);
        mapaPalabrasNumeros.put("cuatrocientos", 400);
        mapaPalabrasNumeros.put("quinientos", 500);
        mapaPalabrasNumeros.put("seiscientos", 600);
        mapaPalabrasNumeros.put("setecientos", 700);
        mapaPalabrasNumeros.put("ochocientos", 800);
        mapaPalabrasNumeros.put("novecientos", 900);
        mapaPalabrasNumeros.put("mil", 1000);
        mapaPalabrasNumeros.put("dos mil", 2000);
        mapaPalabrasNumeros.put("tres mil", 3000);
        mapaPalabrasNumeros.put("cuatro mil", 4000);
        mapaPalabrasNumeros.put("cinco mil", 5000);
        mapaPalabrasNumeros.put("seis mil", 6000);
        mapaPalabrasNumeros.put("siete mil", 7000);
        mapaPalabrasNumeros.put("ocho mil", 8000);
        mapaPalabrasNumeros.put("nueve mil", 9000);
     
     
    }

    private static void inicializarFraccionesEspecificas(Map<String, Integer> mapaPalabrasFracciones) {
        mapaPalabrasFracciones.put("cero", 0);
        mapaPalabrasFracciones.put("entero", 1);
        mapaPalabrasFracciones.put("medio", 2);
        mapaPalabrasFracciones.put("tercio", 3);
        mapaPalabrasFracciones.put("cuarto", 4);
        mapaPalabrasFracciones.put("quinto", 5);
        mapaPalabrasFracciones.put("sexto", 6);
        mapaPalabrasFracciones.put("septimo", 7);
        mapaPalabrasFracciones.put("octavo", 8);
        mapaPalabrasFracciones.put("noveno", 9);
        mapaPalabrasFracciones.put("decimo", 10);
        mapaPalabrasFracciones.put("cero", 0);
         mapaPalabrasFracciones.put("enteros", 1);
        mapaPalabrasFracciones.put("medios", 2);
       mapaPalabrasFracciones.put("tercios", 3);
        mapaPalabrasFracciones.put("cuartos", 4);
        mapaPalabrasFracciones.put("quintos", 5);
        mapaPalabrasFracciones.put("sextos", 6);
        mapaPalabrasFracciones.put("septimos", 7);
        mapaPalabrasFracciones.put("octavos", 8);
        mapaPalabrasFracciones.put("novenos", 9);
        mapaPalabrasFracciones.put("decimos", 10);
    }

    private static void Fracciones(Map<String, Integer> palabrasANumeros, Map<String, Integer> palabrasAFracciones) {
       for (Map.Entry<String, Integer> entry : palabrasANumeros.entrySet()) {
           String clave = entry.getKey();
           Integer valor = entry.getValue();
           if (valor > 10) {  

               if (clave.endsWith("a")) {
                   palabrasAFracciones.put(clave + "vos", valor);
               } else {
                   palabrasAFracciones.put(clave + "avos", valor);
               }
           }
       }
   }


    private static Fraccion convertirPalabrasANumeros(String fraccionStr, Map<String, Integer> palabrasANumeros, Map<String, Integer> palabrasAFracciones) {
        int numerador = 0;
        int denominador = 1;
        boolean auxNumerador = true;
        boolean auxDenominador = true;
        boolean esDenominadorPlural = false;
        String[] partes = fraccionStr.split(" ");

        for (String parte : partes) {
            if (parte.equals("cero")) {
                System.out.println("Cero en el numerador o denominador no validos");
                return null;
            }

            if (palabrasANumeros.containsKey(parte)) {
                int valor = palabrasANumeros.get(parte);
                if (auxNumerador) {
                    numerador += valor;
                    auxDenominador = false;
                } else if (auxDenominador) {
                    denominador = denominador + valor;
                    auxDenominador = false;
                } else {
                    numerador = valor;
                }
            } else if (palabrasAFracciones.containsKey(parte)) {
                denominador = palabrasAFracciones.get(parte);
                esDenominadorPlural = parte.endsWith("s");
            } else {
                if(parte == "y"){
                    System.out.println("No existe " + parte);
                    return null;
                }
            }
        }


        return new Fraccion(numerador, denominador);
    }

    
    private static Fraccion realizarOperacion(Fraccion fraccion1, Fraccion fraccion2, String operacion) {
        switch (operacion) {
            case "mas":
                return fraccion1.sumar(fraccion2);
            case "menos":
                return fraccion1.restar(fraccion2);
            case "por":
                return fraccion1.multiplicar(fraccion2);
            case "entre":
                return fraccion1.dividir(fraccion2);
            default:
                System.out.println("No existe alguna operacion");
                return null;
        }
    }

  


}