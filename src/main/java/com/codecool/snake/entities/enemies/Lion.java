package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.Amo;
import com.codecool.snake.entities.snakes.SnakeHead;

public class Lion extends Enemy implements Interactable, Animatable {


    public Lion() {
        super(20);

        setImage(Globals.getInstance().getImage("Lion"));
        setInitialHeading(rnd.nextDouble() * 360, 0.5);
    }

    @Override
    public void updateDirection() {
        setInitialHeading(this.getDirection() - 0.5, 0.5); ;
    }

    @Override
    public void setEnemyPosition() {
        setX((rnd.nextDouble() * Globals.WINDOW_WIDTH / 1.25) + Globals.WINDOW_WIDTH / 10);
        setY((rnd.nextDouble() * Globals.WINDOW_HEIGHT / 1.25) + Globals.WINDOW_WIDTH / 10);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeBody || entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
        if(entity instanceof Amo) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
