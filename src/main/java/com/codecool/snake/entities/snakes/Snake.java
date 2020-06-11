package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {

    private static float speed = 2;
    private int stepCounter=0;
    private static int health = 100;


    private int snakeNo;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Point2D position , int snakeNo) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        this.snakeNo = snakeNo;

        addPart(4);
    }


    public SnakeHead getHead(){
        return head;
    }


    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, getSpeed(),stepCounter);


        updateSnakeBodyHistory();
//        checkForGameOverConditions();


        body.doPendingModifications();
        stepCounter++;
    }

    public static float getSpeed() {
        return speed;
    }

    public void moveFaster(float speed){
        this.speed +=speed;
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (snakeNo == 1) {
            if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
            if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
            if (InputHandler.getInstance().isKeyPressed(KeyCode.SPACE)) turnDir = SnakeControl.SHOOT;
        }
        if (snakeNo == 2) {
            if (InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
            if (InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;
            if (InputHandler.getInstance().isKeyPressed(KeyCode.V)) turnDir = SnakeControl.SHOOT;

        }
        return turnDir;

    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public static void changeHealth(int diff) {
        health += diff;
    }

    public boolean checkForGameOverConditions(int anotherSnakelenght) {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Globals.getInstance().display.showGameOverScreen(body.getList().size(), anotherSnakelenght);
            return true;
        }
        return false;
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    public int getBodySize(){
        return body.getList().size();
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
