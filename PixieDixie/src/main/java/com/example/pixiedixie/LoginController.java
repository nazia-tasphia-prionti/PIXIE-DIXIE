package com.example.pixiedixie;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    public Hyperlink SignupLink;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView ticketBigImageView;

    @FXML
    private ImageView ticketSmallerOneImageView;

    @FXML
    private void initialize() {
        errorLabel.setVisible(false); // Hide initially
        errorLabel.toFront();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Reset error label
        errorLabel.setVisible(false);
        errorLabel.setText("");

        if (username.isEmpty() || password.isEmpty()) {
            showError("All fields are required");
            return;
        }

        if (checkCredentials(username, password)) {
            showSuccess();
            animateSmallTicket(); // Animate ticket
            System.out.println("Login button clicked!");
            loadGameScene();
        } else {
            showError("Incorrect username or password");
        }
    }

    private boolean checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("signup_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String fileEmail = parts[0].split(": ")[1];
                    String fileUsername = parts[1].split(": ")[1];
                    String filePassword = parts[2].split(": ")[1];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            showError("Error reading signup_info file.");
        }
        return false;
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
        errorLabel.setVisible(true);
    }

    private void showSuccess() {
        errorLabel.setText("Login successful!");
        errorLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
        errorLabel.setVisible(true);
    }

    private void animateSmallTicket() {
        // Rotate animation
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), ticketSmallerOneImageView);
        rotateTransition.setByAngle(30);
        rotateTransition.setCycleCount(1);

        // Translate animation (move to right and slightly up)
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(3000), ticketSmallerOneImageView);
        translateTransition.setByX(950); // Move to right
        translateTransition.setByY(102); // Slightly move up
        translateTransition.setCycleCount(1);

        // Play both animations together
        rotateTransition.play();
        translateTransition.play();
    }

    private void loadGameScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game2.fxml"));
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLOGIN(MouseEvent mouseEvent) {

    }

    public void handleSignupLink(MouseEvent mouseEvent) {
        Stage stage = (Stage) SignupLink.getScene().getWindow();
        ScreenController screenController = new ScreenController(stage);
        screenController.changeScreen("/com/example/pixiedixie/signup.fxml");
    }
}    