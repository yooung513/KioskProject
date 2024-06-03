module kiosk.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens coffee.kiosk to javafx.fxml;
    exports coffee.kiosk;
    opens coffee.kiosk.Controller to javafx.fxml; // 추가된 부분

}
