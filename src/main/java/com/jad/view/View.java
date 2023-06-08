package com.jad.view;

import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;

import javax.swing.*;

public class View implements IView {
    private IModel model;
    private IController controller;
    private JTextArea screen;
    private GameWindow gameWindow;

    @Override
    public String display() {
        return this.gameWindow.drawGameState(model);
    }

    @Override
    public void setModel(final IModel model) {
        this.model = model;
    }

    @Override
    public void setController(final IController controller) {
        this.controller = controller;
        this.gameWindow = new GameWindow("Ascii Snake", controller);
    }
}
