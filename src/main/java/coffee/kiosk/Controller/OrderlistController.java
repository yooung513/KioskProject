package coffee.kiosk.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderlistController implements Initializable {

    @FXML
    private VBox orderItemsContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void addOrderItem(String itemName , int quantity , int price){
        HBox orderItemBox = new HBox();
        orderItemBox.setSpacing(10);

        Label nameLabel = new Label(itemName);
        Label quantityLabel = new Label(String.valueOf(quantity));
        Label priceLabel = new Label(String.valueOf(price));

        orderItemBox.getChildren().addAll(nameLabel, quantityLabel, priceLabel);
        orderItemsContainer.getChildren().add(orderItemBox);
    }

}
