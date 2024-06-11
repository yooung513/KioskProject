package coffee.kiosk.Controller;

import coffee.kiosk.Service.CafemenuGetimageService;
import coffee.kiosk.model.Orderlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import coffee.kiosk.model.Menuimg;
import coffee.kiosk.model.TotalOrderAmount;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 *  Date    : 2024.05.31
 *  Author  : 오재혁
 *  Summary : 메인 화면 컨트롤러
 */


public class CafemenuController implements Initializable {
    public static String order_food_name;
    public static int order_food_price;
    public static int count;
    public Button PayButton;
    private Orderlist orderlist;


    public Button plus_button;
    public Label selected_price;
    public Label select_count;
    public Button minus_button;
    public Label selected_foodname;
    public Label coffeeLabel;
    public Label allLabel;
    public Label beverageLabel;
    public Label smoothieLabel;
    public Label teaLabel;
    public Label dessertLabel;
    public HBox menuhbox;
    @FXML
    private  VBox vboxcontainer;
    private TotalOrderAmount totalorder;
    public VBox imgvbox;
    @FXML
    public Label total_price;
    @FXML
    private GridPane cafemenuimg;
    @FXML Label logo;
    @FXML
    private Label welcomeText;

    OrderlistController orderlistController;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // 카페 메뉴를 스크롤 하기 위해 initialize 부분에 객체 생성하여 이미지 , 이름 ,가격 넣음
    List<Menuimg> cafeimgs;
    List<Menuimg> coffeeimgs;
    List<Menuimg> beverageimgs;
    List<Menuimg> smoothieimgs;
    List<Menuimg> teaimgs;
    List<Menuimg> dessertimgs;
    List<Orderlist> orderlists = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            cafeimgs = new ArrayList<>(getCafeimgs());
            coffeeimgs = new ArrayList<>(getCoffeeimgs());
            beverageimgs = new ArrayList<>(getBeverageimgs());
            smoothieimgs = new ArrayList<>(getSmoothieimgs());
            teaimgs = new ArrayList<>(getTeaimgs());
            dessertimgs = new ArrayList<>(getDessertimgs());
            loadMenuItems(cafeimgs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        allLabel.setOnMouseClicked((event -> {
            cafemenuimg.getChildren().clear();
            loadMenuItems(cafeimgs);
        }));

        coffeeLabel.setOnMouseClicked((event -> {
            cafemenuimg.getChildren().clear();
            loadMenuItems(coffeeimgs);
        }));

        beverageLabel.setOnMouseClicked((event -> {
            cafemenuimg.getChildren().clear();
            loadMenuItems(beverageimgs);
        }));

        smoothieLabel.setOnMouseClicked((event -> {
            cafemenuimg.getChildren().clear();
            loadMenuItems(smoothieimgs);
        }));

        teaLabel.setOnMouseClicked((event -> {
            cafemenuimg.getChildren().clear();
            loadMenuItems(teaimgs);
        }));

        dessertLabel.setOnMouseClicked((event -> {
            cafemenuimg.getChildren().clear();
            loadMenuItems(dessertimgs);
        }));

        PayButton.setOnMouseClicked(event ->{
            try {
                Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/payment.fxml"));
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
        });

        this.totalorder = TotalOrderAmount.getInstance();
        totalorder.setTotalOrderAmount(totalorder.getSelectedOrderAmount());
        total_price.setText(String.valueOf(totalorder.getTotalOrderAmount()));
    }


    private void loadMenuItems(List<Menuimg> menuimgs){
        int column =0;
        int row = 0;
        try {
            for(Menuimg menuimg : menuimgs){
                if(menuimgs == coffeeimgs){
                    DropShadow dropShadow = new DropShadow();
                    coffeeLabel.setTextFill(Color.web("#fafafa"));
                    coffeeLabel.setEffect(dropShadow);
                    allLabel.setTextFill(Color.web("#000000"));
                    allLabel.setEffect(null);
                    beverageLabel.setTextFill(Color.web("#000000"));
                    beverageLabel.setEffect(null);
                    smoothieLabel.setTextFill(Color.web("#000000"));
                    smoothieLabel.setEffect(null);
                    teaLabel.setTextFill(Color.web("#000000"));
                    teaLabel.setEffect(null);
                    dessertLabel.setTextFill(Color.web("#000000"));
                    dessertLabel.setEffect(null);
                }else if (menuimgs == cafeimgs){
                    DropShadow dropShadow = new DropShadow();
                    allLabel.setTextFill(Color.web("#fafafa"));
                    allLabel.setEffect(dropShadow);
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
                }else if (menuimgs == beverageimgs){
                    DropShadow dropShadow = new DropShadow();
                    beverageLabel.setTextFill(Color.web("#fafafa"));
                    beverageLabel.setEffect(dropShadow);
                    allLabel.setTextFill(Color.web("#000000"));
                    allLabel.setEffect(null);
                    coffeeLabel.setTextFill(Color.web("#000000"));
                    coffeeLabel.setEffect(null);
                    smoothieLabel.setTextFill(Color.web("#000000"));
                    smoothieLabel.setEffect(null);
                    teaLabel.setTextFill(Color.web("#000000"));
                    teaLabel.setEffect(null);
                    dessertLabel.setTextFill(Color.web("#000000"));
                    dessertLabel.setEffect(null);
                }else if (menuimgs == smoothieimgs){
                    DropShadow dropShadow = new DropShadow();
                    smoothieLabel.setTextFill(Color.web("#fafafa"));
                    smoothieLabel.setEffect(dropShadow);
                    allLabel.setTextFill(Color.web("#000000"));
                    allLabel.setEffect(null);
                    coffeeLabel.setTextFill(Color.web("#000000"));
                    coffeeLabel.setEffect(null);
                    beverageLabel.setTextFill(Color.web("#000000"));
                    beverageLabel.setEffect(null);
                    teaLabel.setTextFill(Color.web("#000000"));
                    teaLabel.setEffect(null);
                    dessertLabel.setTextFill(Color.web("#000000"));
                    dessertLabel.setEffect(null);
                }else if (menuimgs == teaimgs){
                    DropShadow dropShadow = new DropShadow();
                    teaLabel.setTextFill(Color.web("#fafafa"));
                    teaLabel.setEffect(dropShadow);
                    allLabel.setTextFill(Color.web("#000000"));
                    allLabel.setEffect(null); allLabel.setTextFill(Color.web("#000000"));
                    allLabel.setEffect(null);
                    coffeeLabel.setTextFill(Color.web("#000000"));
                    coffeeLabel.setEffect(null);
                    beverageLabel.setTextFill(Color.web("#000000"));
                    beverageLabel.setEffect(null);
                    smoothieLabel.setTextFill(Color.web("#000000"));
                    smoothieLabel.setEffect(null);
                    dessertLabel.setTextFill(Color.web("#000000"));
                    dessertLabel.setEffect(null);
                }else if (menuimgs == dessertimgs){
                    DropShadow dropShadow = new DropShadow();
                    dessertLabel.setTextFill(Color.web("#fafafa"));
                    dessertLabel.setEffect(dropShadow);
                    allLabel.setTextFill(Color.web("#000000"));
                    allLabel.setEffect(null); allLabel.setTextFill(Color.web("#000000"));
                    coffeeLabel.setTextFill(Color.web("#000000"));
                    coffeeLabel.setEffect(null);
                    beverageLabel.setTextFill(Color.web("#000000"));
                    beverageLabel.setEffect(null);
                    smoothieLabel.setTextFill(Color.web("#000000"));
                    smoothieLabel.setEffect(null);
                    teaLabel.setTextFill(Color.web("#000000"));
                    teaLabel.setEffect(null);
                }

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/coffee/kiosk/menuimg.fxml"));

                VBox vBox = fxmlLoader.load();
                MenuimgController menuimgController = fxmlLoader.getController();
                menuimgController.setData(menuimg);

                //Vbox 이벤트 핸들러 설정
                vBox.setOnMouseClicked(event -> {
                    try{
                        handleVboxSelection(menuimg);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                });

                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPrefHeight(400); // 높이를 400으로 설정
                cafemenuimg.getRowConstraints().add(row, rowConstraints);
                cafemenuimg.add(vBox, column , row);
                column++;

                if(column ==3){
                    column =0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleVboxSelection(Menuimg selectedMenuimg) throws IOException {
        Callback<Class<?>, Object> controllerFactory = type -> {
            if (type == OptionController.class) {
                try {
                    total_price.setText(String.valueOf(totalorder.getTotalOrderAmount()));
                    return new OptionController(selectedMenuimg , this);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/option.fxml"));
        loader.setControllerFactory(controllerFactory);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage) logo.getScene().getWindow();
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();

        currentStage.close();

    }

    public void addMenuItems(List<Orderlist.OrderItem> orderlists) throws IOException {
        if (!orderlists.isEmpty()) {
            vboxcontainer.getChildren().clear();
            for (Orderlist.OrderItem item : orderlists) {
                try {
                    // 반복문 내에서 새로 FXMLLoader 인스턴스를 생성합니다.
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/orderlist.fxml"));
                    Parent hbox = loader.load();
                    OrderlistController controller = loader.getController();
                    controller.setData(item.getFoodname(), item.getCount(), item.getFoodprice());
                    vboxcontainer.getChildren().add(hbox);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void handleAddToCart(List<Orderlist.OrderItem> orderItems) throws IOException {
        count = 1;
        addMenuItems(orderItems);

    }



    @FXML
    private List<Menuimg> getCafeimgs() throws SQLException {
        List<Menuimg> ls = new ArrayList<>();
        CafemenuGetimageService service = new CafemenuGetimageService();
        List<String> imagePaths = service.getImage(); // 이미지 경로들을 가져옴
        List<String> namePaths = service.getName(); // 음식 이름 경로들을 가져옴
        List<Integer> pricePaths = service.getPrice(); // 음식 가격 경로들을 가져옴

        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            String menuName = namePaths.get(i); // 인덱스를 활용하여 각 메뉴에 해당하는 이름 가져오기
            int menuPrice = pricePaths.get(i);

            Menuimg menuimg = new Menuimg();
            menuimg.setFood_name(menuName); // 각 메뉴의 이름 설정
            menuimg.setFood_price(menuPrice); // 각 메뉴의 가격 설정
            menuimg.setFood_img(imagePath); // 각 메뉴의 이미지 경로 설정
            ls.add(menuimg);
        }
        return ls;
    }

    @FXML
    private List<Menuimg> getCoffeeimgs() throws SQLException {
        List<Menuimg> ls = new ArrayList<>();
        CafemenuGetimageService service = new CafemenuGetimageService();
        List<String> imagePaths = service.getCoffeeImage(); // 이미지 경로들을 가져옴
        List<String> namePaths = service.getCoffeeName(); // 음식 이름 경로들을 가져옴
        List<Integer> pricePaths = service.getCoffeePrice(); // 음식 가격 경로들을 가져옴

        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            String menuName = namePaths.get(i); // 인덱스를 활용하여 각 메뉴에 해당하는 이름 가져오기
            int menuPrice = pricePaths.get(i);

            Menuimg menuimg = new Menuimg();
            menuimg.setFood_name(menuName); // 각 메뉴의 이름 설정
            menuimg.setFood_price(menuPrice); // 각 메뉴의 가격 설정
            menuimg.setFood_img(imagePath); // 각 메뉴의 이미지 경로 설정
            ls.add(menuimg);
        }
        return ls;
    }

    @FXML
    private List<Menuimg> getBeverageimgs() throws SQLException {
        List<Menuimg> ls = new ArrayList<>();
        CafemenuGetimageService service = new CafemenuGetimageService();
        List<String> imagePaths = service.getBeverageImage(); // 이미지 경로들을 가져옴
        List<String> namePaths = service.getBeverageName(); // 음식 이름 경로들을 가져옴
        List<Integer> pricePaths = service.getBeveragePrice(); // 음식 가격 경로들을 가져옴

        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            String menuName = namePaths.get(i); // 인덱스를 활용하여 각 메뉴에 해당하는 이름 가져오기
            int menuPrice = pricePaths.get(i);

            Menuimg menuimg = new Menuimg();
            menuimg.setFood_name(menuName); // 각 메뉴의 이름 설정
            menuimg.setFood_price(menuPrice); // 각 메뉴의 가격 설정
            menuimg.setFood_img(imagePath); // 각 메뉴의 이미지 경로 설정
            ls.add(menuimg);
        }
        return ls;
    }

    @FXML
    private List<Menuimg> getSmoothieimgs() throws SQLException {
        List<Menuimg> ls = new ArrayList<>();
        CafemenuGetimageService service = new CafemenuGetimageService();
        List<String> imagePaths = service.getSmoothieImage(); // 이미지 경로들을 가져옴
        List<String> namePaths = service.getSmoothieName(); // 음식 이름 경로들을 가져옴
        List<Integer> pricePaths = service.getSmoothiePrice(); // 음식 가격 경로들을 가져옴

        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            String menuName = namePaths.get(i); // 인덱스를 활용하여 각 메뉴에 해당하는 이름 가져오기
            int menuPrice = pricePaths.get(i);

            Menuimg menuimg = new Menuimg();
            menuimg.setFood_name(menuName); // 각 메뉴의 이름 설정
            menuimg.setFood_price(menuPrice); // 각 메뉴의 가격 설정
            menuimg.setFood_img(imagePath); // 각 메뉴의 이미지 경로 설정
            ls.add(menuimg);
        }
        return ls;
    }

    @FXML
    private List<Menuimg> getTeaimgs() throws SQLException {
        List<Menuimg> ls = new ArrayList<>();
        CafemenuGetimageService service = new CafemenuGetimageService();
        List<String> imagePaths = service.getTeaImage(); // 이미지 경로들을 가져옴
        List<String> namePaths = service.getTeaName(); // 음식 이름 경로들을 가져옴
        List<Integer> pricePaths = service.getTeaPrice(); // 음식 가격 경로들을 가져옴

        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            String menuName = namePaths.get(i); // 인덱스를 활용하여 각 메뉴에 해당하는 이름 가져오기
            int menuPrice = pricePaths.get(i);

            Menuimg menuimg = new Menuimg();
            menuimg.setFood_name(menuName); // 각 메뉴의 이름 설정
            menuimg.setFood_price(menuPrice); // 각 메뉴의 가격 설정
            menuimg.setFood_img(imagePath); // 각 메뉴의 이미지 경로 설정
            ls.add(menuimg);
        }
        return ls;
    }

    @FXML
    private List<Menuimg> getDessertimgs() throws SQLException {
        List<Menuimg> ls = new ArrayList<>();
        CafemenuGetimageService service = new CafemenuGetimageService();
        List<String> imagePaths = service.getDessertImage(); // 이미지 경로들을 가져옴
        List<String> namePaths = service.getDessertName(); // 음식 이름 경로들을 가져옴
        List<Integer> pricePaths = service.getDessertPrice(); // 음식 가격 경로들을 가져옴

        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            String menuName = namePaths.get(i); // 인덱스를 활용하여 각 메뉴에 해당하는 이름 가져오기
            int menuPrice = pricePaths.get(i);

            Menuimg menuimg = new Menuimg();
            menuimg.setFood_name(menuName); // 각 메뉴의 이름 설정
            menuimg.setFood_price(menuPrice); // 각 메뉴의 가격 설정
            menuimg.setFood_img(imagePath); // 각 메뉴의 이미지 경로 설정
            ls.add(menuimg);
        }
        return ls;
    }
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

    // cafemenu -> option
//    public void goOption() throws Exception {
//        try {
//            Parent home = FXMLLoader.load(getClass().getResource("/coffee/kiosk/option.fxml"));
//            Scene scene = new Scene(home, 500, 900);
//
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.show();
//
//            Stage root = (Stage) logo.getScene().getWindow();
//            root.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}