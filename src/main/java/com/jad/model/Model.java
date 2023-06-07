package com.jad.model;

import com.jad.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Model implements IModel {
    private IController controller;
    private static Snake playerSnake;
    private static Snake aiSnake;
    private static ArrayList<Apple> apples = new ArrayList<Apple>();
    private static final Random random = new Random();

    public void start() {
        playerSnake = new Snake(new Point(Constants.GAME_WIDTH/2, Constants.GAME_HEIGHT/2), 3, Direction.RIGHT);
        aiSnake = new Snake(new Point(Constants.GAME_WIDTH/2 + 10, Constants.GAME_HEIGHT/2), 3, Direction.LEFT);
        this.addNewApples();
    }

    public void addNewApples(){
        for (int i = 0; i < random.nextInt(3) + 1; i++){
            apples.add(new Apple(new Point(random.nextInt(Constants.GAME_WIDTH), random.nextInt(Constants.GAME_HEIGHT)), random.nextInt(9) + 1));
            System.out.println(apples.get(apples.size()-1).getValue());
        }
    }

    private void updatePlayerSnakePosition() {
        Direction currentDirection = playerSnake.getDirection();
        Point newHeadPosition = new Point(playerSnake.getHeadPosition());
        switch (currentDirection) {
            case UP:
                newHeadPosition.y--;
                break;
            case DOWN:
                newHeadPosition.y++;
                break;
            case LEFT:
                newHeadPosition.x--;
                break;
            case RIGHT:
                newHeadPosition.x++;
                break;
        }

        playerSnake.move(newHeadPosition);
    }

    public void updateGame() {
        updatePlayerSnakePosition();
        tick();
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


    public static void tick() {


        for (int i = 0; i < apples.size(); i++){
            if (playerSnake.getHead().equals(apples.get(i).getPosition())) {
                if (!apples.get(i).isPoisonous())
                    playerSnake.grow(apples.get(i).getValue());
                else
                    playerSnake.shrink(apples.get(i).getValue());
                apples.remove(i);
            }
            if (aiSnake.getHead().equals(apples.get(i).getPosition())) {
                if (!apples.get(i).isPoisonous())

                    aiSnake.grow(apples.get(i).getValue());
                else
                    aiSnake.shrink(apples.get(i).getValue());
                apples.remove(i);
            }
        }
    }

    public void changePlayerDirection(Direction direction) {
        playerSnake.changeDirection(direction);
    }
    @Override
    public void setController(final IController controller) {
        this.controller = controller;
    }

    public boolean positionApples(Point point){
        int i = 0;
        boolean AiP = false;
        while (!AiP && i < apples.size()){
            AiP = apples.get(i).getPosition().equals(point);
            i++;
        }
        return AiP;
    }

    @Override
    public Apple getAppleAtPos(Point point) {
        int i = 0;
        boolean AiP = false;
        while (!AiP && i < apples.size()){
            AiP = apples.get(i).getPosition().equals(point);
            i++;
        }
        return apples.get(i-1);
    }


}
