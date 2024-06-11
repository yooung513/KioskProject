package coffee.kiosk.Controller;

import coffee.kiosk.Service.CafemenuGetimageService;
import coffee.kiosk.Service.OptionService;
import coffee.kiosk.model.Orderlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import coffee.kiosk.model.Menuimg;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;
import coffee.kiosk.model.TotalOrderAmount;

public class OptionController implements Initializable {

    private CafemenuController cafemenuController;

//    // CafemenuController 인스턴스를 주입하는 setter 메서드 , cafemenucontroller와 의존성 주입
//    public void setCafemenuController(CafemenuController cafemenuController){
//        this.cafemenuController = cafemenuController;
//    }

    public OptionController(Menuimg selectedMenuimg , CafemenuController cafemenuController) throws SQLException {
        this.selectedMenuimg = selectedMenuimg;
        this.optionService = new OptionService(selectedMenuimg);
        this.totalorder = TotalOrderAmount.getInstance();
        this.cafemenuController = cafemenuController;
    }

    private TotalOrderAmount totalOrderAmount;
    public VBox vboxContainer;
    private Menuimg selectedMenuimg;
    private TotalOrderAmount totalorder;
    private OptionService optionService;

    @FXML
    public Button putOnButton;
    @FXML
    public Button cancelbutton;
    @FXML
    public Label selectname;
    @FXML
    public Label selectprice;
    @FXML
    public ImageView selectimage;
    @FXML
    public HBox buttonContainer1;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 선택한 이미지를 가지고 오기 위함
        String imageUrl = selectedMenuimg.getFood_img();
        Image image = new Image(imageUrl);
        selectimage.setImage(image);
        selectname.setText(selectedMenuimg.getFood_name());
        selectprice.setText(String.valueOf(selectedMenuimg.getFood_price()));


        OptionService optionService1 = new OptionService(selectedMenuimg);
        List<String> optionName = null;
        List<Integer> optionPrice = null;
        try {
            optionName = optionService1.getOptionName(selectedMenuimg);
            optionPrice = optionService1.getOptionPrice(selectedMenuimg);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (optionName.isEmpty()) {
            showAlert("옵션이 없습니다.");
        } else {
            String firstOption = optionName.getFirst();

            if (firstOption.equals("HOT")) {
                int[][] indices = {
                        {0, 1, 2, 3, 4},    // 0번째 HBox에 들어갈 인덱스
                        {5, 6, 7},          // 1번째 HBox에 들어갈 인덱스
                        {-1, 8},            // 2번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                        {-1, 9},            // 3번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                        {-1, 10}            // 4번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                };

                for (int i = 0; i < indices.length; i++) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(40);

                    for (int j : indices[i]) {
                        Button button = new Button();
                        button.setPrefSize(120, 130);
                        button.setMinSize(120, 130);
                        button.setText(j == -1 ? optionName.getLast() : optionName.get(j));
                        hbox.getChildren().add(button);
                        List<Integer> finalOptionPrice = optionPrice;
                        button.setOnMouseClicked(event -> {
                            totalorder.addOptionOrderAmount(finalOptionPrice.get(j+1));

                        });
                        if (j == 2 || j == 3 || j == 4) {
                            button.setVisible(false);
                        } else if (j == 1) {
                            for (Node node : hbox.getChildren()) {
                                if (node instanceof Button) {
                                    int index = hbox.getChildren().indexOf(node);
                                    if (index == 1) {
                                        ((Button) node).setOnMouseClicked(event -> {
                                            for (Node childNode : hbox.getChildren()) {
                                                if (childNode instanceof Button) {
                                                    int childIndex = hbox.getChildren().indexOf(childNode);
                                                    if (childIndex >= 2 && childIndex <= 4) {
                                                        childNode.setVisible(true);
                                                    }
                                                }
                                            }
                                            hbox.getChildren().remove(node);
                                            hbox.getChildren().remove(0);

                                            Node button2 = hbox.getChildren().get(0);
                                            Node button3 = hbox.getChildren().get(1);
                                            Node button4 = hbox.getChildren().get(2);
                                            hbox.getChildren().remove(button2);
                                            hbox.getChildren().remove(button3);
                                            hbox.getChildren().remove(button4);
                                            hbox.getChildren().add(0, button2);
                                            hbox.getChildren().add(1, button3);
                                            hbox.getChildren().add(2, button4);
                                        });
                                    }
                                }
                            }
                        }
                    }
                    vboxContainer.setSpacing(20);
                    vboxContainer.getChildren().add(hbox);
                }
            } else if (firstOption.equals("ICE")) {
                int[][] indices = {
                        {1, 2, 3},    // 0번째 HBox에 들어갈 인덱스
                        {4, 5, 6},          // 1번째 HBox에 들어갈 인덱스
                        {-1, 7},            // 2번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                        {-1, 8},            // 3번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                        {-1, 9}            // 4번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                };
                for (int i = 0; i < indices.length; i++) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(40);

                    for (int j : indices[i]) {
                        Button button = new Button();
                        button.setPrefSize(120, 130);
                        button.setMinSize(120, 130);
                        button.setText(j == -1 ? optionName.getLast() : optionName.get(j));
                        hbox.getChildren().add(button);
                    }
                    vboxContainer.setSpacing(20);
                    vboxContainer.getChildren().add(hbox);
                }
            } else if (firstOption.equals("샷 연하게")) {
                int[][] indices = {
                        {0, 1, 2},          // 1번째 HBox에 들어갈 인덱스
                        {-1, 3},            // 2번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                        {-1, 4},            // 3번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                        {-1, 5}            // 4번째 HBox에 들어갈 인덱스, -1은 마지막 요소
                };
                for (int i = 0; i < indices.length; i++) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(40);

                    for (int j : indices[i]) {
                        Button button = new Button();
                        button.setPrefSize(120, 130);
                        button.setMinSize(120, 130);
                        button.setText(j == -1 ? optionName.getLast() : optionName.get(j));
                        hbox.getChildren().add(button);
                    }
                    vboxContainer.setSpacing(20);
                    vboxContainer.getChildren().add(hbox);
                }
            }
        }

