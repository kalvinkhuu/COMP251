//import java.util.LinkedList;
//import java.util.Queue;

import java.util.*;

public class Q1 {
    /*
    * Find the start node and initialize each position as unvisited
    * Search for its neighbors
    * Check if it's the end node
    * Else search for its neighbors
    * Do until end is found or no possible paths
    * Return the distance
    * */

    static Node startPoint = new Node();
    static boolean[][][] visited;

    public static int find_exit(String[][][] jail) {

        visited = new boolean[jail.length][jail[0].length][jail[0][0].length];

        int distance = 0;
        Queue<Node> nodeQ = new LinkedList();
        // Searching for the start
        findingStartingPoint(jail);
        // Start the search for the exit
        nodeQ.add(startPoint);
        while (!nodeQ.isEmpty()){
            Node node = nodeQ.remove();

            for (int i = 0; i < 6; i++) {
                int directionX = 0; // West - East
                int directionY = 0; // North - South
                int directionZ = 0; // Up - Down
                switch (i){
                    case 0:
                        directionX = 1;
                        break;
                    case 1:
                        directionX = -1;
                        break;
                    case 2:
                        directionY = 1;
                        break;
                    case 3:
                        directionY = -1;
                        break;
                    case 4:
                        directionZ = 1;
                        break;
                    case 5:
                        directionZ = -1;
                        break;
                }
                try {
                    if (jail[node.level+directionZ][node.row+directionX][node.column+directionY].equals(".")){
                        Node neighbor = new Node(node.level+directionZ, node.row+directionX, node.column+directionY);
                        nodeQ.add(neighbor);
                        distance++;
                    }
                    else if (jail[node.level+directionZ][node.row+directionX][node.column+directionY].equals("E")){
                        distance++;
                        return distance;
                    }
                } catch (Exception e){}
            }
        }
        return 0;
    }

    public static class Node {
        int level;
        int row;
        int column;

        public Node(){};

        public Node(int level, int row, int column) {
            this.level = level;
            this.row = row;
            this.column = column;
        }
    }
    private static void findingStartingPoint(String[][][] jail){
        int numLevel = jail.length;
        int numRow = jail[0].length;
        int numColumn = jail[0][0].length;
        for (int i = 0; i < numLevel; i++) {
            for (int j = 0; j < numRow; j++) {
                for (int k = 0; k < numColumn; k++) {
                    if (jail[i][j][k].equals("S")){
                        startPoint.level = i;
                        startPoint.row = j;
                        startPoint.column = k;
                    }
                    visited[i][j][k] = false;
                }
            }
        }
    }


    public static void main(String[] args) {

    }

}
