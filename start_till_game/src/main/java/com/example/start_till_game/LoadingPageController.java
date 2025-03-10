package com.example.start_till_game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingPageController implements Initializable {

    @FXML
    private ImageView loadingAnimation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Pause for 1 second before transitioning to the next scene
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/start_till_game/spaceLogin.fxml"));
                Stage stage = (Stage) loadingAnimation.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}