package com.jad;

import com.jad.controller.Controller;
import com.jad.model.Model;
import com.jad.view.View;

public enum Main {
    ;

    public static void main(final String[] args) {
        final IView view = new View();
        final IModel model = new Model();
        final IController controller = new Controller(view, model);

        controller.start();
        controller.runGame();
    }
}