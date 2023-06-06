package com.jad.model;

import com.jad.*;

import java.awt.*;
import java.util.Random;

public class Model implements IModel {
    private IController controller;
    private static Snake playerSnake;
    private static Snake aiSnake;
    private static Apple apple;
    private static final Random random = new Random();

    public void start() {
        playerSnake = new Snake(new Point(Constants.GAME_WIDTH/2, Constants.GAME_HEIGHT/2), 3, Direction.RIGHT);
        aiSnake = new Snake(new Point(Constants.GAME_WIDTH/2 + 10, Constants.GAME_HEIGHT/2), 3, Direction.LEFT);
        apple = new Apple(new Point(random.nextInt(Constants.GAME_WIDTH), random.nextInt(Constants.GAME_HEIGHT)), random.nextInt(9) + 1);
    }

    public Snake getPlayerSnake() {
        return this.playerSnake;
    }

    public Snake getAiSnake() {
        return this.aiSnake;
    }

    public Apple getApple() {
        return this.apple;
    }

    @Override
    public IModel getgameState() {
        return null;
    }

    @Override
    public IModel getGameState() {
        return this;
    }


    public static void tick() {

        // Check for collisions with the apple
        if (playerSnake.getHead().equals(apple.getPosition())) {
            playerSnake.grow(apple.getValue());
            apple = null;
        }
        if (aiSnake.getHead().equals(apple.getPosition())) {
            aiSnake.grow(apple.getValue());
            apple = null;
        }


        if (apple == null) {
            Point position = new Point(random.nextInt(Constants.GAME_WIDTH), random.nextInt(Constants.GAME_HEIGHT));
            int value = random.nextInt(9) + 1;  // recrée ce bout de script avec des nombres negatif pour la pomme empoisonnée
            apple = new Apple(position, value);
        }
    }

    public void changePlayerDirection(Direction direction) {
        playerSnake.changeDirection(direction);
    }
    @Override
    public void setController(final IController controller) {
        this.controller = controller;
    }

}
