package com.jad.model;

import com.jad.Constants;

import java.awt.*;

public class Apple {
    private Point position;
    private int value;

    public Apple(Point position, int value) {
        this.position = position;
        this.value = value;
    }

    // Getters
    public Point getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }


}

