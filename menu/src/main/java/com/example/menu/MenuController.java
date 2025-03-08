package com.example.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class MenuController {

    public ImageView settingsIcon;
    public ImageView profileIcon;
    public ImageView leftArrowIcon;
    @FXML
    private Button playButton1;

    @FXML
    private Button playButton2;


    @FXML
    private void handlePlayButton1Click() {
        System.out.println("Play button 1 clicked!");
        loadGameScene1();
    }
    @FXML
    private void handleLeftArrowIcon() {
        System.out.println("left Arrow clicked!");
        loadGameScene3();
    }

    private void loadGameScene3() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game2.fxml"));//eikhane jeitar sathe connect korte chao oitar fxml file er naam ta diyo (same hocche sob gular jonno)
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) leftArrowIcon.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void handleSettingsIcon() {
        System.out.println("setting button clicked!");
        loadGameScene4();
    }

    private void loadGameScene4() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game2.fxml"));//eikhane jeitar sathe connect korte chao oitar fxml file er naam ta diyofxml
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) settingsIcon.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleProfileIcon() {
        System.out.println("profile button clicked!");
        loadGameScene5();
    }

    private void loadGameScene5() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game2.fxml"));//eikhane jeitar sathe connect korte chao oitar fxml file er naam ta diyofxml
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) profileIcon.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePlayButton2Click() {
        System.out.println("Play button 2 clicked!");
        loadGameScene2();
    }


    private void loadGameScene1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game1.fxml"));
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) playButton1.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGameScene2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game2.fxml"));
            AnchorPane gameRoot = loader.load();
            Stage stage = (Stage) playButton2.getScene().getWindow();
            Scene gameScene = new Scene(gameRoot);
            stage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
