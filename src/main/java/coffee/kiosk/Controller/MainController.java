package coffee.kiosk.Controller;

import coffee.kiosk.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        System.setProperty("prism.lcdtext", "false");
        Scene scene = new Scene(fxmlLoader.load(), 500, 800);
        stage.setTitle("Welcome To JH Cafe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
