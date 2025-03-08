module com.example.demo101 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo101 to javafx.fxml;
    exports com.example.demo101;
}