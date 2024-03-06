/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ecuationsolverfromstring;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author brunosanchezpadilla
 */
public class EcuationSolverFromString {

    public static void main(String[] args) {
        System.out.println("Ingresa la operacion que deseas realizar:");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        scanner.close();

        try {
            double result = evaluateExpression(expression);
            System.out.printf("El resultado es: %.2f%n", result);
        } catch (Exception e) {
            System.out.println("Error en la expresión: " + e.getMessage());
        }
    }

    private static double evaluateExpression(String expr) throws Exception {
        return evaluate(expr, 0, expr.length() - 1);
    }


    
    private static double evaluate(String expr, int start, int end) throws Exception {
    double result = 0; // Resultado final de la evaluación.
    double currentNumber = 0; // Número actual siendo construido por dígitos consecutivos.
    boolean isNegative = false; // Indica si el número actual es negativo.
    char operation = '+'; // Operación actual, inicializada en '+' para manejar el primer número adecuadamente.

    for (int i = start; i <= end; i++) {
        char currentChar = expr.charAt(i);

        if (currentChar == '-' && (i == start || expr.charAt(i - 1) == '(')) {
            // Maneja un número negativo al inicio o justo después de un paréntesis abierto.
            isNegative = true;
        } else if (Character.isDigit(currentChar)) {
            // Construye el número actual.
            currentNumber = currentNumber * 10 + (currentChar - '0');
        }

        if (i == end || currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^') {
            if (isNegative) {
                currentNumber = -currentNumber; // Aplica negativo al número actual.
                isNegative = false; // Restablece la bandera para el próximo número.
            }

            if (currentChar == '^') {
                // Procesa la operación de potencia de inmediato.
                i++; // Avanza al exponente.
                double exponent = 0;
                while (i <= end && Character.isDigit(expr.charAt(i))) {
                    exponent = exponent * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                currentNumber = Math.pow(currentNumber, exponent);
                i--; // Ajusta el índice para la próxima iteración.
            } else {
                // Evalúa la operación previa.
                result = applyOperation(result, currentNumber, operation);
                operation = currentChar; // Actualiza la operación para la próxima iteración.
                currentNumber = 0; // Restablece el número actual para el próximo ciclo.
            }
        }

        if (currentChar == '(') {
            // Encuentra el índice del paréntesis correspondiente para evaluar la subexpresión.
            int braces = 1;
            int j = i + 1;
            while (j <= end && braces != 0) {
                if (expr.charAt(j) == '(') braces++;
                else if (expr.charAt(j) == ')') braces--;
                j++;
            }
            currentNumber = evaluate(expr, i + 1, j - 2); // Evalúa la subexpresión dentro de los paréntesis.
            i = j - 1; // Ajusta el índice para continuar después del paréntesis cerrado.
        }
    }

    return applyOperation(result, currentNumber, operation); // Aplica la última operación.
}

private static double applyOperation(double result, double currentNumber, char operation) {
    switch (operation) {
        case '+':
            return result + currentNumber;
        case '-':
            return result - currentNumber;
        case '*':
            return result * currentNumber;
        case '/':
            if (currentNumber == 0) throw new ArithmeticException("Division by zero.");
            return result / currentNumber;
        default:
            return result;
    }
}

   


}
