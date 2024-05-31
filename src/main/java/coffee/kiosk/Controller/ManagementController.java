package coffee.kiosk.Controller;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;



/**
 *  Date    : 2024.05.31
 *  Author  : 이다영
 *  Summary : 메뉴 관리 화면 컨트롤러
 */

public class ManagementController implements Initializable {

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

    public void goHome(MouseEvent mouseEvent) {
        FXMLLoader managementLoader = new FXMLLoader(getClass().getResource("/coffee/kiosk/management.fxml"));
        Scene managementScene = new Scene(managementLoader.load());
        Button managementBtn = (Button) scene.lookup("#management");
        managementBtn.setOnAction(event -> {
            stage.setScene(managementScene);
        });
    }
}
