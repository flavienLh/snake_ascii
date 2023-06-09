package com.jad;

import java.awt.*;
import java.util.ArrayList;

public interface IModel {

    void changePlayerDirection(Direction direction);

    void setController(IController controller);

    Snake getAiSnake();

    Snake getPlayerSnake();

    ArrayList<Apple> getApples();

    void start();

    boolean positionApples(Point point);

    Apple getAppleAtPos(Point point);

    void updateGame(int t);

    boolean isWin();

    void resetGame();

    boolean isLost();
}
