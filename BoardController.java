package com.example.board;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class BoardController {

    @FXML
    private ImageView learnButton, practiceButton, leaderboardButton, questsButton, shopButton, profileButton, moreButton;
    @FXML
    private ImageView homeButton, menuButton, guidebookButton;
    @FXML
    private Label scoreLabel;
    @FXML
    private BarChart<String, Number> progressBar;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Text learnText, practiceText, leaderboardText, questsText, shopText, profileText, moreText;

    private int readingScore = 0;
    private int listeningScore = 0;
    private int writingScore = 0;

    @FXML
    public void initialize() {
        // Setup hover effects for ImageViews with corresponding text
        setImageViewHoverEffect(learnButton, learnText);
        setImageViewHoverEffect(practiceButton, practiceText);
        setImageViewHoverEffect(leaderboardButton, leaderboardText);
        setImageViewHoverEffect(questsButton, questsText);
        setImageViewHoverEffect(shopButton, shopText);
        setImageViewHoverEffect(profileButton, profileText);
        setImageViewHoverEffect(moreButton, moreText);

        // Setup the progress bar chart
        setUpProgressBar();

        // Generate random scores for the skills
        generateRandomScores();

        // Set up the action for home, menu, and guidebook ImageViews
        homeButton.setOnMouseClicked(event -> loadScreen("home"));
        menuButton.setOnMouseClicked(event -> loadScreen("menuu"));

        // Open flipbook URL on guidebook button click
        guidebookButton.setOnMouseClicked(event -> openFlipBook());
    }

    private void setImageViewHoverEffect(ImageView imageView, Text buttonText) {
        imageView.setOnMouseEntered(event -> {
            imageView.setStyle("-fx-opacity: 0.7;");  // Darken the image on hover
            buttonText.setStyle("-fx-effect: dropshadow(gaussian, #FFFF00, 10, 0, 0, 0);");
        });

        imageView.setOnMouseExited(event -> {
            imageView.setStyle("-fx-opacity: 1.0;");  // Reset the image opacity
            buttonText.setStyle("-fx-effect: null;");  // Reset glow effect on text
        });

        imageView.setOnMousePressed(event -> imageView.setStyle("-fx-opacity: 0.5;"));
        imageView.setOnMouseReleased(event -> imageView.setStyle("-fx-opacity: 0.7;"));
    }

    private void setUpProgressBar() {
        // Set labels for the chart
        xAxis.setLabel("Skills");
        xAxis.setCategories(FXCollections.observableArrayList("Reading", "Listening", "Writing"));

        yAxis.setLabel("Score");


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Skills Progress");


        series.getData().add(new XYChart.Data<>("Reading", readingScore));
        series.getData().add(new XYChart.Data<>("Listening", listeningScore));
        series.getData().add(new XYChart.Data<>("Writing", writingScore));

        progressBar.getData().add(series);
    }

    private void generateRandomScores() {
        // Use Random to generate scores between 0 and 100 for each skill
        Random random = new Random();

        readingScore = random.nextInt(101); // Random value between 0 and 100
        listeningScore = random.nextInt(101); // Random value between 0 and 100
        writingScore = random.nextInt(101); // Random value between 0 and 100

        // Update the score label with the total score
        scoreLabel.setText("Total Score: " + (readingScore + listeningScore + writingScore));

        // Refresh the progress bar with the random scores
        progressBar.getData().clear();
        setUpProgressBar();
    }

    private void loadScreen(String screenName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/board/" + screenName + ".fxml"));
            AnchorPane pane = loader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(pane));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void openFlipBook() {
        try {

            String flipbookUrl = "https://heyzine.com/flip-book/505a7fa2d4.html#page/1";

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(flipbookUrl));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
