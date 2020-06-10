package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import java.util.Random;

public class Lion extends Enemy implements Interactable, Animatable {


    private Point2D lion;
    private static Random rnd = new Random();

    public Lion() {
        super(20);

        setImage(Globals.getInstance().getImage("Lion"));
        setX(0);
        setY((rnd.nextDouble() * Globals.WINDOW_HEIGHT - 60) + 30);

        double direction = 90;

        double speed = 0.5;
        lion = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + lion.getX());
        setY(getY() + lion.getY());
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
