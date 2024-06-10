package coffee.kiosk.Controller;

import coffee.kiosk.DTO.Food;
import coffee.kiosk.DTO.FoodOptions;
import coffee.kiosk.DTO.PossibleOptions;
import coffee.kiosk.Service.ManagementService;
import coffee.kiosk.model.OptionsSingleton;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;


/**
 *  Date    : 2024.06.04
 *  Author  : 이다영
 *  Summary : 메뉴 리스트 관리 컨트롤러
 */

public class ManageFoodListController {

    ManagementService managementService = new ManagementService();

    @FXML private Label foodIdLabel;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;

    @FXML private Button chkCancelBtn;

    @FXML private ImageView imageView;
    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private Button insertBtn;
    @FXML private VBox insertContainer;
    @FXML private Label optionIdLabel;
    @FXML private Label optionNameLabel;
    @FXML private CheckBox optionChk;

    @FXML private ChoiceBox<String> choiceBox;

    Map<Integer, String> optionMap = new HashMap<>();
    
    String imgUri = "image/logo.png";
    int deleteId;

    public void setData(Food food) {
        foodIdLabel.setText(String.valueOf(food.getFoodId()));
        nameLabel.setText(food.getFoodName());
        priceLabel.setText(String.valueOf(food.getFoodPrice()));
    }

    public void  setOptionData(FoodOptions option) {
        optionIdLabel.setText(String.valueOf(option.getOptionId()));
        optionNameLabel.setText(option.getOptionName());

        // CheckBox 선택 상태 변경 시 싱글톤에 반영 -> set으로 아이디 관리
        optionChk.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                OptionsSingleton.getInstance().addOption(option.getOptionId());
            } else {
                OptionsSingleton.getInstance().removeOption(option.getOptionId());
            }
        });
    }

    public void viewEdit(MouseEvent mouseEvent) {
    }



    // 메뉴 삭제하기
    public void viewDelete(MouseEvent mouseEvent) {
        try {

            Parent popupDel = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupdelete.fxml"));
            Scene delScene = new Scene(popupDel,450, 350);

            Stage stage = new Stage();
            stage.setScene(delScene);
            stage.setResizable(false);
            stage.show();

            deleteId = Integer.parseInt(foodIdLabel.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goCancel(MouseEvent mouseEvent) {
        Stage nowStage = (Stage) chkCancelBtn.getScene().getWindow();
        nowStage.close();
    }

    public void goDelete(MouseEvent mouseEvent) {
        // 메뉴 컨트롤러 -> 서비스 -> 진행 하면 됨
    }


    // 메뉴 등록하기
    // 1. 등록 화면 불러오기
    public void viewInsert() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/menuinsert.fxml"));
            Parent menuInsert = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(menuInsert, 500, 900));
            stage.setResizable(false);
            stage.show();

            ManageFoodListController controller = loader.getController();
            controller.setOptionList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setOptionList() {
        try {

            List<FoodOptions> optionsList = managementService.findOptions();
            for(FoodOptions option : optionsList) {

                optionMap.put(option.getOptionId(), option.getOptionName());
                FXMLLoader fxmlInsertOption = new FXMLLoader();
                fxmlInsertOption.setLocation(getClass().getResource("/coffee/kiosk/insertoptions.fxml"));

                HBox hBox = fxmlInsertOption.load();
                ManageFoodListController controller = fxmlInsertOption.getController();
                controller.setOptionData(option);

                insertContainer.getChildren().add(hBox);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 2. 등록하기
    // 2-1. 이미지 변경하기
    public void changeImg(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("이미지 변경하기");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {

                // 이미지 파일 불러오기
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
                imgUri = selectedFile.getName();

                // 이미지 파일 저장하기
                File dataDir = new File("/dev/kiosk/src/main/resources/image");
                File dataFile = new File(dataDir, imgUri);
                Files.copy(selectedFile.toPath(), dataFile.toPath());

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    // 2-2. 등록 버튼 클릭
    public void goInsert(MouseEvent mouseEvent) {
        try {
            // 예외 처리
            if (choiceBox.getValue() == null || choiceBox.getValue().isEmpty()) {
                Parent manuInsert = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmisscategory.fxml"));
                Scene popupName = new Scene(manuInsert, 450, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (nameField.getText() == null || nameField.getText().isEmpty()) {

                Parent manuInsert = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmissname.fxml"));
                Scene popupName = new Scene(manuInsert, 450, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (priceField.getText() == null || priceField.getText().isEmpty()) {

                Parent manuInsert = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmissprice.fxml"));
                Scene popupName = new Scene(manuInsert, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (!isNumberic(priceField.getText())) {

                Parent manuInsert = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupcheckprice.fxml"));
                Scene popupName = new Scene(manuInsert, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (Integer.parseInt(priceField.getText()) < 100 || Integer.parseInt(priceField.getText()) > 10000) {

                Parent manuInsert = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupcheckpricerange.fxml"));
                Scene popupName = new Scene(manuInsert, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else {
                // 메뉴 등록하기
                String foodName = nameField.getText();

                Food food = new Food();
                food.setMenuId(findId(choiceBox.getValue()));
                food.setFoodName(foodName);
                food.setFoodPrice(Integer.parseInt(priceField.getText()));
                food.setFoodImg("image/" + findCategory(choiceBox.getValue()) + "/" + imgUri);

                List<PossibleOptions> possibleList = new ArrayList<>();
                Set<Integer> checkedOptions = OptionsSingleton.getInstance().getSelectedOptions();
                for (Integer chk : checkedOptions){
                    PossibleOptions options = new PossibleOptions();

                    options.setOptionId(chk);
                    options.setFoodName(foodName);
                    options.setOptionName(optionMap.get(chk));

                    possibleList.add(options);
                }

                int success = managementService.insertFoodAndPossibleOptions(food, possibleList);
                if (success > 0) {

                    Stage nowStage = (Stage) insertBtn.getScene().getWindow();
                    nowStage.close();

                } else {
                    // 메뉴 등록 실패의 경우
                    Parent failFood = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupfailinsert.fxml"));
                    Scene popupName = new Scene(failFood, 500, 350);

                    Stage stage = new Stage();
                    stage.setScene(popupName);
                    stage.setResizable(false);
                    stage.show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNumberic(String check) {
        try {
            Double.parseDouble(check);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String findCategory(String value) {
        switch (value) {
            case "커피"   : return "coffee";
            case "음료"   : return "beverage";
            case "스무디" : return "smoothie";
            case "차"    : return "tea";
            case "디저트" : return "dessert";
        }
        return "";
    }

    private int findId(String value) {
        return switch (value) {
            case "커피" -> 1;
            case "음료" -> 2;
            case "스무디" -> 3;
            case "차" -> 4;
            case "디저트" -> 5;
            default -> 0;
        };
    }


}
