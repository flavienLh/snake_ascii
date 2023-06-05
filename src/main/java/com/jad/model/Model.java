package com.jad.model;

import com.jad.IController;
import com.jad.IModel;

public class Model implements IModel {
    private IController controller;
    @Override
    public void setController(final IController controller) {
        this.controller = controller;
    }
}
