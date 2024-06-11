package coffee.kiosk.Controller;


import coffee.kiosk.DTO.Food;
import coffee.kiosk.DTO.FoodOptions;
import coffee.kiosk.Service.ManagementService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;



/**
 *  Date    : 2024.05.30
 *  Author  : 이다영
 *  Summary : 메뉴 관리 화면 컨트롤러
 */

public class ManagementController implements Initializable{

    @FXML Label logo;
    @FXML VBox foodListContainer;


    @FXML Label totalLabel;
    @FXML Label coffeeLabel;
    @FXML Label beverageLabel;
    @FXML Label smoothieLabel;
    @FXML Label teaLabel;
    @FXML Label dessertLabel;
    @FXML Label optionLabel;
    @FXML TextField searchField;

    ManagementService managementService = new ManagementService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Food> foodList = null;
        try {
            foodList = managementService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setFoodList(foodList);
        setDesign(0);
    }

    public void goTotal(MouseEvent e) throws SQLException {
        List<Food> foodList = managementService.findAll();
        setFoodList(foodList);
        setDesign(0);
    }

    public void goCoffee(MouseEvent e) throws SQLException {
        List<Food> foodList = managementService.findByMenu(1);
        setFoodList(foodList);
        setDesign(1);
    }

    public void goBeverage(MouseEvent mouseEvent) throws SQLException {
        List<Food> foodList = managementService.findByMenu(2);
        setFoodList(foodList);
        setDesign(2);
    }

    public void goSmoothie(MouseEvent mouseEvent) throws SQLException {
        List<Food> foodList = managementService.findByMenu(3);
        setFoodList(foodList);
        setDesign(3);
    }

    public void goTea(MouseEvent mouseEvent) throws SQLException {
        List<Food> foodList = managementService.findByMenu(4);
        setFoodList(foodList);
        setDesign(4);
    }

    public void goDessert(MouseEvent mouseEvent) throws SQLException {
        List<Food> foodList = managementService.findByMenu(5);
        setFoodList(foodList);
        setDesign(5);
    }

    public void goOptions(MouseEvent mouseEvent) throws SQLException, IOException {
        List<FoodOptions> optionsList = managementService.findOptions();

        try {
            foodListContainer.getChildren().clear();

            for (FoodOptions options : optionsList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/coffee/kiosk/manageoptionlist.fxml"));

                HBox hBox = fxmlLoader.load();
                ManageFoodListController mflController = fxmlLoader.getController();
                mflController.setOption(options);

                foodListContainer.getChildren().add(hBox);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        setDesign(6);
    }



    public void goSearch(MouseEvent mouseEvent) throws SQLException {
        String word = searchField.getText();
        List<Food> foodList = managementService.findByWord(word);
        setFoodList(foodList);
        setDesign(0);
    }


    // 첫 화면 이동 메소드
    public void goHome() throws Exception {

        try {
            Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/home.fxml"));
            Scene scene = new Scene(home, 500, 900);

            Stage stage = (Stage) logo.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void setFoodList(List<Food> foodList) {
        try {
                foodListContainer.getChildren().clear();

                for (Food food : foodList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/coffee/kiosk/managefoodlist.fxml"));

                    HBox hBox = fxmlLoader.load();
                    ManageFoodListController mflController = fxmlLoader.getController();
                    mflController.setData(food);

                    foodListContainer.getChildren().add(hBox);
                }
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void goInsert(MouseEvent mouseEvent) {
        ManageFoodListController foodListController = new ManageFoodListController();

        foodListController.viewInsert();
    }

    public void goInsertOpt(MouseEvent mouseEvent) {
        ManageFoodListController foodListController = new ManageFoodListController();
        foodListController.viewInsertOpt();
    }

    public void setDesign(int menuId) {
        try {

            DropShadow dropShadow = new DropShadow();
            totalLabel.setTextFill(Color.web("#000000"));
            totalLabel.setEffect(null);
            coffeeLabel.setTextFill(Color.web("#000000"));
            coffeeLabel.setEffect(null);
            beverageLabel.setTextFill(Color.web("#000000"));
            beverageLabel.setEffect(null);
            smoothieLabel.setTextFill(Color.web("#000000"));
            smoothieLabel.setEffect(null);
            teaLabel.setTextFill(Color.web("#000000"));
            teaLabel.setEffect(null);
            dessertLabel.setTextFill(Color.web("#000000"));
            dessertLabel.setEffect(null);
            optionLabel.setTextFill(Color.web("#000000"));
            optionLabel.setEffect(null);

            switch (menuId) {
                case 0 :
                    totalLabel.setTextFill(Color.web("#fafafa"));
                    totalLabel.setEffect(dropShadow);
                    break;
                case 1 :
                    searchField.clear();
                    coffeeLabel.setTextFill(Color.web("#fafafa"));
                    coffeeLabel.setEffect(dropShadow);
                    break;
                case 2 :
                    searchField.clear();
                    beverageLabel.setTextFill(Color.web("#fafafa"));
                    beverageLabel.setEffect(dropShadow);
                    break;
                case 3 :
                    searchField.clear();
                    smoothieLabel.setTextFill(Color.web("#fafafa"));
                    smoothieLabel.setEffect(dropShadow);
                    break;
                case 4 :
                    searchField.clear();
                    teaLabel.setTextFill(Color.web("#fafafa"));
                    teaLabel.setEffect(dropShadow);
                    break;
                case 5 :
                    searchField.clear();
                    dessertLabel.setTextFill(Color.web("#fafafa"));
                    dessertLabel.setEffect(dropShadow);
                    break;
                case 6 :
                    searchField.clear();
                    optionLabel.setTextFill(Color.web("#fafafa"));
                    optionLabel.setEffect(dropShadow);



                    break;

                default:
                    break;
            }

        } catch (Exception e) {

        }

    }
}
