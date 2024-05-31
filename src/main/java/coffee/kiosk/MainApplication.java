package coffee.kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("home.fxml"));
        System.setProperty("prism.lcdtext", "false");
        Scene scene = new Scene(fxmlLoader.load(), 500, 800);

        // 개점 처리
        // home scene -> cafemenu scene move
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
        Scene cafemenuScene = new Scene(loader.load());
        Button button = (Button) scene.lookup("#openstore");
        button.setOnAction(event -> {
            stage.setScene(cafemenuScene);
        });


        // 메뉴 관리
        FXMLLoader managementLoader = new FXMLLoader(getClass().getResource("/coffee/kiosk/management.fxml"));
        Scene managementScene = new Scene(managementLoader.load());
        Button managementBtn = (Button) scene.lookup("#management");
        managementBtn.setOnAction(event -> {
            stage.setScene(managementScene);
        });

        stage.setTitle("Welcome To JH Cafe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
