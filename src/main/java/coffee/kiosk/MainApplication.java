package coffee.kiosk;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @FXML Button management;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home.fxml"));
        System.setProperty("prism.lcdtext", "false");
        Scene scene = new Scene(fxmlLoader.load(), 500, 900);

        // 개점 처리
        // home scene -> cafemenu scene move
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cafemenu.fxml"));
        Scene cafemenuScene = new Scene(loader.load());
        Button button = (Button) scene.lookup("#openstore");
        button.setOnAction(event -> {
            stage.setScene(cafemenuScene);
        });

        stage.setTitle("Welcome To JH Cafe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    // 메뉴 관리 이동 메소드
    public void goManagement() {
        try {

            FXMLLoader managementLoader = new FXMLLoader(getClass().getResource("management.fxml"));
            Scene scene = new Scene(managementLoader.load(), 500, 900);

            Stage stage = (Stage) management.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goCafemenu() {
        try {

            FXMLLoader managementLoader = new FXMLLoader(getClass().getResource("cafemenu.fxml"));
            Scene scene = new Scene(managementLoader.load(), 500, 900);

            Stage stage = (Stage) management.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
