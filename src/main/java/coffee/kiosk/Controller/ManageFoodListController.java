package coffee.kiosk.Controller;

import coffee.kiosk.DTO.Food;
import coffee.kiosk.DTO.FoodOptions;
import coffee.kiosk.DTO.PossibleOptions;
import coffee.kiosk.Service.ManagementService;
import coffee.kiosk.model.IdSingleton;
import coffee.kiosk.model.OptionsSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.*;


/**
 *  Date    : 2024.06.04
 *  Author  : 이다영
 *  Summary : 메뉴 리스트 관리 컨트롤러
 */

public class ManageFoodListController {

    ManagementService managementService = new ManagementService();

    @FXML Label logo;
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
    @FXML private Button deleteBtn;
    @FXML private Button updateBtn;

    @FXML private ChoiceBox<String> choiceBox;

    Map<Integer, String> optionMap = new HashMap<>();
    String imgUri = "image/logo.png";

    // 데이터 설정
    public void setData(Food food) {
        foodIdLabel.setText(String.valueOf(food.getFoodId()));
        nameLabel.setText(food.getFoodName());
        priceLabel.setText(String.valueOf(food.getFoodPrice()));
    }

    public void setOption(FoodOptions option) {
        optionIdLabel.setText(String.valueOf(option.getOptionId()));
        nameLabel.setText(option.getOptionName());
        priceLabel.setText(String.valueOf(option.getOptionPrice()));
    }

