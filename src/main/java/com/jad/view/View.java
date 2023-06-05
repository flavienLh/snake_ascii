package com.jad.view;

import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;

public class View implements IView {
    private IModel model;
    private IController controller;
    private final GameWindow gameWindow = new GameWindow("Ascii Snake");

    @Override
    public void display() {
        this.gameWindow.setScreen("Not yet implemented");
    }

    @Override
    public void setModel(final IModel model) {
        this.model = model;
    }

    @Override
    public void setController(final IController controller) {
        this.controller = controller;
    }
}
