package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {
    public static Random rnd=new Random();
    private final int damage;
    private Point2D heading;
    private double direction;
    private double speed;

    public Enemy(int damage) {
        this.damage=damage;
        setEnemyPosition();
    }

    public void updateDirection() {
    }

    public int getDamage() {
        return this.damage;
    }


    public double getDirection() {
        return direction;
    }


    public void setEnemyPosition() {
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    public void setInitialHeading(double direction, double speed) {
        this.direction=direction;
        this.speed=speed;
        this.heading=Utils.directionToVector(direction, speed);
    }


    public void step() {
        updateDirection();
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

}
