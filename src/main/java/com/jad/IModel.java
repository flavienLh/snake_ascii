package com.jad;

import com.jad.model.Apple;
import com.jad.model.Model;

public interface IModel {
    IModel getGameState();
    void changePlayerDirection(Direction direction);
    void setController(IController controller);

    Snake getAiSnake();

    Snake getPlayerSnake();

    Apple getApple();

    IModel getgameState();
}
