package es.cheste.castillaloginfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CalculadoraApplication extends Application {

    double x = 0;
    double y = 0;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmloader = new FXMLLoader(CalculadoraApplication.class.getResource("calculadora.fxml"));

        Scene scene = new Scene(fxmloader.load(), 400, 650);
        scene.setFill(null);


        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() - x);
                stage.setY(mouseEvent.getScreenY() - y);
            }

        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Calculadora Castilla");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {


        launch();

    }
}
