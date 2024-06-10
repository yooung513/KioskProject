package coffee.kiosk.Controller;

import coffee.kiosk.DAO.CafemenuLoginRepository;
import coffee.kiosk.Service.CafemenuService;
import coffee.kiosk.Service.ManagementService;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 *  Date    : 2024.05.31
 *  Author  : 오재혁
 *  Summary : 메인 화면 컨트롤러
 */


public class CafemenuLoginController implements Initializable {

    CafemenuService managementService = new CafemenuService();

    @FXML Label logo;

    @FXML
    private Pagination menuPagination;

    @FXML
    private Label displayLabel;

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


        private List<String> try_login = new ArrayList<>();
        String currentInput;
        String finalLogin;

        public void click1(MouseEvent mouseEvent) {
        try_login.add("1");
        currentInput = String.join("",try_login);
        displayLabel.setText(currentInput);
        }

        public void click2(MouseEvent mouseEvent) {
            try_login.add("2");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click3(MouseEvent mouseEvent) {
            try_login.add("3");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click4(MouseEvent mouseEvent) {
            try_login.add("4");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click5(MouseEvent mouseEvent) {
            try_login.add("5");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click6(MouseEvent mouseEvent) {
            try_login.add("6");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click7(MouseEvent mouseEvent) {
            try_login.add("7");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click8(MouseEvent mouseEvent) {
            try_login.add("8");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click9(MouseEvent mouseEvent) {
            try_login.add("9");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        public void click0(MouseEvent mouseEvent) {
            try_login.add("0");
            currentInput = String.join("",try_login);
            displayLabel.setText(currentInput);
        }

        // cafemenuLogin -> cafemenu
        public void clickCancel(MouseEvent mouseEvent) {
            try {
                Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
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

        public void clickDelete(MouseEvent mouseEvent) {
        if (!try_login.isEmpty()){
            try_login.removeLast();
            finalLogin = String.join("",try_login);
            displayLabel.setText(String.valueOf(finalLogin));
        }
        }


        public void clickAdd(MouseEvent mouseEvent) throws SQLException {
            String password = managementService.getCode();
            finalLogin = String.join("",try_login);

            if (finalLogin.equals(password) ){
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




}