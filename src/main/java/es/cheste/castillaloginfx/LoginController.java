package es.cheste.castillaloginfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    Map<String, String> userCredentials = new HashMap<>();
    @FXML
    private ImageView passwordIcon;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView usenameIcon;
    @FXML
    private PasswordField passwordTextfield;


    public LoginController() {
        userCredentials.put("user1", "password123");
        userCredentials.put("user2", "securePass456");
        userCredentials.put("admin", "adminPass");
    }

    @FXML
    private TextField usernameTextfield;


    @FXML
    private Button loginButton;
    @FXML
    private Label loginLabel;


  @FXML
    protected void onButtonLoginClick(ActionEvent event) {

        if (checkCredentials()) {
            // Si las credenciales son correctas
            loginLabel.setText("¡Inicio de sesión correcto!");
            loginLabel.setStyle("-fx-text-fill: green;"); // Color verde para el texto
        } else {
            // Si las credenciales son incorrectas
            loginLabel.setText("Usuario o contraseña incorrectos.");
            loginLabel.setStyle("-fx-text-fill: red;"); // Color rojo para el texto
        }


    }


    protected boolean checkCredentials() {

        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();

        if (userCredentials.containsKey(username)) {

            if (userCredentials.get(username).equals(password)) {

                return true;


            }


        }

        return false;

    }


}