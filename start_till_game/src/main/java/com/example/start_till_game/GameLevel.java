package com.example.start_till_game;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class GameLevel {

    public ImageView backgroundImage;
    public Label titleLabel;
    public VBox levelContainer;
    @FXML
    private ImageView imageLevel1, imageLevel2, imageLevel3, imageLevel4, imageLevel5, imageLevel6, imageLevel7, imageLevel8;
    @FXML private ImageView goMenuButton;
    @FXML
    private Label lockedMessageLabel; // Label to show locked message

    private double spaceshipX = 150.0;
    private final int unlockedLevels = 1;  // Start with 2 levels unlocked

    @FXML
    public void initialize() {
        // Set up the levels
        setupLevelClickEvent(imageLevel1, 1);
        setupLevelClickEvent(imageLevel2, 2);
        setupLevelClickEvent(imageLevel3, 3);
        setupLevelClickEvent(imageLevel4, 4);
        setupLevelClickEvent(imageLevel5, 5);
        setupLevelClickEvent(imageLevel6, 6);
        setupLevelClickEvent(imageLevel7, 7);
        setupLevelClickEvent(imageLevel8, 8);

        // Initialize the curvy dotted line
        createCurvyDottedLine();

        // Add animations to the level images
        addHoverAnimation(imageLevel1);
        addHoverAnimation(imageLevel2);
        addHoverAnimation(imageLevel3);
        addHoverAnimation(imageLevel4);
        addHoverAnimation(imageLevel5);
        addHoverAnimation(imageLevel6);
        addHoverAnimation(imageLevel7);
        addHoverAnimation(imageLevel8);

        // Update the images' states based on unlocked levels
        updateImageStates();
        try {
            Image leftArrow = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/left_arrow.png")));
            goMenuButton.setImage(leftArrow);
        } catch (NullPointerException e) {
            System.out.println("Error: left_arrow.png file is missing!");
        }

        // Handle back button click
        goMenuButton.setOnMouseClicked(event -> goBack());
    }

    private void setupLevelClickEvent(ImageView levelImage, int levelNumber) {
        levelImage.setOnMouseClicked(event -> onLevelClick(event, levelNumber));
    }

    private void onLevelClick(MouseEvent event, int levelNumber) {
        if (levelNumber <= unlockedLevels) {
            System.out.println("You clicked on level " + levelNumber);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/start_till_game/game.fxml"));
                Stage stage = (Stage) imageLevel1.getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
            lockedMessageLabel.setVisible(false); // Hide the locked message if level is unlocked
        } else {
            System.out.println("Level " + levelNumber + " is locked.");
            lockedMessageLabel.setText("Level " + levelNumber + " is locked."); // Show the locked message
            lockedMessageLabel.setVisible(true); // Make the label visible
        }
    }

    private void createCurvyDottedLine() {
        // Implementation for creating curvy dotted line
    }

    private void addHoverAnimation(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);

        imageView.setOnMouseEntered(event -> scaleTransition.playFromStart());
        imageView.setOnMouseExited(event -> {
            scaleTransition.setRate(-1);
            scaleTransition.playFrom("end");
        });
    }

    private void updateImageStates() {
        ImageView[] levelImages = {imageLevel1, imageLevel2, imageLevel3, imageLevel4, imageLevel5, imageLevel6, imageLevel7, imageLevel8};
        for (int i = 0; i < levelImages.length; i++) {
            if (i < unlockedLevels) {
                levelImages[i].setOpacity(1.0);
            } else {
                levelImages[i].setOpacity(0.25);
            }
        }
    }

    public double getSpaceshipX() {
        return spaceshipX;
    }

    public void setSpaceshipX(double spaceshipX) {
        this.spaceshipX = spaceshipX;
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/start_till_game/menuu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Error: Unable to load menuu.fxml");
        }
    }
}