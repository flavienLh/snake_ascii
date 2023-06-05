package com.jad.controller;

import com.jad.IController;
import com.jad.IModel;
import com.jad.IView;

public class Controller implements IController {
    private final IView view;
    private final IModel model;

    public Controller(final IView view, final IModel model) {
        this.view = view;
        this.model = model;
        this.view.setController(this);
        this.view.setModel(this.model);
        this.model.setController(this);
    }

    @Override
    public void start() {
        this.view.display();
    }
}
