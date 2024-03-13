/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package newtonraphson;
import static java.lang.Math.random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.Scanner;
/**
 *
 * @author BRUNO
 */
public class NewtonRaphson {

     private static final Pattern termPattern = Pattern.compile("([-+]?[^-+]+)");

public static void main(String[] args) {
    //3pasta^5-7pasta+4
    //3x^9+33
    //9x^2+6x^7-10x^5
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("Ingrese la ecuacion");
    String input = scanner.nextLine().toLowerCase();
    double startPoint = 0.0;
    double tolerance = 1e-7;
    String variable = extractVariable(input);
    Map<Integer, Double> coefficients = parseCoefficients(input, variable);

    // Vamos a imprimir los coeficientes para verificar si se extrajeron correctamente
    for(Map.Entry<Integer, Double> entry : coefficients.entrySet()) {
        System.out.println("Exponente " + entry.getKey() + ", Coefficiente " + entry.getValue());
    }

    Function<Double, Double> function = x -> evaluatePolynomial(x, coefficients);
    Function<Double, Double> derivative = x -> evaluateDerivative(x, coefficients);
    
        try {
        double root = findRoot(function, derivative, startPoint, tolerance);
        System.out.println("Raiz encontrada " + root);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}



    private static String extractVariable(String input) {
        Matcher matcher = Pattern.compile("[a-zA-Z]+").matcher(input);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            throw new IllegalArgumentException("No se encontró ninguna variable");
        }
    }

private static Map<Integer, Double> parseCoefficients(String input, String variable) {
    Map<Integer, Double> coefficients = new HashMap<>();
    Matcher matcher = termPattern.matcher(input.replaceAll("\\s+", "").replaceAll(variable, "x"));

    while (matcher.find()) {
        String term = matcher.group(1);
        if(term.equals("x")) {
            coefficients.put(1, 1.0);
        } else if(term.matches("-x")) {
            coefficients.put(1, -1.0);
        } else {
            String[] parts = term.split("x\\^?"); // Split on "x" or "x^"
            double coefficient = parts.length > 0 && !parts[0].isEmpty() ? Double.parseDouble(parts[0]) : 1.0;
            int exponent = parts.length > 1 && !parts[1].isEmpty() ? Integer.parseInt(parts[1]) : (parts.length == 1 && term.contains("x") ? 1 : 0);
            coefficients.put(exponent, coefficient);
        }
    }

    return coefficients;
}



    private static double evaluatePolynomial(double x, Map<Integer, Double> coefficients) {
        return coefficients.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * Math.pow(x, entry.getKey()))
                .sum();
    }

private static double evaluateDerivative(double x, Map<Integer, Double> coefficients) {
    double sum = 0;
    for (Map.Entry<Integer, Double> entry : coefficients.entrySet()) {
        if (entry.getKey() == 0) {
            // No contribution from the constant term to the derivative.
            continue;
        }
        sum += entry.getKey() * entry.getValue() * Math.pow(x, entry.getKey() - 1);
    }
    return sum;
}



private static double findRoot(Function<Double, Double> function, Function<Double, Double> derivative, double startPoint, double tolerance) {
    double x0 = startPoint;
    double x1;
    double f_x0, f_x1, f_prime_x0;
    Random random = new Random();

    System.out.println("Newton-Raphson en x0 = " + x0);

    for (int i = 0; i < 100; i++) {
        f_x0 = function.apply(x0);
        f_prime_x0 = derivative.apply(x0);

        System.out.println("Iteracion " + i + ": x0 = " + x0 + ", f(x0) = " + f_x0 + ", f'(x0) = " + f_prime_x0);

        if (Math.abs(f_prime_x0) < tolerance) {
            f_prime_x0 = -0.5 + random.nextDouble();
        }

        x1 = x0 - f_x0 / f_prime_x0;
        f_x1 = function.apply(x1);

        if (Math.abs(f_x1) < tolerance) {
            System.out.println("Convergencia alcanzada después de " + i + " iteraciones.");
            return x1;
        }

        x0 = x1;
    }

    throw new RuntimeException("No se pudo encontrar después de 100 iteraciones.");
}


    
}
