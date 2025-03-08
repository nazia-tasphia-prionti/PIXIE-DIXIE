module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.menu to javafx.fxml;
    exports com.example.menu;
}