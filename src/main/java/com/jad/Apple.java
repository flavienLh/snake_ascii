package com.jad;

import java.awt.*;
import java.util.Random;

public class Apple {
    private final Point position;
    private int value;

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

    //public boolean isPoisonous() {
    //    return value > 0 ? false : true;
    //}

    public boolean isPoisonous() {
        return false;
    }
}


