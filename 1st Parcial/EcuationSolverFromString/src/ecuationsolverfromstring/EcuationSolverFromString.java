/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ecuationsolverfromstring;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author brunosanchezpadilla
 */
public class EcuationSolverFromString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa la operacion que deseas realizar:");
        String expression = scanner.nextLine();
        
        expression = expression.replace(" ", "");

        // Extracción de variables de la expresión
        List<String> variables = extractVariables(expression);
        
        // Si hay variables, solicitar sus valores
        Map<String, Integer> variableValues = new HashMap<>();
        if (!variables.isEmpty()) {
            variableValues = requestVariableValues(variables, scanner);
            expression = replaceVariables(expression, variableValues);
        }
        
        System.out.println("Expresion despues del reemplazo: " + expression);


        try {
            double result = evaluateExpression(expression);
            System.out.println("El resultado es: " + result);
        } catch (Exception e) {
            System.out.println("Error en la expresion: " + e.getMessage());
        }

        scanner.close();
    }

private static Map<String, Integer> requestVariableValues(List<String> variables, Scanner scanner) {
    Map<String, Integer> values = new HashMap<>();
    for (String variable : variables) {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.println("Ingresa el valor de la variable " + variable + ":");
                int value = Integer.parseInt(scanner.nextLine()); // Usa parseDouble después de leer una línea completa
                values.put(variable, value);
                isValidInput = true; // La entrada es válida, salir del bucle
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
                // No actualiza isValidInput, por lo que el bucle continuará
            }
        }
    }
    return values;
}




private static String replaceVariables(String expr, Map<String, Integer> variableValues) {
    for (Map.Entry<String, Integer> entry : variableValues.entrySet()) {
        expr = expr.replace(entry.getKey(), entry.getValue().toString());
    }
    return expr;
}



private static List<String> extractVariables(String expr) {
    List<String> variables = new ArrayList<>();
    StringBuilder variable = new StringBuilder();
    // Introduce espacios alrededor de cada operador para asegurar la separación de las variables
    String modifiedExpr = expr.replaceAll("([\\+\\-\\*/\\^()])", " $1 ");
    Scanner scanner = new Scanner(modifiedExpr);

    while (scanner.hasNext()) {
        String token = scanner.next();
        if (!token.matches("[\\+\\-\\*/\\^()0-9]+") && !variables.contains(token)) { // Asume que cualquier token que no sea un operador o dígito es una variable
            variables.add(token);
        }
    }
    scanner.close();
    return variables;
}



private static double evaluateExpression(String expr) throws Exception {
    // Verificar paréntesis balanceados
    if (!areParenthesesBalanced(expr)) {
        throw new Exception("Error en la expresion, no todos los parentesis fueron cerrados");
    }

    // Verificar operadores consecutivos inválidos (excepto el caso especial de números negativos)
    if (!areOperatorsValid(expr)) {
        throw new Exception("Error en la expresion, no puedes introducir dos operadores consecutivos");
    }

    return evaluate(expr, 0, expr.length() - 1);
}

private static boolean areParenthesesBalanced(String expr) {
    int balance = 0;
    for (int i = 0; i < expr.length(); i++) {
        if (expr.charAt(i) == '(') balance++;
        else if (expr.charAt(i) == ')') balance--;

        if (balance < 0) { // Se encontró un paréntesis cerrado antes de uno abierto
            return false;
        }
    }
    return balance == 0; // Verdadero si todos los paréntesis están balanceados
}




private static boolean areOperatorsValid(String expr) {
    boolean lastWasOperator = false;
    boolean lastWasOpenParenthesis = false;

    for (int i = 0; i < expr.length(); i++) {
        char c = expr.charAt(i);
        if ("+-*/^".indexOf(c) >= 0) {
            if (lastWasOperator && !(lastWasOpenParenthesis && c == '-')) { // Permite un '-' para números negativos después de un operador o paréntesis abierto
                return false; // Dos operadores consecutivos (invalido, excepto "-")
            }
            lastWasOperator = true;
            lastWasOpenParenthesis = false;
        } else if (c == '(') {
            lastWasOpenParenthesis = true;
            lastWasOperator = false; // Un operador antes de '(' es válido
        } else if (c == ')') {
            lastWasOpenParenthesis = false;
            lastWasOperator = false; // Reset después de ')'
        } else {
            lastWasOperator = false; // Si es un número o cualquier otro carácter, resetea lastWasOperator
            lastWasOpenParenthesis = false;
        }
    }
    return true;
}





private static double evaluate(String expr, int start, int end) throws Exception {
    List<Double> numbers = new ArrayList<>();
    List<Character> operations = new ArrayList<>();
    double currentNumber = 0;
    boolean isBuildingNumber = false;

    for (int i = start; i <= end; i++) {
        char currentChar = expr.charAt(i);

        if (Character.isDigit(currentChar)) {
            currentNumber = currentNumber * 10 + (currentChar - '0');
            isBuildingNumber = true;
        } else if (currentChar == '(') {
            int braces = 1;
            int j = i + 1;
            while (j <= end && braces != 0) {
                if (expr.charAt(j) == '(') braces++;
                else if (expr.charAt(j) == ')') braces--;
                j++;
            }
            currentNumber = evaluate(expr, i + 1, j - 2);
            i = j - 1; // Ajusta el índice para continuar después del paréntesis cerrado.
            isBuildingNumber = true;
        } else {
            if (isBuildingNumber) {
                numbers.add(currentNumber);
                currentNumber = 0;
                isBuildingNumber = false;
            }
            if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^') {
                operations.add(currentChar);
            }
        }
    }

    if (isBuildingNumber) {
        numbers.add(currentNumber);
    }

    // Procesamiento de potencias primero
    for (int i = 0; i < operations.size(); i++) {
        if (operations.get(i) == '^') {
            double base = numbers.get(i);
            double exponent = numbers.get(i + 1);
            double result = Math.pow(base, exponent);
            numbers.set(i, result);
            numbers.remove(i + 1);
            operations.remove(i);
            i--; // Ajuste del índice debido a la eliminación
        }
    }

    // Procesamiento de multiplicaciones y divisiones
    for (int i = 0; i < operations.size(); i++) {
        if (operations.get(i) == '*' || operations.get(i) == '/') {
            double left = numbers.get(i);
            double right = numbers.get(i + 1);
            double result = operations.get(i) == '*' ? left * right : left / right;
            numbers.set(i, result);
            numbers.remove(i + 1);
            operations.remove(i);
            i--; // Ajuste del índice debido a la eliminación
        }
    }

    // Procesamiento de sumas y restas
    double finalResult = numbers.get(0);
    for (int i = 0; i < operations.size(); i++) {
        double nextNumber = numbers.get(i + 1);
        finalResult += operations.get(i) == '+' ? nextNumber : -nextNumber;
    }

    return finalResult;
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
            if (currentNumber == 0) throw new ArithmeticException("Division entre cero");
            return result / currentNumber;
        default:
            return result;
    }
}

   


}
