package com.jad.view;

import com.jad.Constants;
import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;

import javax.swing.*;
import java.awt.*;

import static com.jad.view.GameWindow.*;

public class View implements IView {
    private IModel model;
    private IController controller;
    private JTextArea screen;
    private final GameWindow gameWindow = new GameWindow("Ascii Snake");

    @Override
    public void display() {
        this.gameWindow.drawGameState(model);
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
