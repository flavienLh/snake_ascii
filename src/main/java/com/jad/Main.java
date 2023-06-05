package com.jad;

import com.jad.controller.Controller;
import com.jad.model.Model;
import com.jad.view.View;

public enum Main {
    ;

    public static void main(final String[] args) {
        final IController controller = new Controller(new View(), new Model());
        controller.start();
    }
}