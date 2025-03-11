package com.example.board;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class TranscriptController {

    @FXML
    private ImageView leftArrow;
    @FXML
    private ImageView rightArrow;
    @FXML
    private ImageView bar1, bar2, bar3, bar4, bar5, bar6;

    // Variable to keep track of the current slide
    private int currentSlide = 0;
    private MediaPlayer mediaPlayer;


    public void initialize() {
        // Debugging: Check if leftArrow is null
        if (leftArrow != null) {
            leftArrow.setOnMouseClicked(event -> goToPreviousSlide());
        } else {
            System.out.println("Left Arrow is null!");
        }

        // Handling hover effects for bars
        handleBarHoverEvents();

        // Add click event listeners for each bar to play audio
        bar3.setOnMouseClicked(event -> playAudio("audio1.mp3"));
        bar1.setOnMouseClicked(event -> playAudio("audio2.mp3"));
        bar2.setOnMouseClicked(event -> playAudio("audio3.mp3"));
        bar6.setOnMouseClicked(event -> playAudio("audio4.mp3"));
        bar4.setOnMouseClicked(event -> playAudio("audio5.mp3"));
        bar5.setOnMouseClicked(event -> playAudio("audio6.mp3"));
    }
    // Method to play audio for each bar
    private void playAudio(String audioFilePath) {
        try {
            // Stop the previous audio if any is playing
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.stop();  // Stop the current audio if it's playing
            }

            // Load the audio file from resources
            // Get the full path to the audio file from resources folder
            Media media = new Media(getClass().getResource("/com/example/board/" + audioFilePath).toExternalForm());

            // Create a MediaPlayer instance to play the audio
            mediaPlayer = new MediaPlayer(media);

            // Start playing the audio
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();  // Handle the exception if the audio file fails to load
        }
    }

    // Handle hover events for the bars
    private void handleBarHoverEvents() {
        bar1.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleBarHover(bar1, true));
        bar1.addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleBarHover(bar1, false));
        bar2.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleBarHover(bar2, true));
        bar2.addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleBarHover(bar2, false));
        bar3.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleBarHover(bar3, true));
        bar3.addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleBarHover(bar3, false));
        bar4.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleBarHover(bar4, true));
        bar4.addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleBarHover(bar4, false));
        bar5.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleBarHover(bar5, true));
        bar5.addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleBarHover(bar5, false));
        bar6.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleBarHover(bar6, true));
        bar6.addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleBarHover(bar6, false));
    }

    // Method to handle hover effect on bars (change opacity)
    private void handleBarHover(ImageView bar, boolean hover) {
        if (hover) {
            bar.setStyle("-fx-opacity: 0.8;");  // On hover: change opacity
        } else {
            bar.setStyle("-fx-opacity: 1.0;");  // Reset opacity
        }
    }

    // Handle previous slide click (left arrow)
    private void goToPreviousSlide() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/board/level.fxml"));
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) leftArrow.getScene().getWindow();  // Get the current window
            Scene gameScene = new Scene(gameRoot);  // Create a new scene
            stage.setScene(gameScene);  // Set the new scene on the stage
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception if the FXML file loading fails
        }
    }

    }

    // Handle next slide click (right arrow)


    // Load a new scene (e.g., 'level.fxml')
