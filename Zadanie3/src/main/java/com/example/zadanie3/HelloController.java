package com.example.zadanie3;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    protected Pane root;

    Ball ball = new Ball();

    @FXML
    protected void start() {
        root.getChildren().add(ball);
        ball.requestFocus();
    }
}
