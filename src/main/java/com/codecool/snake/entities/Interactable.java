package com.codecool.snake.entities;


import com.codecool.snake.entities.enemies.Enemy;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(GameEntity entity);

    String getMessage();

}
