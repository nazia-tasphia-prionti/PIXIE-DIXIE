module com.example.start_till_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.start_till_game to javafx.fxml;
    exports com.example.start_till_game;
}