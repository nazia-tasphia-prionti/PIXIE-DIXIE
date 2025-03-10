package com.example.start_till_game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GameController {
    @FXML private Label wordPrompt, feedbackLabel;
    @FXML private TextField userInput;
    @FXML private ImageView wordImage;
    @FXML private Button hintButton, musicButton;

    private int level = 1, wordIndex = 0, hintCount = 2;
    private boolean isMusicOn = true;
    private Map<Integer, Map<String, String>> levels;
    private SpeechValidator speechValidator;
    //private SpeechService speechService;

    public void initialize() {
        initializeLevels();
        speechValidator = new SpeechValidator();
        //speechService = new SpeechService();
        loadNextWord();
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
            wordPrompt.setText("🎉 Game Completed! 🎉");
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
        //speechService.speak("This is a " + word + ". Could you spell it correctly?");
    }

    @FXML
    private void checkSpelling() {
        String userInputText = userInput.getText().trim();
        String correctWord = getCurrentWord();
        boolean isCorrect = speechValidator.validateSpelling(userInputText, correctWord);
        feedbackLabel.setText(isCorrect ? "✅ Correct!" : "❌ Wrong!");
        //speechService.speak(isCorrect ? "Correct!" : "Wrong! Please try again.");
        if (isCorrect) {
            userInput.clear();
            wordIndex++;
            loadNextWord();
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
        musicButton.setText(isMusicOn ? "Music On" : "Music Off");
    }

    private String getCurrentWord() {
        Map<String, String> currentLevel = levels.get(level);
        return (String) currentLevel.keySet().toArray()[wordIndex];
    }
}