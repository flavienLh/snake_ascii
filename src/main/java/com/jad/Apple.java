package com.jad;

import com.jad.Constants;

import java.awt.*;

import static java.lang.Math.random;

public class Apple {
    private Point position;
    private int value;
    private boolean isPoisonous;

    public Apple(Point position, int value) {
        this.position = position;
        this.value = value;
        if (random() >= 0.75 )
            isPoisonous = false;
        else
            isPoisonous = true;
    }


    // Getters
    public Point getPosition() {
        return this.position;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }
}

