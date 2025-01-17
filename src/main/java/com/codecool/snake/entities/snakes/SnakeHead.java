package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.HurryPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import com.codecool.snake.entities.powerups.SmallPowerUp;
import com.codecool.snake.eventhandler.InputHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;
    int attackSpeed = 15;

    public SnakeHead(Snake snake, Point2D position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }


    public void updateRotation(SnakeControl turnDirection, float speed, int stepCounter) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }
        if (turnDirection.equals(SnakeControl.SHOOT)) {
            if (stepCounter % attackSpeed == 0){
                new Amo(this.getPosition(),snake);
            }
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
            Globals.getInstance().game.updateHealthLabel();
        }
        if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(2);
        }
        if(entity instanceof SmallPowerUp){
            System.out.println(getMessage());
            snake.addPart(1);
        }
        if(entity instanceof HurryPowerUp){
            System.out.println(getMessage());
            snake.moveFaster((float) 0.1);
        }

    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
