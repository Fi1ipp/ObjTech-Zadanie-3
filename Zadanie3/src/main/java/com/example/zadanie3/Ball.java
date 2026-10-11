package com.example.zadanie3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Ball extends Canvas {
    private GraphicsContext gc;
    private Timeline t;

    private Color color = Color.GREEN;
    private double x = getWidth()/2+50;
    private double y = getHeight()/2-10;

    private boolean right;
    private boolean down;

    private int speed = 20;
    private double xSpeed = 1;
    private double ySpeed = 1;

    private boolean mode;

    public Ball() {
        super(500,500);
        gc = this.getGraphicsContext2D();
        setOnMouseClicked(e -> mode());

        requestFocus();
        setOnKeyPressed(e -> speed(e));
        setFocusTraversable(true);

        draw(color);

    }

    private void draw(Color c){
        gc.clearRect(0,0,getWidth(),getHeight());

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,getWidth(),getHeight());
        gc.setFill(c);
        gc.fillOval(x,y,50,50);
    }

    private void move() {

        if(x+50 >= getWidth()) {
            right=false;
            x=getWidth()-50;
            xSpeed = 1 - Math.random() + Math.random();
            ySpeed += xSpeed/2;
        } else if (x <= 0) {
            right=true;
            xSpeed = 1 - Math.random() + Math.random();
            ySpeed += xSpeed/2;
            x=0;
        }

        if(y+50 >= getHeight()) {
            down=false;
            y=getHeight()-50;
            ySpeed = 1 - Math.random() + Math.random();
            xSpeed += ySpeed/2;
        } else if (y <= 0) {
            down=true;
            ySpeed = 1 - Math.random() + Math.random();
            xSpeed += ySpeed/2;
            y=0;
        }

        if(right)x+=xSpeed;
        else x-=xSpeed;

        if(down)y+=ySpeed;
        else y-=ySpeed;

        draw(color);
    }

    private void mode() {
        if(mode){
            mode = false;
            if(t!=null)t.stop();
        } else {
            mode = true;
            t = new Timeline(new KeyFrame(Duration.millis(speed),e->move()));
            t.setCycleCount(Timeline.INDEFINITE);
            t.play();
        }
    }

    private void speed(KeyEvent key) {
        switch(key.getCode()) {
            case F:
                if(speed>1)speed-=1;
                else speed=1;
                break;
            case S:
                speed+=1;
        }

        if (mode) {
            if(t!=null)t.stop();
            t = new Timeline(new KeyFrame(Duration.millis(speed),e->move()));
            t.setCycleCount(Timeline.INDEFINITE);
            t.play();
        }
    }
}
