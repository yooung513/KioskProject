package coffee.kiosk.Controller;

import coffee.kiosk.model.TotalOrderAmount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderlistController implements Initializable {


    @FXML
    public Label selected_price;
    @FXML
    public Button plus_button;
    @FXML
    public Label selected_count;
    @FXML
    public Button minus_button;
    @FXML
    public Label selected_foodname;

    @FXML
    private VBox orderItemsContainer;
    private int count;
    private int price;
    private TotalOrderAmount totalorder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalorder = TotalOrderAmount.getInstance();
    }

    public void setData(String foodname, int count , int foodprice){
        this.selected_foodname.setText(foodname);
        this.count = count;
        this.selected_count.setText(String.valueOf(count));
        this.price = foodprice;
        this.selected_price.setText(String.valueOf(foodprice + totalorder.getOptionamount()));

        plus_button.setOnAction(event -> {
            this.count++;
            this.selected_count.setText(String.valueOf(this.count));
            this.selected_price.setText(String.valueOf((this.price+totalorder.getOptionamount()) * this.count));
            totalorder.addSelectedOrderAmount(this.price);
            totalorder.setTotalOrderAmount(totalorder.getSelectedOrderAmount());
            System.out.println("selectecdOrder PLUS : " + totalorder.getSelectedOrderAmount());
        });

        minus_button.setOnAction(event -> {
            if (this.count > 1) {
                this.count--;
                this.selected_count.setText(String.valueOf(this.count));
                this.selected_price.setText(String.valueOf(this.price * this.count));
                totalorder.addSelectedOrderAmount(-this.price);
                totalorder.setTotalOrderAmount(totalorder.getSelectedOrderAmount());
                System.out.println("selectecdOrder MINUS : " + totalorder.getSelectedOrderAmount());
            }
        });
    }
}
