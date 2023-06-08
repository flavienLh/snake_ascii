package com.jad;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;



public class Snake {

    private Deque<Point> body;
    private Direction direction;
    private int length;

    public Snake(Point startPoint, int initialSize, Direction direction) {
        this.body = new ArrayDeque<>();
        this.direction = direction;
        for(int i = 0; i < initialSize; i++) {
            this.body.addLast(new Point(startPoint.x - i, startPoint.y));
        }
    }

    public boolean contains(Point point) {
        return this.body.contains(point);
    }

    public Point getHead() {
        return body.getFirst();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Point getHeadPosition() {
        if (this.body.size() > 0) {
            return this.body.getFirst();
        } else {
            return null;
        }
    }

    public boolean selfHit(){
        int c = 0;
        for (Point point : this.getBody()) {
            if (c != 0 && this.getHead().equals(point)) {
                return true;
            }
            c++;
        }
        return false;
    }

    public boolean hitOtherSnake(Snake otherSnake) {
        for (Point point : otherSnake.getBody()) {
            if (this.getHead().equals(point)) {
                return true;
            }
        }
        return false;
    }

    public void move(Point newHeadPosition) {
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
        this.body.addFirst(newHead);


        if (this.body.size() > this.length) {
            this.body.removeLast();
        }
    }


    public void grow(int growth) {
        for (int i = 0; i < growth; i++) {
            body.addLast(new Point(-1, -1));
        }
        System.out.println("Grow : "+this.body.size());
    }

    public void shrink(int shrink) {
        for (int i = 0; i > shrink; i--){
            body.removeLast();
        }
    }

    public void changeDirection(Direction newDirection) {

        if ((this.direction == Direction.UP && newDirection == Direction.DOWN) ||
                (this.direction == Direction.DOWN && newDirection == Direction.UP) ||
                (this.direction == Direction.LEFT && newDirection == Direction.RIGHT) ||
                (this.direction == Direction.RIGHT && newDirection == Direction.LEFT)) {
            return;
        }

        this.direction = newDirection;
    }

    public Deque<Point> getBody() {
        return body;
    }


    // getters et setters
}

