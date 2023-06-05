package com.jad;

public class Snake {
    private Deque<Point> body;
    private Direction direction;

    public static class CollisionException extends RuntimeException {
        public CollisionException() {
            super("Collision");
        }
    }
}

    public Snake(Point startPoint, int initialSize, Direction direction) {
        this.body = new ArrayDeque<>();
        this.direction = direction;
        for(int i = 0; i < initialSize; i++) {
            this.body.addLast(new Point(startPoint.x - i, startPoint.y));
        }
    }

    public void move(Snake otherSnake) {
        Point head = this.body.getFirst();
        Point newHead;

        switch(this.direction) {
            case UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
        }

        if (this.body.contains(newHead) || otherSnake.getBody().contains(newHead)) {
            throw new CollisionException();
        }

        this.body.addFirst(newHead);
        this.body.removeLast();
    }

    public void grow(int growth) {

    }

    public void shrink(int shrink) {

    }

    public void changeDirection(Direction newDirection) {
        this.direction = newDirection;
    }


}
