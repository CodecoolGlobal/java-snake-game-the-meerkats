package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;

public class Eagle extends Enemy implements Interactable, Animatable {


    public Eagle() {
        super(20);
        setImage(Globals.getInstance().getImage("Eagle"));
        setInitialHeading((rnd.nextDouble() * 80) + 140, 2.5);
    }

    @Override
    public void setEnemyPosition() {
        setX((rnd.nextDouble() * Globals.WINDOW_WIDTH - 100) + 50);
        setY(0);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeBody || entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
