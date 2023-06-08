package com.jad;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;


public class Snake {

    private Deque<Point> body;
    private Direction direction;

    private int length;

    private ArrayList<Integer> aiPath = new ArrayList<>();

    public Snake(Point startPoint, int initialSize, Direction direction) {
        this.body = new ArrayDeque<>();
        this.direction = direction;
        for (int i = 0; i < initialSize; i++) {
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

    public boolean selfHit() {
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

        switch (this.direction) {
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
    }

    public void shrink(int shrink) {
        for (int i = 0; i > shrink; i--) {
            if (body.size() > 0) {
                body.removeLast();
            }
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

    public void setAiPath(ArrayList<Integer> aiPath) {
        this.aiPath = aiPath;
    }

    public int getNextInstruction() {
        int nextInstruction = 0;
        if (this.aiPath.size() > 0) {
            nextInstruction = this.aiPath.get(0);
            this.aiPath.remove(0);
        }
        return nextInstruction;
    }

    public void updateSnakePosition() {
        Direction currentDirection = this.getDirection();
        Point newHeadPosition = new Point(this.getHeadPosition());
        switch (currentDirection) {
            case UP:
                newHeadPosition.y--;
                break;
            case DOWN:
                newHeadPosition.y++;
                break;
            case LEFT:
                newHeadPosition.x--;
                break;
            case RIGHT:
                newHeadPosition.x++;
                break;
        }

        this.move(newHeadPosition);
    }

    public Deque<Point> getBody() {
        return body;
    }

    public int getLength() {
        return this.body.size();
    }


    // getters et setters
}

