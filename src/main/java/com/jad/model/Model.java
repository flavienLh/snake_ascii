package com.jad.model;

import com.jad.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Model implements IModel {
    private static final Random random = new Random();
    private static Snake playerSnake;
    private static Snake aiSnake;
    private static ArrayList<Apple> apples = new ArrayList<Apple>();
    private static IController controller;
    private boolean win = false;

    public static void addNewApples() {
        for (int i = 0; i < random.nextInt(6) + 1; i++) {
            apples.add(new Apple(new Point(random.nextInt(GameboardUtils.GAME_WIDTH), random.nextInt(GameboardUtils.GAME_HEIGHT)), random.nextInt(9) + 1));
        }
    }

    public static void tick(int t) {
        if (playerSnake.selfHit() || playerSnake.hitOtherSnake(aiSnake) || playerSnake.getLength() <= 0 || aiSnake.getLength() >= 100) {
            controller.gameOver();
        }
        if (playerSnake.getLength() >= 100 || aiSnake.getLength() <= 0) {
            controller.gameWin();
        }
        for (int i = 0; i < apples.size(); i++) {
            if (playerSnake.getLength() > 0 && playerSnake.getHead().equals(apples.get(i).getPosition())) {
                if (!apples.get(i).isPoisonous()) {
                    playerSnake.grow(apples.get(i).getValue());
                } else {
                    playerSnake.shrink(apples.get(i).getValue());
                }
                apples.remove(i);
            }
        }
        for (int i = 0; i < apples.size(); i++) {
            if (aiSnake.getLength() > 0 && aiSnake.getHead().equals(apples.get(i).getPosition())) {
                if (!apples.get(i).isPoisonous()) {
                    aiSnake.grow(apples.get(i).getValue());
                } else {
                    aiSnake.shrink(apples.get(i).getValue());
                }
                apples.remove(i);
            }
        }
        if (t == 50) {
            addNewApples();
        }
        moveAiSnake();
    }

    public static void moveAiSnake() {
        Point nearestApple = getNearestApple();
        aiSnakeBestMove(nearestApple);
        snakeInFront();
        OwnBodyInFront();
    }

    private static void OwnBodyInFront() {
        switch (aiSnake.getDirection()) {
            case UP -> {
                if (aiSnake.contains(new Point(aiSnake.getHead().x, aiSnake.getHead().y + 1))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                }
            }
            case DOWN -> {
                if (aiSnake.contains(new Point(aiSnake.getHead().x, aiSnake.getHead().y - 1))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                }
            }
            case RIGHT -> {
                if (aiSnake.contains(new Point(aiSnake.getHead().x + 1, aiSnake.getHead().y))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                }
            }
            case LEFT -> {
                if (aiSnake.contains(new Point(aiSnake.getHead().x - 1, aiSnake.getHead().y))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                }
            }
        }
    }

    private static boolean snakeInFront() {
        boolean evaded = false;
        switch (aiSnake.getDirection()) {
            case UP -> {
                if (playerSnake.contains(new Point(aiSnake.getHead().x, aiSnake.getHead().y + 1))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                    evaded = true;
                }
            }
            case DOWN -> {
                if (playerSnake.contains(new Point(aiSnake.getHead().x, aiSnake.getHead().y - 1))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                    evaded = true;
                }
            }
            case RIGHT -> {
                if (playerSnake.contains(new Point(aiSnake.getHead().x + 1, aiSnake.getHead().y))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                    evaded = true;
                }
            }
            case LEFT -> {
                if (playerSnake.contains(new Point(aiSnake.getHead().x - 1, aiSnake.getHead().y))) {
                    if (random.nextInt() > 0.5) {
                        followInstruction(1);
                    } else {
                        followInstruction(-1);
                    }
                    evaded = true;
                }
            }
        }
        return !evaded ? false : snakeInFront();
    }

    public static Point getNearestApple() {
        Point snakeHead = aiSnake.getHead();
        Apple nearestApple = null;
        int DxNearestApple = 999;
        int DyNearestApple = 999;
        for (Apple apple : apples) {
            if (!apple.isPoisonous() && (DxNearestApple + DyNearestApple) > (Math.abs(snakeHead.x - apple.getPosition().x) + Math.abs(snakeHead.y - apple.getPosition().y))) {
                DxNearestApple = Math.abs(snakeHead.x - apple.getPosition().x);
                DyNearestApple = Math.abs(snakeHead.y - apple.getPosition().y);
                nearestApple = apple;
            }
        }
        return nearestApple == null ? null : nearestApple.getPosition();
    }

    public static void aiSnakeBestMove(Point nearestApple) {
        if (nearestApple != null) {
            int Dx = Math.abs(aiSnake.getHeadPosition().x) - Math.abs(nearestApple.x);
            int Dy = Math.abs(aiSnake.getHeadPosition().y) - Math.abs(nearestApple.y);
            boolean doY = false;
            if (Math.abs(Dx) >= Math.abs(Dy)) {
                if (Dx > 0) {
                    if (aiSnake.getDirection() == Direction.DOWN) {
                        followInstruction(1);
                    } else if (aiSnake.getDirection() == Direction.UP) {
                        followInstruction(-1);
                    } else {
                        doY = true;
                    }
                } else if (Dx < 0) {
                    if (aiSnake.getDirection() == Direction.DOWN) {
                        followInstruction(-1);
                    } else if (aiSnake.getDirection() == Direction.UP) {
                        followInstruction(1);
                    } else {
                        doY = true;
                    }
                } else {
                    doY = true;
                }
            }
            if (doY || Dx >= Dy) {
                if (Dy > 0) {
                    if (aiSnake.getDirection() == Direction.LEFT) {
                        followInstruction(1);
                    }
                    if (aiSnake.getDirection() == Direction.RIGHT) {
                        followInstruction(-1);
                    }
                } else if (Dy < 0) {
                    if (aiSnake.getDirection() == Direction.LEFT) {
                        followInstruction(-1);
                    }
                    if (aiSnake.getDirection() == Direction.RIGHT) {
                        followInstruction(1);
                    }
                }
            }
        }
    }

    public static void followInstruction(int instruction) {
        switch (instruction) {
            case -1 -> {
                switch (aiSnake.getDirection()) {
                    case UP -> aiSnake.changeDirection(Direction.LEFT);
                    case DOWN -> aiSnake.changeDirection(Direction.RIGHT);
                    case LEFT -> aiSnake.changeDirection(Direction.DOWN);
                    case RIGHT -> aiSnake.changeDirection(Direction.UP);
                }
            }
            case 1 -> {
                switch (aiSnake.getDirection()) {
                    case UP -> aiSnake.changeDirection(Direction.RIGHT);
                    case DOWN -> aiSnake.changeDirection(Direction.LEFT);
                    case LEFT -> aiSnake.changeDirection(Direction.UP);
                    case RIGHT -> aiSnake.changeDirection(Direction.DOWN);
                }
            }
        }
    }

    public void start() {
        playerSnake = new Snake(new Point(GameboardUtils.GAME_WIDTH / 2 - 10, GameboardUtils.GAME_HEIGHT / 2), 3, Direction.DOWN);
        aiSnake = new Snake(new Point(GameboardUtils.GAME_WIDTH / 2 + 10, GameboardUtils.GAME_HEIGHT / 2), 3, Direction.UP);
        this.addNewApples();
    }

    private void updatePlayerSnakePosition() {
        playerSnake.updateSnakePosition();
    }

    public void updateAiSnakePosition() {
        aiSnake.updateSnakePosition();
    }

    public void updateGame(int t) {
        updatePlayerSnakePosition();
        updateAiSnakePosition();
        tick(t);
    }

    public Snake getPlayerSnake() {
        return this.playerSnake;
    }

    public Snake getAiSnake() {
        return this.aiSnake;
    }

    public ArrayList<Apple> getApples() {
        return this.apples;
    }

    public void changePlayerDirection(Direction direction) {
        playerSnake.changeDirection(direction);
    }

    @Override
    public void setController(final IController controller) {
        this.controller = controller;
    }

    public boolean positionApples(Point point) {
        for (Apple apple : apples) {
            if (apple.getPosition().equals(point)) {
                return true;
            }
        }
        /*while (!AppleInPosition && i < apples.size()){
            AppleInPosition = apples.get(i).getPosition().equals(point);
            i++;
        }*/
        return false;
    }

    @Override
    public Apple getAppleAtPos(Point point) {
        int i = 0;
        boolean AiP = false;
        while (!AiP && i < apples.size()) {
            AiP = apples.get(i).getPosition().equals(point);
            i++;
        }
        return apples.get(i - 1);
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLost() {
        return playerSnake.getLength() <= 0 || aiSnake.getLength() >= 100;
    }


    public void resetGame() {
        apples.clear();

        playerSnake = new Snake(new Point(GameboardUtils.GAME_WIDTH / 2, GameboardUtils.GAME_HEIGHT / 2), 3, Direction.RIGHT);
        aiSnake = new Snake(new Point(GameboardUtils.GAME_WIDTH / 2 + 10, GameboardUtils.GAME_HEIGHT / 2), 3, Direction.LEFT);

        addNewApples();
    }
}
