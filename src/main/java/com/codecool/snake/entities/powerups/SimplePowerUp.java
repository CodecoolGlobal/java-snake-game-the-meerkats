package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        double x = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double y = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        setImage(Globals.getInstance().getImage("PowerUpBerry"));
        setX(x);
        setY(y);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead || entity instanceof SnakeBody){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
