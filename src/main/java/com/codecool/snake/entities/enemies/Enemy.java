package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {
    private final int damage;
    private Point2D heading;
    public static Random rnd = new Random();
    private double direction;
    private int speed;

    public Enemy(int damage) {
        this.damage = damage;
        setEnemyPosition();
    }

    public Point2D getHeading(Point2D heading) {
        return this.heading;
    }

    private void setHeading(Point2D heading) {
        this.heading = heading;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getSpeed() {
        return this.speed;
    }

    public double getDirection() {
        return direction;
    }

    protected void setEnemyPosition() {
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    public void setFinalHeading(double direction, int speed) {
        this.direction = direction;
        this.speed = speed;
        this.heading =Utils.directionToVector(direction, speed);
    }


    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

}
