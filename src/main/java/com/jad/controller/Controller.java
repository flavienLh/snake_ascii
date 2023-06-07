package com.jad.controller;

import com.jad.Direction;
import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;
import com.jad.model.Model;
import javax.swing.*;


public class Controller implements IController {
    private final IView view;
    private final IModel model;
    private boolean gameRunning = true;


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
        this.model.start();
        this.view.display();
        runGame();
    }

    public void runGame() {
        while (gameRunning) {
            this.model.updateGame();
            view.display();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
