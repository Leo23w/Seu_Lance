module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Modal;
    opens Modal to javafx.fxml;
}