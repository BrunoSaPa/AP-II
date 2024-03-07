/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacionesconfracciones;

import java.util.Map;

/**
 *
 * @author BRUNO
 */
public class Fraccion {
        private int numerador;
        private int denominador;

        public Fraccion(int numerador, int denominador) {
           
            this.numerador = numerador;
            this.denominador = denominador;
        }

        public Fraccion sumar(Fraccion otra) {
            int nuevoNumerador = (this.numerador * otra.denominador) + (otra.numerador * this.denominador);
            int nuevoDenominador = this.denominador * otra.denominador;
            return new Fraccion(nuevoNumerador, nuevoDenominador);
        }

        public Fraccion restar(Fraccion otra) {
            int nuevoNumerador = (this.numerador * otra.denominador) - (otra.numerador * this.denominador);
            int nuevoDenominador = this.denominador * otra.denominador;
            return new Fraccion(nuevoNumerador, nuevoDenominador);
        }

        public Fraccion multiplicar(Fraccion otra) {
            int nuevoNumerador = this.numerador * otra.numerador;
            int nuevoDenominador = this.denominador * otra.denominador;
            return new Fraccion(nuevoNumerador, nuevoDenominador);
        }

        public Fraccion dividir(Fraccion otra) {
    if (otra.numerador == 0) {
        System.out.println("no se puede dividir entre 0");
        return null;
    }
    int nuevoNumerador = this.numerador * otra.denominador;
    int nuevoDenominador = this.denominador * otra.numerador;
    return new Fraccion(nuevoNumerador, nuevoDenominador);
}

        @Override
        public String toString() {
            if (numerador == 0) {
                return "0";
            }
            if (denominador == 1) {
                return String.valueOf(numerador);
            }
            return numerador + "/" + denominador;
        }

        public String ResultadoPalabras(Map<String, Integer> palabrasANumeros) {
            String numeradorPalabras = convertirNumeroAPalabras(this.numerador, palabrasANumeros);
            String denominadorPalabras;

            if (this.numerador == 1) {
                switch (this.denominador) {
                   case 0:
                        denominadorPalabras = "cero";
                        break;
                    case 1:
                        return numeradorPalabras; 
                    case 2:
                        denominadorPalabras = "medio";
                        break;
                    case 3:
                        denominadorPalabras = "tercio";
                        break;
                    case 4:
                        denominadorPalabras = "cuarto";
                        break;
                    case 5:
                        denominadorPalabras = "quinto";
                        break;
                    case 6:
                        denominadorPalabras = "sexto";
                        break;
                    case 7:
                        denominadorPalabras = "septimo";
                        break;
                    case 8:
                        denominadorPalabras = "octavo";
                        break;
                    case 9:
                        denominadorPalabras = "noveno";
                        break;
                    case 10:
                        denominadorPalabras = "decimo";
                        break;
                    default:
                        denominadorPalabras = convertirNumeroAPalabras(this.denominador, palabrasANumeros);
                if (denominadorPalabras.endsWith("a")) {
                    denominadorPalabras += "vo";
                } else {
                    denominadorPalabras += "avo";
                }
                break;
               
                }
            } else { // Uso de formas plurales
                switch (this.denominador) {
          case 0:
                        denominadorPalabras = "cero";
                        break;
                    case 1:
                        denominadorPalabras = "enteros";
                        break;
                    case 2:
                        denominadorPalabras = "medios";
                        break;
                    case 3:
                        denominadorPalabras = "tercios";
                        break;
                    case 4:
                        denominadorPalabras = "cuartos";
                        break;
                    case 5:
                        denominadorPalabras = "quintos";
                        break;
                    case 6:
                        denominadorPalabras = "sextos";
                        break;
                    case 7:
                        denominadorPalabras = "septimos";
                        break;
                    case 8:
                        denominadorPalabras = "octavos";
                        break;
                    case 9:
                        denominadorPalabras = "novenos";
                        break;
                    case 10:
                        denominadorPalabras = "decimos";
                        break;
                    default:
                          denominadorPalabras = convertirNumeroAPalabras(this.denominador, palabrasANumeros);
                if (denominadorPalabras.endsWith("a")) {
                    denominadorPalabras += "vos";
                } else {
                    denominadorPalabras += "avos";
                }
                break;
                }
            }
            return numeradorPalabras + " " + denominadorPalabras;
        }

  private static String convertirNumeroAPalabras(int numero, Map<String, Integer> palabrasANumeros) {
        if (numero == 0) {
            return "cero";
        }

        StringBuilder resultado = new StringBuilder();
        if (numero >= 1000) {
            int miles = numero / 1000;
            resultado.append(convertirNumeroAPalabras(miles, palabrasANumeros)).append(" mil ");
            numero %= 1000;
        }

        if (numero >= 100) {
            if (numero == 100) {
                resultado.append("cien ");
            } else if (numero > 100 && numero < 200) {
                resultado.append("ciento ");
                numero -= 100;
            } else {
                int centenas = numero / 100 * 100;
                resultado.append(obtenerClavePorValor(palabrasANumeros, centenas)).append(" ");
                numero %= 100;
            }
        }

        if (numero > 0) {
            if (palabrasANumeros.containsValue(numero)) {
                resultado.append(obtenerClavePorValor(palabrasANumeros, numero));
            } else {
                int decenas = numero / 10 * 10;
                int unidades = numero % 10;
                if (decenas > 0) {
                    resultado.append(obtenerClavePorValor(palabrasANumeros, decenas)).append(" ");
                }
                if (unidades > 0) {
                    resultado.append(obtenerClavePorValor(palabrasANumeros, unidades));
                }
            }
        }

        return resultado.toString().trim();
    }

      private static String obtenerClavePorValor(Map<String, Integer> map, Integer valor) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(valor)) {
                return entry.getKey();
            }
        }
        return "El numero no se encuentra en el rango correcto";
    }
  
    }
