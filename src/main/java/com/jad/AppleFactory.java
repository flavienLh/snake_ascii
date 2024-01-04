package com.jad;

import java.awt.*;
import java.util.Random;

public class AppleFactory {
    private static final Random random = new Random();

    public static Apple createApple(Point position, int value) {
        if (random.nextInt(100) >= 75) {
            return new PoisonousApple(position, value);
        } else {
            return new Apple(position, value);
        }
    }
}
