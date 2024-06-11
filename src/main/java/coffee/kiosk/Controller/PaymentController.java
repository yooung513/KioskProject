package coffee.kiosk.Controller;

import coffee.kiosk.model.Orderlist;
import coffee.kiosk.model.TotalOrderAmount;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class PaymentController implements Initializable {
    public Button cardbutton;
    public Button cashbutton;
    private TotalOrderAmount totalOrderAmount = null;
    private CafemenuController cafemenuController = null;

    public PaymentController (TotalOrderAmount totalOrderAmount , CafemenuController cafemenuController ){
        this.totalOrderAmount = totalOrderAmount;
        this.cafemenuController = cafemenuController;
    }

    public PaymentController(){
        this.totalOrderAmount = new TotalOrderAmount();
        this.cafemenuController = new CafemenuController();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PaymentController paymentController = new PaymentController(totalOrderAmount , this.cafemenuController);
        cardbutton.setOnMouseClicked(event ->{
            showAlert("카드 결제가 완료되었습니다.");
            TotalOrderAmount.getInstance().clearAmount();
            Orderlist.getInstance().clearOrderItems();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
                Parent home = loader.load();

                Scene scene = new Scene(home, 500, 900);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                Stage root = (Stage) cardbutton.getScene().getWindow();
                root.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cashbutton.setOnMouseClicked(event ->{
            showAlert("현금 결제가 완료되었습니다.");
            TotalOrderAmount.getInstance().clearAmount();
            Orderlist.getInstance().clearOrderItems();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/coffee/kiosk/cafemenu.fxml"));
                Parent home = loader.load();

                Scene scene = new Scene(home, 500, 900);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                Stage root = (Stage) cardbutton.getScene().getWindow();
                root.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void showAlert (String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("알림");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
