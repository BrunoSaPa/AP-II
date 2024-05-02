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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author BRUNO
 */
//3pasta^5-7pasta+4
    //3x^9+33
    //9x^2+6x^7-10x^5


public class NewtonRaphson extends JFrame {
    private JTextField inputField;
    private JButton solveButton, graphButton;
    private JTextArea resultArea;
    private GraphPanel graphPanel;
    private static final Pattern termPattern = Pattern.compile("([-+]?[^-+]+)");

    public NewtonRaphson() {
        setTitle("Newton-Raphson Solver and Grapher");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField(20);
        solveButton = new JButton("Solve");
        graphButton = new JButton("Graph");
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

        graphPanel = new GraphPanel();

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Polynomial:"));
        inputPanel.add(inputField);
        inputPanel.add(solveButton);
        inputPanel.add(graphButton);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.SOUTH);
        add(graphPanel, BorderLayout.CENTER);

        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solvePolynomial();
            }
        });

        graphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphPolynomial();
            }
        });
    }

    private void solvePolynomial() {
        try {
            String input = inputField.getText().toLowerCase();
            double startPoint = 0.0;
            double tolerance = 1e-7;
            String variable = extractVariable(input);
            Map<Integer, Double> coefficients = parseCoefficients(input, variable);

            Function<Double, Double> function = x -> evaluatePolynomial(x, coefficients);
            Function<Double, Double> derivative = x -> evaluateDerivative(x, coefficients);

            double root = findRoot(function, derivative, startPoint, tolerance);
            resultArea.setText("Root found: " + root);
        } catch (Exception ex) {
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    private void graphPolynomial() {
        String input = inputField.getText().toLowerCase();
        String variable = extractVariable(input);
        Map<Integer, Double> coefficients = parseCoefficients(input, variable);
        graphPanel.setCoefficients(coefficients);
        graphPanel.repaint();
    }

    private static String extractVariable(String input) {
        Matcher matcher = Pattern.compile("[a-zA-Z]+").matcher(input);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            throw new IllegalArgumentException("No variable found");
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
            System.out.println("Convergencia alcanzada despues de " + i + " iteraciones.");
            return x1;
        }

        x0 = x1;
    }

    throw new RuntimeException("No se pudo encontrar despues de 100 iteraciones.");
}

class GraphPanel extends JPanel {
    private Map<Integer, Double> coefficients;
    private final double xScale = 10;  
    private final double yScale = 10;  

    public void setCoefficients(Map<Integer, Double> coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (coefficients == null) return;
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth() / 2, getHeight() / 2);
        g2.scale(1, -1);  // Flip to make y-values display in traditional Cartesian system

        // Drawing axes
        g2.setColor(Color.BLACK);
        g2.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0);  // X-axis
        g2.drawLine(0, -getHeight() / 2, 0, getHeight() / 2);  // Y-axis

        // Temporarily reverse Y scale for correct text orientation
        g2.scale(1, -1);
        
        // Draw scale marks on the axes
        for (int i = -getWidth() / 2; i < getWidth() / 2; i += 50) {
            g2.drawLine(i, -5, i, 5);
            g2.drawString(String.valueOf(i / xScale), i - 10, -15);  // Adjust text position and orientation
        }
        for (int i = -getHeight() / 2; i < getHeight() / 2; i += 50) {
            g2.drawLine(-5, i, 5, i);
            g2.drawString(String.valueOf(i / yScale), 10, -i + 5);  // Adjust text position and orientation
        }

        // Reapply Y scale inversion to continue drawing the graph
        g2.scale(1, -1);
        
        g2.setColor(Color.RED);
        for (double x = -getWidth() / (2 * xScale); x <= getWidth() / (2 * xScale); x += 0.001) {
            double y = evaluatePolynomial(x, coefficients);
            int scaledX = (int) (x * xScale);
            int scaledY = (int) (y * yScale);
            g2.fillOval(scaledX - 1, scaledY - 1, 2, 2); 
        }
    }
}



        

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            NewtonRaphson frame = new NewtonRaphson();
            frame.setVisible(true);
        });
    }
}
