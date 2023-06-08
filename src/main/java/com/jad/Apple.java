package com.jad;

import com.jad.Constants;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.random;

public class Apple {
    private final Point position;
    private int value;

    public Apple(Point position, int value) {
        this.position = position;
        this.value = value;
        if (new Random().nextInt(100) >= 75) {
            this.value *= -1;
        }
    }


    // Getters
    public Point getPosition() {
        return this.position;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isPoisonous() {
        return value > 0 ? false : true;
    }
}

