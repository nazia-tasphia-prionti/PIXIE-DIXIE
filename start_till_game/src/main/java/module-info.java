module com.example.start_till_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.start_till_game to javafx.fxml;
    exports com.example.start_till_game;
}