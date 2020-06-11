package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Eagle;
import com.codecool.snake.entities.enemies.Lion;
import com.codecool.snake.entities.powerups.HurryPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SmallPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class GameLoop {
    private Snake snake;
    private boolean running = false;


    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.step();

            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
        }

        Globals.getInstance().display.frameFinished();
    }


    private static void randomSpawnPowerUps(){
        Random r = new Random();
        int power = r.nextInt(600)+1;
        if(power==1) new SimplePowerUp();
        else if(power==2) new SmallPowerUp();
        else if(power==3) new HurryPowerUp();
    }

    private static void randomEagles() {
        Random r = new Random();
        int power = r.nextInt(200)+1;
        if(power==1) new Eagle();
    }

    private static void randomLions() {
        Random r = new Random();
        int power = r.nextInt(800)+1;
        if(power==1) new Lion();
    }


    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        randomSpawnPowerUps();
        randomEagles();
        randomLions();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
