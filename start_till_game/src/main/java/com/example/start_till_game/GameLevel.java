package com.example.start_till_game;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLevel {

    public ImageView backgroundImage;
    public Label titleLabel;
    public HBox levelContainer;
    @FXML
    private ImageView imageLevel1, imageLevel2, imageLevel3, imageLevel4, imageLevel5, imageLevel6, imageLevel7, imageLevel8;

    @FXML
    private Path curvyLine;

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
        curvyLine.getElements().clear();
        double startX = 150.0, startY = 250.0;
        double controlX1 = 400.0, controlY1 = 100.0;
        double controlX2 = 600.0, controlY2 = 400.0;
        double endX = 650.0, endY = 250.0;

        MoveTo moveTo = new MoveTo(startX, startY);
        CubicCurveTo cubicCurveTo = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        curvyLine.getElements().addAll(moveTo, cubicCurveTo);

        curvyLine.setStroke(Color.WHITE);
        curvyLine.setStrokeWidth(2);
        curvyLine.getStrokeDashArray().addAll(10.0, 5.0);
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
}