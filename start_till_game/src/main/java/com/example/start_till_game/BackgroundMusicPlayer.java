package com.example.start_till_game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BackgroundMusicPlayer {
    private static MediaPlayer backgroundMusicPlayer;

    public static void playBackgroundMusic() {
        if (backgroundMusicPlayer == null) {
            String musicFile = "src/main/resources/sounds/game_background_music.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            backgroundMusicPlayer = new MediaPlayer(sound);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        backgroundMusicPlayer.play();
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }
}
