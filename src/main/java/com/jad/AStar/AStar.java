package com.jad.AStar;

import com.jad.Constants;

import java.util.*;

public class AStar {
    int[][] grid;  // the grid representing the map
    int startX, startY;  // starting coordinates
    int goalX, goalY;    // goal coordinates

    public AStar(int[][] grid, int startX, int startY, int goalX, int goalY) {
        this.grid = grid;
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    public List<Node> findPath() {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node startNode = new Node(startX, startY);
        startNode.h = calculateHeuristic(startX, startY);
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = getLowestFNode(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);

            if (currentNode.x == goalX && currentNode.y == goalY) {
                return buildPath(currentNode);
            }

            List<Node> neighbors = getNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (closedList.contains(neighbor)) {
                    continue;
                }

                int tentativeG = currentNode.g + 1;  // assuming uniform cost for each step

                if (!openList.contains(neighbor) || tentativeG < neighbor.g) {
                    neighbor.g = tentativeG;
                    neighbor.h = calculateHeuristic(neighbor.x, neighbor.y);
                    neighbor.parent = currentNode;

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        // No path found
        return new ArrayList<>();
    }

    private int calculateHeuristic(int x, int y) {
        // Manhattan distance heuristic
        return Math.abs(x - goalX) + Math.abs(y - goalY);
    }

    private Node getLowestFNode(List<Node> nodes) {
        Node lowestNode = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (node.getF() < lowestNode.getF()) {
                lowestNode = node;
            }
        }
        return lowestNode;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.x;
        int y = node.y;

        // Add all valid neighbors (up, down, left, right)
        if (x > 0 && grid[x - 1][y] != 0) {
            neighbors.add(new Node(x - 1, y));
        }
        if (x < grid.length - 1 && grid[x + 1][y] != 0) {
            neighbors.add(new Node(x + 1, y));
        }
        if (y > 0 && grid[x][y - 1] != 0) {
            neighbors.add(new Node(x, y - 1));
        }
        if (y < grid[x].length - 1 && grid[x][y + 1] != 0) {
            neighbors.add(new Node(x, y + 1));
        }

        return neighbors;
    }

    private List<Node> buildPath(Node node) {
        List<Node> path = new ArrayList<>();
        Node current = node;
        while (current != null) {
            path.add(0, current);
            current = current.parent;
        }
        return path;
    }
}
