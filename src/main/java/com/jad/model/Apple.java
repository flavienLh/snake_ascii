package com.jad.model;

import com.jad.Constants;

import java.awt.*;

public class Apple {
    private Point position;
    private int value;
    private boolean isPoisonous;

    public Apple(Point position, int value) {
        this.position = position;
        this.value = value;
    }
    

    // Getters
    public Point getPosition() {
        return this.position;
    }

    public int getValue() {
        return this.value;
    }


}

