package com.jad;

import java.util.ArrayList;

public interface IModel {
    IModel getGameState();
    void changePlayerDirection(Direction direction);
    void setController(IController controller);

    Snake getAiSnake();

    Snake getPlayerSnake();

    ArrayList<Apple> getApples();

    void start();
}
