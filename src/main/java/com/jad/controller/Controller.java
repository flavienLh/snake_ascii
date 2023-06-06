package com.jad.controller;

import com.jad.Direction;
import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;
import com.jad.model.Model;

public class Controller implements IController {
    private final IView view;
    private final IModel model;
    private boolean gameRunning = true;
    private IView gameWindow;

    public Controller(final IView view, final IModel model) {
        this.view = view;
        this.model = model;
        this.view.setController(this);
        this.view.setModel(this.model);
        this.model.setController(this);
    }

    @Override
    public void changePlayerDirection(Direction direction) {
        model.changePlayerDirection(direction);
    }

    @Override
    public void start() {
        this.view.display();
    }

    public void runGame() {
        while (gameRunning) {
            Model.tick();
            gameWindow.drawGameState(model.getgameState());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