    public void setOptionData(FoodOptions option) {
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

    public void setOptionData(List<Integer> optionIdList, FoodOptions option) {
        optionIdLabel.setText(String.valueOf(option.getOptionId()));
        optionNameLabel.setText(option.getOptionName());

        if (optionIdList.contains(option.getOptionId())) {
            optionChk.setSelected(true);
            OptionsSingleton.getInstance().addOption(option.getOptionId());
        }

        optionChk.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                OptionsSingleton.getInstance().addOption(option.getOptionId());
            } else {
                OptionsSingleton.getInstance().removeOption(option.getOptionId());
            }
        });

    }


    // 메뉴 수정하기
    // 1. 수정 화면 가져오기
    // 1-1. 수정 화면 띄우기
    public void viewEdit(MouseEvent mouseEvent) throws SQLException {
        int foodId = Integer.parseInt(foodIdLabel.getText());
        Food food = managementService.findByFoodId(foodId);
        List<PossibleOptions> options = managementService.findPossibleOptions(foodId);
        IdSingleton.getInstance().setId(foodId);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/menuupdate.fxml"));
            Parent menuUpdate = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(menuUpdate, 500, 900));
            stage.setResizable(false);
            stage.show();

            ManageFoodListController controller = loader.getController();
            controller.setUpdatePage(food);
            controller.setOptionList(options);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1-2. 초기 음료 화면 설정
    private void setUpdatePage(Food food) {
        Image image = new Image(food.getFoodImg());
        imageView.setImage(image);
        nameField.setText(food.getFoodName());
        priceField.setText(String.valueOf(food.getFoodPrice()));
        choiceBox.setValue(findCategoryKor(food.getMenuId()));
    }

    // 1-3. 초기 옵션 화면 설정
    private void setOptionList(List<PossibleOptions> options) {
        try {
            List<Integer> optionIdList = new ArrayList<>();
            for (PossibleOptions o : options) {
                optionIdList.add(o.getOptionId());
            }

            List<FoodOptions> optionsList = managementService.findOptions();
            for(FoodOptions option : optionsList) {

                optionMap.put(option.getOptionId(), option.getOptionName());
                FXMLLoader fxmlInsertOption = new FXMLLoader();
                fxmlInsertOption.setLocation(getClass().getResource("/coffee/kiosk/insertoptions.fxml"));

                HBox hBox = fxmlInsertOption.load();
                ManageFoodListController controller = fxmlInsertOption.getController();
                controller.setOptionData(optionIdList, option);

                insertContainer.getChildren().add(hBox);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 2. 수정하기
    public void goUpdate(MouseEvent mouseEvent) {
        try {
            // 예외 처리
            if (choiceBox.getValue() == null || choiceBox.getValue().isEmpty()) {
                Parent manuUpdate = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmisscategory.fxml"));
                Scene popupName = new Scene(manuUpdate, 450, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (nameField.getText() == null || nameField.getText().isEmpty()) {

                Parent manuUpdate = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmissname.fxml"));
                Scene popupName = new Scene(manuUpdate, 450, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (priceField.getText() == null || priceField.getText().isEmpty()) {

                Parent manuUpdate = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmissprice.fxml"));
                Scene popupName = new Scene(manuUpdate, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (!isNumberic(priceField.getText())) {

                Parent manuUpdate = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupcheckprice.fxml"));
                Scene popupName = new Scene(manuUpdate, 500, 350);

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
                // 메뉴 수정하기
                String foodName = nameField.getText();
                int foodId = IdSingleton.getInstance().getId();
                String img;

                Food food = new Food();
                food.setFoodId(foodId);
                food.setMenuId(findId(choiceBox.getValue()));
                food.setFoodName(foodName);
                food.setFoodPrice(Integer.parseInt(priceField.getText()));
                if (imgUri.contains("logo")) {
                    img = "image/logo.png";
                } else {
                    img = "image/" + findCategory(choiceBox.getValue()) + "/" + imgUri;
                }
                food.setFoodImg(img);

                List<PossibleOptions> possibleList = new ArrayList<>();
                Set<Integer> checkedOptions = OptionsSingleton.getInstance().getSelectedOptions();
                for (Integer chk : checkedOptions){
                    PossibleOptions options = new PossibleOptions();

                    options.setOptionId(chk);
                    options.setFoodName(String.valueOf(foodName));
                    options.setOptionName(optionMap.get(chk));
                    options.setFoodId(foodId);

                    possibleList.add(options);
                }

                int success = managementService.updateFoodAndPO(food, possibleList);
                if (success > 0) {      // 메뉴 등록 성공

                    Stage nowStage = (Stage) updateBtn.getScene().getWindow();
                    nowStage.close();

                    Parent popup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupokupdate.fxml"));
                    Scene popupName = new Scene(popup, 450, 350);

                    Stage stage = new Stage();
                    stage.setScene(popupName);
                    stage.setResizable(false);
                    stage.show();

                } else {                // 메뉴 등록 실패
                    Parent popup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupfailupdate.fxml"));
                    Scene popupName = new Scene(popup, 450, 350);

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




    // 메뉴 삭제하기
    public void viewDelete(MouseEvent mouseEvent) {
        try {

            Parent popupDel = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupdelete.fxml"));
            Scene delScene = new Scene(popupDel,450, 350);

            Stage stage = new Stage();
            stage.setScene(delScene);
            stage.setResizable(false);
            stage.show();

            IdSingleton.getInstance().setId(Integer.parseInt(foodIdLabel.getText()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goDelete(MouseEvent mouseEvent) throws Exception {
        int result = managementService.deleteMenu(IdSingleton.getInstance().getId());

        if (result > 0) {   // 메뉴 삭제 성공

            Stage nowStage = (Stage) deleteBtn.getScene().getWindow();
            nowStage.close();

            Parent successPopup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupokdelete.fxml"));
            Scene popupName = new Scene(successPopup, 450, 350);

            Stage stage = new Stage();
            stage.setScene(popupName);
            stage.setResizable(false);
            stage.show();

        } else {            // 메뉴 삭제 실패
            Parent failPopup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupfaildelete.fxml"));
            Scene popupName = new Scene(failPopup, 450, 350);

            Stage stage = new Stage();
            stage.setScene(popupName);
            stage.setResizable(false);
            stage.show();
        }
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
                String img;

                Food food = new Food();
                food.setMenuId(findId(choiceBox.getValue()));
                food.setFoodName(foodName);
                food.setFoodPrice(Integer.parseInt(priceField.getText()));
                if (imgUri.contains("logo")) {
                    img = "image/logo.png";
                } else {
                    img = "image/" + findCategory(choiceBox.getValue()) + "/" + imgUri;
                }
                food.setFoodImg(img);

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
                if (success > 0) {      // 메뉴 등록 성공

                    Stage nowStage = (Stage) insertBtn.getScene().getWindow();
                    nowStage.close();

                    Parent successPopup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupokinsert.fxml"));
                    Scene popupName = new Scene(successPopup, 450, 350);

                    Stage stage = new Stage();
                    stage.setScene(popupName);
                    stage.setResizable(false);
                    stage.show();

                } else {                // 메뉴 등록 실패
                    Parent failPopup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupfailinsert.fxml"));
                    Scene popupName = new Scene(failPopup, 450, 350);

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


    // 옵션 수정하기
    // 1-1. 화면 표출
    public void viewOptionEdit(MouseEvent mouseEvent) throws SQLException {
        int optionId = Integer.parseInt(optionIdLabel.getText());
        FoodOptions foodOptions = managementService.findOptionById(optionId);
        IdSingleton.getInstance().setId(optionId);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/optionupdate.fxml"));
            Parent optionUpdate = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(optionUpdate, 500, 900));
            stage.setResizable(false);
            stage.show();

            ManageFoodListController controller = loader.getController();
            controller.setUpdatePage(foodOptions);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1-2. 초기 옵션 화면 설정
    private void setUpdatePage(FoodOptions foodOptions) {
        nameField.setText(foodOptions.getOptionName());
        priceField.setText(String.valueOf(foodOptions.getOptionPrice()));
    }

    // 2. 옵션 수정
    public void goOptUpdate(MouseEvent mouseEvent) {
        try {
            // 예외 처리
            if (nameField.getText() == null || nameField.getText().isEmpty()) {

                Parent popup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmissname.fxml"));
                Scene popupName = new Scene(popup, 450, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();
            } else if (priceField.getText() == null || priceField.getText().isEmpty()) {

                Parent popup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupmissprice.fxml"));
                Scene popupName = new Scene(popup, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (!isNumberic(priceField.getText())) {

                Parent popup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupcheckprice.fxml"));
                Scene popupName = new Scene(popup, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();

            } else if (Integer.parseInt(priceField.getText()) < 0 || Integer.parseInt(priceField.getText()) > 10000) {

                Parent popup = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupcheckpricerange.fxml"));
                Scene popupName = new Scene(popup, 500, 350);

                Stage stage = new Stage();
                stage.setScene(popupName);
                stage.setResizable(false);
                stage.show();
            } else {
                // 옵션 수정하기
                FoodOptions foodOptions = new FoodOptions();
                foodOptions.setOptionId(IdSingleton.getInstance().getId());
                foodOptions.setOptionName(nameField.getText());
                foodOptions.setOptionPrice(Integer.parseInt(priceField.getText()));

                int success = managementService.updateOption(foodOptions);
                if (success > 0) {      // 옵션 수정 성공
                    Stage nowStage = (Stage) updateBtn.getScene().getWindow();
                    nowStage.close();

                    Parent successUpt = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupokupdate.fxml"));
                    Scene popup = new Scene(successUpt, 450, 350);

                    Stage stage = new Stage();
                    stage.setScene(popup);
                    stage.setResizable(false);
                    stage.show();

                } else {                // 옵션 수정 실패
                    Parent failUpt = FXMLLoader.load(getClass().getResource("/coffee/kiosk/popupfailupdate.fxml"));
                    Scene popup = new Scene(failUpt, 450, 350);

                    Stage stage = new Stage();
                    stage.setScene(popup);
                    stage.setResizable(false);
                    stage.show();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 옵션 삭제하기



    // 메소드
    // 팝업 창 닫기
    public void goCancel(MouseEvent mouseEvent) {
        Stage nowStage = (Stage) chkCancelBtn.getScene().getWindow();
        nowStage.close();
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
        return switch (value) {
            case "커피" -> "coffee";
            case "음료" -> "beverage";
            case "스무디" -> "smoothie";
            case "차" -> "tea";
            case "디저트" -> "dessert";
            default -> "";
        };
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

    private String findCategoryKor(int value) {
        return switch (value) {
            case 1 -> "커피";
            case 2 -> "음료";
            case 3 -> "스무디";
            case 4 -> "차";
            case 5 -> "디저트";
            default -> "";
        };
    }


}
