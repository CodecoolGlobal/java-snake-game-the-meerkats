package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;

import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import java.util.Random;

public class Lion extends Enemy implements Interactable, Animatable {


    public Lion() {
        super(20);

        setImage(Globals.getInstance().getImage("Lion"));
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
