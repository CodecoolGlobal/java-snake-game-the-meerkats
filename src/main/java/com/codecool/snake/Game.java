package com.codecool.snake;

import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.enemies.Lion;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HurryPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SmallPowerUp;
import com.codecool.snake.entities.snakes.Amo;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;


public class Game extends Pane {
    private Snake snake=null;
    private GameTimer gameTimer=new GameTimer();
    private Label health;


    public Game() {
        Globals.getInstance().game=this;
        Globals.getInstance().display=new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);

        spawnPowerUps(4);
        gameRestart();
        showHealth();

        GameLoop gameLoop=new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    private void gameRestart() {
        Button restartB=new Button("Restart");
        restartB.setLayoutX(5);
        restartB.setLayoutY(2);
        restartB.requestFocus();
        this.getChildren().add(restartB);
        restartB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                restart();
            }
        });

    }

    public void showHealth() {
        health = new Label("HEALTH:"+ Snake.getHealth());
        health.setTextFill(Color.web("#8b0000"));
        health.setLayoutX(5);
        health.setLayoutY(30);
        this.getChildren().add(health);
    }

    public void updateHealthLabel(){
        health.setText("HEALTH:"+ Snake.getHealth());
    }

    public void restart() {
        Globals.getInstance().stopGame();
        Globals.getInstance().display.clear();
        init();
        start();
    }

    public void start() {
        requestFocus();
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake=new Snake(new Point2D(500, 500));
    }

//    private  void spawnMuci(){
//        Amo muci =new Amo(new Point2D(300,300));
//    }

    private void spawnEnemies(int numberOfEnemies) {
        for (int i=0; i < numberOfEnemies; ++i) new SimpleEnemy();
        for (int i=0; i < numberOfEnemies / 4; ++i) new Lion();
        for (int i=0; i < numberOfEnemies; ++i) new Eagle();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for (int i=0; i < numberOfPowerUps; ++i) {
            new SimplePowerUp();
            new SmallPowerUp();
            new HurryPowerUp();
        }
    }


    private void setupInputHandling() {
        Scene scene=getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
