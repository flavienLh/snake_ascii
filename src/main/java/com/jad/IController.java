package com.jad;

public interface IController {

    void changePlayerDirection(Direction direction);
    void start();
    Direction getCurrentDirection();
    void runGame();
}
