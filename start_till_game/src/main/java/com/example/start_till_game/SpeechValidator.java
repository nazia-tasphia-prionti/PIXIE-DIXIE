package com.example.start_till_game;

public class SpeechValidator {
//    private final SpeechHelper speechHelper;

    public SpeechValidator() {
//        speechHelper = new SpeechHelper();
    }

    public boolean validateSpelling(String userInput, String correctWord) {
        boolean isCorrect = userInput.equalsIgnoreCase(correctWord);
//        speechHelper.speak(isCorrect ? "Correct!" : "Wrong! Try again.");
        return isCorrect;
    }
}
