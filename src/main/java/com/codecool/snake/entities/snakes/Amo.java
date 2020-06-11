package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import javafx.geometry.Point2D;

public class Amo extends GameEntity implements Animatable, Interactable {
    private static float amoSpeed = 3;
    private Point2D heading;
    private Snake snake;

    public Amo(Point2D coord, Snake snake) {
        super();
        this.snake=snake;
        setImage(Globals.getInstance().getImage("Amo"));
        setX(coord.getX());
        setY(coord.getY());
        double direction = snake.getHead().getRotate();
        heading = Utils.directionToVector(direction, amoSpeed);
    }

    @Override
    public void setPosition(Point2D pos) {
        Point2D currentPos = pos;
        setX(currentPos.getX());
        setY(currentPos.getY());
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage(){
            return "woosh";
    }
}
