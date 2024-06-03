package coffee.kiosk.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *  Date    : 2024.05.31
 *  Author  : 오재혁
 *  Summary : 메인 화면 컨트롤러
 */


public class CafemenuController implements Initializable {



    @FXML Label logo;

    @FXML
    private Pagination menuPagination;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        goTotal();
    }

    @FXML
    private void goTotal() {
        System.out.println("go total");
    }
    @FXML
    private void goCoffee() {
        System.out.println("go Coffee");
    }
    @FXML
    private void goDrink() {
        System.out.println("go Drink");
    }
    @FXML
    private void goSmoothie() {
        System.out.println("go Smoothie");
    }
    @FXML
    private void goTea() {
        System.out.println("go Tea");
    }
    @FXML
    private void goDessert() { System.out.println("go Dessert"); }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // pagination 으로 페이지 동적 생성 위해서 초기화
//    public void initialize(){
//        setupPagination();
//    }
//
//
//
//    private void setupData(){
//    }
//
//    private void setupPagination(){
//        int itemsPerPage = 9;
//        int pageCount = (int) Math.ceil((double)datalist.size() / itemsPerPage);
//    }




    // cafemenu scene -> home scene
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

    // cafemenu -> cafelogin
    public void goLogin() throws Exception {
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/cafemenuLogin.fxml"));
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