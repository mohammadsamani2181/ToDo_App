package com.example.todo_app;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MakeInvisible {

    public static void start (Label label) {
        PauseTransition visiblePause1 = new PauseTransition(
                Duration.seconds(7)
        );
        visiblePause1.setOnFinished(
                event -> label.setVisible(false)
        );
        visiblePause1.play();



        PauseTransition visiblePause2 = new PauseTransition(
                Duration.seconds(7)
        );
        visiblePause2.setOnFinished(
                event -> label.setVisible(true)
        );
        visiblePause2.play();



        PauseTransition visiblePause3 = new PauseTransition(
                Duration.seconds(7)
        );
        visiblePause3.setOnFinished(
                event -> label.setText("")
        );
        visiblePause3.play();
    }
}