        cancelbutton.setOnMouseClicked(event -> {
            try {
                Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
                Scene scene = new Scene(home, 500, 900);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                Stage root = (Stage) cancelbutton.getScene().getWindow();
                root.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        putOnButton.setOnMouseClicked(event ->{
            try {
                totalorder.addSelectedOrderAmount(selectedMenuimg.getFood_price());
                System.out.println("selectecdOrder putonbutton : " + totalorder.getSelectedOrderAmount());

                // Orderlist 클래스의 싱글톤 인스턴스를 가져옴
                Orderlist orderlist = Orderlist.getInstance();

                // 새로운 주문 아이템 생성 및 추가
                Orderlist.OrderItem orderItem = new Orderlist.OrderItem(
                        selectedMenuimg.getFood_name(), // 음식 이름
                        1,                              // 기본 수량은 1로 설정
                        selectedMenuimg.getFood_price(), // 음식 가격
                        null , 0             // 옵션 이름 , 옵션 가격
                );

                // Orderlist의 orderItems 리스트에 주문 아이템 추가
                orderlist.getOrderItems().add(orderItem);


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
                Parent home = loader.load();

                cafemenuController = loader.getController();
                cafemenuController.handleAddToCart(orderlist.getOrderItems());
                Stage currentStage = (Stage) putOnButton.getScene().getWindow();
                Scene scene = new Scene(home , 500 , 900);

                currentStage.setScene(scene);
                currentStage.setResizable(false);
                currentStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
        // dessert 옵션 선택 시 옵션없음 알림 창
        private void showAlert (String message){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("알림");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        // option -> cafemenu
        public void gocafemenu () throws Exception {
            try {
                Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
                Scene scene = new Scene(home, 500, 900);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                Stage root = (Stage) cancelbutton.getScene().getWindow();
                root.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


