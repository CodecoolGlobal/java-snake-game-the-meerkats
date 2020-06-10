package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();

    public Display(Pane pane) {
        displayPane = pane;
    }

    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);
    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
    public void showGameOverScreen(int snakeLength) {
        double width = Globals.WINDOW_WIDTH;
        double height = Globals.WINDOW_HEIGHT;

        Label gameOver = new Label("Game Over");
        gameOver.setMinWidth(width);
        gameOver.setFont(Font.font("Chilanka", FontWeight.BOLD,45));
        gameOver.setTextFill(Color.web("#8b0000"));
        gameOver.setAlignment(Pos.CENTER);
        gameOver.relocate(0,height / 2 - 30);

        Label length = new Label("Your snake's length: " + snakeLength);
        length.setMinWidth(width);
        length.setFont(Font.font("Chilanka", 25));
        length.setAlignment(Pos.CENTER);
        length.relocate(0, height / 2 + 30);

        //restart button for game over
        Button restart = new Button("Restart");
        getRestartButton(restart);

        clear();
        displayPane.getChildren().addAll(gameOver, length, restart);
    }

    public void getRestartButton(Button restart){
        restart.setOnAction((ActionEvent e) -> Globals.getInstance().game.restart());
        double width = Globals.WINDOW_WIDTH;
        double height = Globals.WINDOW_HEIGHT;
        restart.setMinWidth(50);
        restart.relocate((width / 2 - 0.5 * restart.getMinWidth()), height / 2 + 80);
        displayPane.getChildren().add(restart);
    }

}
