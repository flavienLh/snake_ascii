package com.jad;

import java.awt.*;

public class PoisonousApple extends Apple {
    public PoisonousApple(Point position, int value) {
        super(position, -Math.abs(value));
    }

    @Override
    public boolean isPoisonous() {
        return true;
    }
}
