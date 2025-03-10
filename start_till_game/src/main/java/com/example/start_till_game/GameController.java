package com.example.start_till_game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GameController {
    @FXML private Label wordPrompt, feedbackLabel, starCountLabel;
    @FXML private TextField userInput;
    @FXML private ImageView wordImage, starImageView, backButton;
    @FXML private Button hintButton, musicButton;

    private int level = 1, wordIndex = 0, hintCount = 2;
    private int correctAnswers = 0; // Tracks correct word count
    private boolean isMusicOn = true;
    private Map<Integer, Map<String, String>> levels;
    private SpeechValidator speechValidator;
    private MediaPlayer wordAudioPlayer;

    public void initialize() {
        initializeLevels();
        speechValidator = new SpeechValidator();

        // Load the star image
        try {
            Image starImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/star.png")));
            starImageView.setImage(starImage);
        } catch (NullPointerException e) {
            System.out.println("Error: star.png file is missing!");
        }

        // Load the back button image
        try {
            Image leftArrow = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/left_arrow.png")));
            backButton.setImage(leftArrow);
        } catch (NullPointerException e) {
            System.out.println("Error: left_arrow.png file is missing!");
        }

        // Handle back button click
        backButton.setOnMouseClicked(event -> goBack());

        updateStarDisplay();
        loadNextWord();
        BackgroundMusicPlayer.playBackgroundMusic();
    }

    private void initializeLevels() {
        levels = new LinkedHashMap<>();

        levels.put(1, Map.of(
                "cat", "/images/cat.gif",
                "dog", "/images/dog.gif",
                "bat", "/images/bat.gif",
                "rat", "/images/rat.gif",
                "hen", "/images/hen.gif"));

        levels.put(2, Map.of(
                "rose", "/images/rose.gif",
                "fish", "/images/fish.gif",
                "frog", "/images/frog.gif",
                "duck", "/images/duck.gif",
                "tree", "/images/tree.gif"));

        levels.put(3, Map.of(
                "earth", "/images/earth.gif",
                "truck", "/images/truck.gif",
                "grape", "/images/grape.gif",
                "peach", "/images/peach.gif",
                "clock", "/images/clock.gif"));

        levels.put(4, Map.of(
                "monkey", "/images/monkey.gif",
                "banana", "/images/banana.gif",
                "candle", "/images/candle.gif",
                "hammer", "/images/hammer.gif",
                "rabbit", "/images/rabbit.gif"));

        levels.put(5, Map.of(
                "avocado", "/images/avocado.gif",
                "balloon", "/images/balloon.gif",
                "giraffe", "/images/giraffe.gif",
                "rainbow", "/images/rainbow.gif",
                "bicycle", "/images/bicycle.gif"));
    }

    private void loadNextWord() {
        if (level > 5) {
            wordPrompt.setText("�� Game Completed! 🎉");
            return;
        }

        Map<String, String> currentLevel = levels.get(level);
        if (wordIndex >= currentLevel.size()) {
            level++;
            wordIndex = 0;
            hintCount = 2;
            if (level > 5) {
                wordPrompt.setText("🎉 Game Completed! 🎉");
                return;
            }
            currentLevel = levels.get(level);
        }

        String word = (String) currentLevel.keySet().toArray()[wordIndex];
        wordPrompt.setText("Could you spell the word correctly?");
        wordImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentLevel.get(word)))));
        playWordAudio(word);
    }

    @FXML
    private void checkSpelling() {
        String userInputText = userInput.getText().trim();
        String correctWord = getCurrentWord();
        boolean isCorrect = speechValidator.validateSpelling(userInputText, correctWord);

        if (isCorrect) {
            feedbackLabel.setText("✅ Correct!");
            correctAnswers++; // Increase correct word count
            updateStarDisplay(); // Update star count display

            userInput.clear();
            wordIndex++;
            loadNextWord();
        } else {
            feedbackLabel.setText("❌ Wrong!");
        }
    }

    @FXML
    private void useHint() {
        if (hintCount > 0) {
            hintCount--;
            String correctWord = getCurrentWord();
            String hint = correctWord.substring(0, 2) + "...";
            feedbackLabel.setText("Hint: " + hint);
        } else {
            hintButton.setDisable(true);
            feedbackLabel.setText("No more hints!");
        }
    }

    @FXML
    private void toggleMusic() {
        isMusicOn = !isMusicOn;
        if (isMusicOn) {
            BackgroundMusicPlayer.playBackgroundMusic();
            musicButton.setText("Music On");
        } else {
            BackgroundMusicPlayer.stopBackgroundMusic();
            musicButton.setText("Music Off");
        }
    }

    // Update the star count label
    private void updateStarDisplay() {
        starCountLabel.setText(" " + correctAnswers);
    }

    // Go back to game_interface.fxml
    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/start_till_game/game_interface.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Error: Unable to load game_interface.fxml");
        }
    }

    private void playWordAudio(String word) {
        if (wordAudioPlayer != null) {
            wordAudioPlayer.stop();
        }
        String audioFile = "src/main/resources/sounds/" + word + ".mp3";
        Media sound = new Media(new File(audioFile).toURI().toString());
        wordAudioPlayer = new MediaPlayer(sound);
        wordAudioPlayer.play();
    }

    private String getCurrentWord() {
        Map<String, String> currentLevel = levels.get(level);
        return (String) currentLevel.keySet().toArray()[wordIndex];
    }
}