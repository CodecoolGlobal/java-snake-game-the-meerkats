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
        setFinalHeading(rnd.nextDouble() * 360, 1);
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
