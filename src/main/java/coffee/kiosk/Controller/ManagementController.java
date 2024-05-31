package coffee.kiosk.Controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



/**
 *  Date    : 2024.05.31
 *  Author  : 이다영
 *  Summary : 메뉴 관리 화면 컨트롤러
 */

public class ManagementController implements Initializable {

    @FXML Label logo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void goTotal(MouseEvent e) {
        System.out.println("goTotal");
    }

    public void goCoffee(MouseEvent e) {
    }

    public void goBeverage(MouseEvent mouseEvent) {
    }

    public void goSmoothie(MouseEvent mouseEvent) {
    }

    public void goTea(MouseEvent mouseEvent) {
    }

    public void goDessert(MouseEvent mouseEvent) {
    }

    public void goHome() throws Exception {

        try {
            Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/home.fxml"));
            Scene scene = new Scene(home, 500, 900);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            Stage root = (Stage) logo.getScene().getWindow();
            root.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
