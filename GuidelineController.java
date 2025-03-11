package com.example.board;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GuidelineController {

    @FXML
    private ImageView imageView;

    @FXML
    private Button previousButton, nextButton;

    @FXML
    private Text menuText;

    @FXML
    private ImageView menuIcon;

    private int currentPage = 0;

    // Array to store the paths of images (8 pages in total)
    private final String[] imagePaths = {
            "1.jpg", "2.jpg", "3.jpg", "4.jpg",
            "5.jpg", "6.jpg", "7.jpg", "8.jpg"
    };

    // Handle Previous Button Click
    @FXML
    private void handlePrevious() {
        if (currentPage > 0) {
            currentPage--;
            updateImage();
            updateButtons();
        }
    }


    @FXML
    private void handleNext() {
        if (currentPage < imagePaths.length - 1) {
            currentPage++;
            updateImage();
            updateButtons();
        }
    }


    private void updateImage() {
        imageView.setImage(new Image(getClass().getResource("/com/example/guideline/" + imagePaths[currentPage]).toString()));

    }


    private void updateButtons() {
        previousButton.setDisable(currentPage == 0); // Disable Previous on the first page
        nextButton.setDisable(currentPage == imagePaths.length - 1); // Disable Next on the last page
    }


    @FXML
    private void handleMenuClick() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/board/home.fxml"));
            StackPane root = loader.load();
            Scene scene = new Scene(root);


            Stage stage = (Stage) menuIcon.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {

        menuIcon.setOnMouseClicked(event -> handleMenuClick());
        menuText.setOnMouseClicked(event -> handleMenuClick());

        updateImage();
        updateButtons();
    }
}
