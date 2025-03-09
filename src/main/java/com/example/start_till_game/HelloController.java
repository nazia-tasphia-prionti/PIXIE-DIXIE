package com.example.start_till_game;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {



    @FXML
    private ImageView animationImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), animationImageView);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
    }

    @FXML
    private void onAnimationImageViewClicked() {
        try {
            Parent loadingPage = FXMLLoader.load(getClass().getResource("/com/example/start_till_game/loadingpage.fxml"));
            Stage stage = (Stage) animationImageView.getScene().getWindow();
            stage.setScene(new Scene(loadingPage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSignInButtonClicked() {
        System.out.println("Sign in as Neurologist button clicked!");
    }

}