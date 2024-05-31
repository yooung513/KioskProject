package coffee.kiosk.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CafemenuController implements Initializable {


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

    @FXML
    private void americano() {
        System.out.println("americano");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}