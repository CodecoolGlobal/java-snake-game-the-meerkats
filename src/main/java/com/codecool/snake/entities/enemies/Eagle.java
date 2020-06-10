package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import java.util.Random;

public class Eagle extends Enemy implements Interactable, Animatable {


    private Point2D eagle;
    private static Random rnd = new Random();

    public Eagle() {
        super(20);

        setImage(Globals.getInstance().getImage("Eagle"));
        setX((rnd.nextDouble() * Globals.WINDOW_HEIGHT - 60) + 30);
        setY(0);

        double direction = (rnd.nextDouble() * 270 - 180) + 180;
        double speed = 3;
        eagle = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {

        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + eagle.getX());
        setY(getY() + eagle.getY());

    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeBody) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
