package com.jad;

import com.jad.Constants;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.random;

public class Apple {
    private final Point position;
    private final int value;
    private final boolean isPoisonous;

    public Apple(Point position, int value) {
        this.position = position;
        this.value = value;
        if (new Random().nextInt(100) >= 75)
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

