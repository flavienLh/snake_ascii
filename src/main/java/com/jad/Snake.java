package com.jad;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {

    private Deque<Point> body;
    private Direction direction;

    public Snake(Point startPoint, int initialSize, Direction direction) {
        this.body = new ArrayDeque<>();
        this.direction = direction;
        for(int i = 0; i < initialSize; i++) {
            this.body.addLast(new Point(startPoint.x - i, startPoint.y));
        }
    }

    public Point getHead() {
        return body.getFirst();
    }



    public void move() {
        Point head = this.body.getFirst();
        Point newHead = null;

        switch(this.direction) {
            case UP:
                newHead = new Point(head.x, (head.y - 1 + Constants.GAME_HEIGHT) % Constants.GAME_HEIGHT);
                break;
            case DOWN:
                newHead = new Point(head.x, (head.y + 1) % Constants.GAME_HEIGHT);
                break;
            case LEFT:
                newHead = new Point((head.x - 1 + Constants.GAME_WIDTH) % Constants.GAME_WIDTH, head.y);
                break;
            case RIGHT:
                newHead = new Point((head.x + 1) % Constants.GAME_WIDTH, head.y);
                break;
        }

    }

    public void grow(int growth) {
        for (int i = 0; i < growth; i++) {
            body.addLast(new Point(-1, -1));  // The position doesn't matter, it will be overwritten in the next move
        }
    }

    public void shrink(int shrink) {
        // ajouter ici la logique de réduction
    }

    public void changeDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    // getters et setters
}

