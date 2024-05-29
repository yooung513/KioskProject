module coffee.kiosk {
    requires javafx.controls;
    requires javafx.fxml;


    opens coffee.kiosk to javafx.fxml;
    exports coffee.kiosk;
}