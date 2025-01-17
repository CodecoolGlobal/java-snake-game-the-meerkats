package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.HurryPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SmallPowerUp;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Queue;



public class SnakeBody extends GameEntity implements Interactable{
    private Queue<Point2D> history = new LinkedList<>();
    private static final int historySize = 10;
    private Snake snake;

    public SnakeBody(Snake snake, Point2D coord) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeBody"));
        setX(coord.getX());
        setY(coord.getY());

        for (int i = 0; i < historySize; i++) {
            history.add(coord);
        }
    }


    @Override
    public void setPosition(Point2D pos) {
        Point2D currentPos = history.poll(); // remove the oldest item from the history
        setX(currentPos.getX());
        setY(currentPos.getY());
        history.add(pos); // add the parent's current position to the beginning of the history
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
            Globals.getInstance().game.updateHealthLabel();
        }

    }

    @Override
    public String getMessage() {
        return "I'm hurt!";
    }
}