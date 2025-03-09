import com.microsoft.cognitiveservices.speech.*;

import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SpeechHelper {
    private static final String AZURE_SUBSCRIPTION_KEY = System.getenv("AZURE_SUBSCRIPTION_KEY");
    private static final String AZURE_SERVICE_REGION = System.getenv("AZURE_SERVICE_REGION");

    private SpeechSynthesizer synthesizer;

    // Word list with levels
    private static final Map<String, String[]> wordLevels = new LinkedHashMap<>();

    static {
        wordLevels.put("Level 1 (3-Letter Words)", new String[]{"Cat", "Dog", "Bat", "Rat", "Hen"});
        wordLevels.put("Level 2 (4-Letter Words)", new String[]{"Rose", "Fish", "Frog", "Duck", "Tree"});
        wordLevels.put("Level 3 (5-Letter Words)", new String[]{"Earth", "Truck", "Grape", "Peach", "Clock"});
        wordLevels.put("Level 4 (6-Letter Words)", new String[]{"Monkey", "Banana", "Candle", "Hammer", "Rabbit"});
        wordLevels.put("Level 5 (7-Letter Words)", new String[]{"Avocado", "Balloon", "Giraffe", "Rainbow", "Bicycle"});
    }

    public SpeechHelper() {
        if (AZURE_SUBSCRIPTION_KEY == null || AZURE_SERVICE_REGION == null) {
            throw new RuntimeException("Azure keys are not set. Please configure environment variables.");
        }
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(AZURE_SUBSCRIPTION_KEY, AZURE_SERVICE_REGION);
        this.synthesizer = new SpeechSynthesizer(speechConfig);
    }

    public void speak(String text) {
        try {
            synthesizer.SpeakTextAsync(text).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // Iterate through each level
        for (Map.Entry<String, String[]> entry : wordLevels.entrySet()) {
            String levelName = entry.getKey();
            String[] words = entry.getValue();
            boolean levelCompleted = false;

            // Continue to next level if the current one is completed
            while (!levelCompleted) {
                System.out.println("🔹 Starting " + levelName);
                speak("Starting " + levelName);

                boolean allCorrect = true;

                // Iterate through words in the current level
                for (String word : words) {
                    String prompt = "This is a " + word + ". Could you write the correct spelling of it?";
                    System.out.println(prompt);
                    speak(prompt);

                    // Get user input
                    System.out.print("Your answer: ");
                    String userInput = scanner.nextLine().trim();

                    // Check if the spelling is correct
                    if (word.equalsIgnoreCase(userInput)) {
                        speak("Correct!");
                        System.out.println("✅ Correct!");
                    } else {
                        speak("Wrong. Please try again.");
                        System.out.println("❌ Wrong. Please try again.");
                        allCorrect = false;
                    }
                }

                // If all words are correct, proceed to the next level
                if (allCorrect) {
                    speak("Level completed! Moving to the next level.");
                    System.out.println("🎉 Level completed! Moving to the next level.");
                    levelCompleted = true;
                }
            }
        }

        // End of game
        System.out.println("🎉 Game Over! You have completed all levels.");
        speak("Game Over! You have completed all levels.");
        scanner.close();
    }

    public static void main(String[] args) {
        SpeechHelper game = new SpeechHelper();
        game.startGame();
    }
}


