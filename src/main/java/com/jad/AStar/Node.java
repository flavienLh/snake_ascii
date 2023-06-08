package com.jad.AStar;

public class Node {
    int x, y;  // coordinates of the node
    int g;     // cost from the start node
    int h;     // heuristic value (estimated cost to the goal)
    Node parent;  // parent node

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = 0;
        this.h = 0;
        this.parent = null;
    }

    public int getF() {
        return g + h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
