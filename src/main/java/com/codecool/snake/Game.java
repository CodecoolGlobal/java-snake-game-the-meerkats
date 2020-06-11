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

import com.sun.javafx.geom.Vec2d;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Game extends Pane {
    private Snake snake=null;
    private GameTimer gameTimer=new GameTimer();
    protected Label health;
    protected Label health2;
    private List<Snake> snakes = new ArrayList<>();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);

        spawnPowerUps(4);
        gameRestart();
        showHealth( 5, 30);
//        snakes.get(0).setHealth(100);
//        snakes.get(1).setHealth(100);

        GameLoop gameLoop = new GameLoop(snakes);
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

    public void showHealth(int healthX, int healthY) {

        health = new Label("HEALTH:"+ snakes.get(0).getHealth());
        health.setTextFill(Color.web("#8b0000"));
        health.setLayoutX(healthX);
        health.setLayoutY(healthY);
        this.getChildren().add(health);

        health2 = new Label("HEALTH2:"+ snakes.get(1).getHealth());
        health2.setTextFill(Color.web("#8b0000"));
        health2.setLayoutX(5);
        health2.setLayoutY(50);
        this.getChildren().add(health2);
    }

    public void updateHealthLabel(){
        health.setText("HEALTH:"+ snakes.get(0).getHealth());
        health2.setText("HEALTH2:"+ snakes.get(1).getHealth());
    }

    public void restart(){
        snakes.clear();
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
        snakes.add(new Snake(new Point2D(500  , 600), 1 ));
        snakes.add(new Snake(new Point2D(500  , 400), 2 ));

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
