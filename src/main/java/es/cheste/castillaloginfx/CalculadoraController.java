package es.cheste.castillaloginfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraController {


    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;


    @FXML
    private Button button8;


    @FXML
    private Button button9;


    @FXML
    private Button buttonSuma;


    @FXML
    private Button buttonPunto;


    @FXML
    private Button buttonMultiplicacion;

    @FXML
    private Button buttonDivision;

    @FXML
    private Button buttonResta;

    @FXML
    private Button buttonIgual;

    @FXML
    private Button buttonSalir;

    @FXML
    private Label labelResultado;


    List<String> operaciones = new ArrayList<>();
    List<Double> valores = new ArrayList<>();


    @FXML
    protected void onButtonClick(ActionEvent event) {
        Button boton = (Button) event.getSource();
        String valor = boton.getText();

        if (labelResultado.getText().equals("0") || labelResultado.getText().equals("")) {
            labelResultado.setText(valor);
        } else {
            labelResultado.setText(labelResultado.getText() + valor);
        }
    }

    @FXML
    protected void onButtonSumaClick(ActionEvent event) {
        valores.add(Double.parseDouble(labelResultado.getText()));
        Button boton = (Button) event.getSource();
        operaciones.add("+");
        labelResultado.setText("");
    }

    @FXML
    protected void onButtonRestaClick(ActionEvent event) {
        valores.add(Double.parseDouble(labelResultado.getText()));
        Button boton = (Button) event.getSource();
        operaciones.add("-");
        labelResultado.setText("");
    }

    @FXML
    protected void onButtonMultiplicacionClick(ActionEvent event) {
        valores.add(Double.parseDouble(labelResultado.getText()));
        Button boton = (Button) event.getSource();
        operaciones.add("X");
        labelResultado.setText("");
    }

    @FXML
    protected void onButtonDivisionClick(ActionEvent event) {
        valores.add(Double.parseDouble(labelResultado.getText()));
        Button boton = (Button) event.getSource();
        operaciones.add("/");
        labelResultado.setText("");

    }


    @FXML
    protected void onButtonPuntoClick(ActionEvent event) {
        if (!labelResultado.getText().contains(".")) {
            labelResultado.setText(labelResultado.getText() + ".");
        }
    }

    @FXML
    protected void onButtonIgualClick(ActionEvent event) {
        if (!labelResultado.getText().isEmpty()) {
            valores.add(Double.parseDouble(labelResultado.getText())); // Guardar último valor
        }
        labelResultado.setText(realizarOperacion(operaciones, valores)); // Resolver operaciones
        operaciones.clear(); // Limpiar lista de operaciones
        valores.clear(); // Limpiar lista de valores
    }

    @FXML
    protected void onButtonSalirClick(ActionEvent event) {
        System.exit(0);
    }


    private String realizarOperacion(List<String> operaciones, List<Double> valores) {
        if (operaciones == null || valores == null || operaciones.isEmpty() || valores.size() <= 1) {
            throw new IllegalArgumentException("Operaciones y valores deben ser listas válidas y con datos suficientes.");
        }

        // Primera pasada: manejar multiplicación y división
        for (int i = 0; i < operaciones.size(); i++) {
            String operacion = operaciones.get(i);
            if (operacion.equals("X") || operacion.equals("/")) {
                if (i + 1 < valores.size()) {
                    double resultado;
                    if (operacion.equals("X")) {
                        resultado = multiplicar(valores.get(i), valores.get(i + 1));
                    } else {
                        if (valores.get(i + 1) == 0) {
                            throw new ArithmeticException("División por cero no permitida.");
                        }
                        resultado = dividir(valores.get(i), valores.get(i + 1));
                    }
                    valores.set(i, resultado);
                    valores.remove(i + 1);
                    operaciones.remove(i);
                    i--; // Ajustar índice tras la eliminación
                }
            }
        }

        // Segunda pasada: manejar suma y resta
        for (int i = 0; i < operaciones.size(); i++) {
            String operacion = operaciones.get(i);
            if (operacion.equals("+") || operacion.equals("-")) {
                if (i + 1 < valores.size()) {
                    double resultado;
                    if (operacion.equals("+")) {
                        resultado = sumar(valores.get(i), valores.get(i + 1));
                    } else {
                        resultado = restar(valores.get(i), valores.get(i + 1));
                    }
                    valores.set(i, resultado);
                    valores.remove(i + 1);
                    operaciones.remove(i);
                    i--; // Ajustar índice tras la eliminación
                }
            }
        }

        // Devolver el resultado final como cadena
        return String.valueOf(valores.get(0));
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        return a / b;
    }

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }




}
