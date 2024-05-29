module coffee.kiosk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens coffee.kiosk to javafx.fxml;
    exports coffee.kiosk;
}