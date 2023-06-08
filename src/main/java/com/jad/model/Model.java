package com.jad.model;

import com.jad.*;
import com.jad.AStar.AStar;
import com.jad.AStar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model implements IModel {
    private static final Random random = new Random();
    private static Snake playerSnake;
    private static Snake aiSnake;
    private static ArrayList<Apple> apples = new ArrayList<Apple>();
    private static IController controller;
    private boolean win = false;

    public static void addNewApples() {
        for (int i = 0; i < random.nextInt(3) + 1; i++) {
            apples.add(new Apple(new Point(random.nextInt(Constants.GAME_WIDTH), random.nextInt(Constants.GAME_HEIGHT)), random.nextInt(9) + 1));
        }
    }

    public static void tick(int t) {
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
        if (playerSnake.selfHit()) {
            controller.gameOver();
        }
        if (playerSnake.hitOtherSnake(aiSnake) || aiSnake.hitOtherSnake(playerSnake)) {
            controller.gameOver();
        }
        if (t == 50) {
            addNewApples();
        }
        followInstruction();
    }

    public static void followInstruction() {
        switch (aiSnake.getNextInstruction()) {
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

    public void gameOver() {
        controller.gameOver();
    }

    public void start() {
        playerSnake = new Snake(new Point(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2), 3, Direction.RIGHT);
        aiSnake = new Snake(new Point(Constants.GAME_WIDTH / 2 + 10, Constants.GAME_HEIGHT / 2), 3, Direction.LEFT);
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

    @Override
    public IModel getGameState() {
        return this;
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

    public void generatePath(String gameBoard) {/*
        int[][] grid = this.generateGrid(gameBoard);
        Point nearestApplePosition = this.getNearestApple();
        grid[aiSnake.getHeadPosition().x][aiSnake.getHeadPosition().y] = 0;
        if (nearestApplePosition != null) {
            AStar aStar = new AStar(grid, aiSnake.getHead().x, aiSnake.getHead().y, nearestApplePosition.x, nearestApplePosition.y);
            List<Node> path = aStar.findPath();
            System.out.println(path.size());
            if (!path.isEmpty()) {
                aiSnake.setAiPath(this.copyNodePathToInstructionArray(path));
            }
        }*/
    }

    public ArrayList<Integer> copyNodePathToInstructionArray(List<Node> path) {
        ArrayList<Integer> instructionArray = new ArrayList<>();
        Point previousPoint = aiSnake.getHeadPosition();
        for (Node node : path) {
            if (this.getAiSnake().getDirection() == Direction.UP) {
                if (previousPoint.x + 1 == node.getX()) {
                    instructionArray.add(1);
                } else if (previousPoint.x - 1 == node.getX()) {
                    instructionArray.add(-1);
                }
            } else if (this.getAiSnake().getDirection() == Direction.DOWN) {
                if (previousPoint.x + 1 == node.getX()) {
                    instructionArray.add(-1);
                } else if (previousPoint.x - 1 == node.getX()) {
                    instructionArray.add(1);
                }
            } else if (this.getAiSnake().getDirection() == Direction.RIGHT) {
                if (previousPoint.y + 1 == node.getY()) {
                    instructionArray.add(1);
                } else if (previousPoint.y - 1 == node.getX()) {
                    instructionArray.add(-1);
                }
            } else if (this.getAiSnake().getDirection() == Direction.LEFT) {
                if (previousPoint.y + 1 == node.getY()) {
                    instructionArray.add(-1);
                } else if (previousPoint.y - 1 == node.getY()) {
                    instructionArray.add(1);
                }
            } else {
                instructionArray.add(0);
            }
        }
        return instructionArray;
    }


    public int[][] generateGrid(String gameBoard) {
        int[][] grid = new int[Constants.GAME_WIDTH][Constants.GAME_HEIGHT];
        int x = 0, y = 0;
        for (char c : gameBoard.toCharArray()) {
            if (c != '\n') {
                switch (c) {
                    case ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> grid[x][y] = 0;
                    default -> grid[x][y] = 1;
                }
                if (x == 99) {
                    y++;
                }
                x = (x + 1) % 100;
            }
        }
        return grid;
    }

    public Point getNearestApple() {
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

    @Override
    public void endGame() {
        if (playerSnake.getLength() >= 100 || aiSnake.getLength() <= 0) {
            win = true;
        }
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLost() {
        if (playerSnake.getLength() <= 0 || aiSnake.getLength() >= 100) {
            return true;
        }
        return false;
    }


    public void resetGame() {
        apples.clear();

        playerSnake = new Snake(new Point(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2), 3, Direction.RIGHT);
        aiSnake = new Snake(new Point(Constants.GAME_WIDTH / 2 + 10, Constants.GAME_HEIGHT / 2), 3, Direction.LEFT);

        addNewApples();
    }
}
