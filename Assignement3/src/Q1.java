//import java.util.LinkedList;
//import java.util.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
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
            List<Node> neighbors = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int directionX = 0; // West - East
                int directionY = 0; // North - South
                int directionZ = 0; // Up - Down
                Node neighbor = new Node();
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
                        neighbor.level = node.level+directionZ;
                        neighbor.row = node.row+directionX;
                        neighbor.column = node.column+directionY;
                        neighbor.distance = node.distance+1;
                        neighbors.add(neighbor);
                    }
                    else if (jail[node.level+directionZ][node.row+directionX][node.column+directionY].equals("E")){
                        int totalDistance = node.distance + 1;
                        return totalDistance;
                    }
                } catch (Exception e){}

            }
            for (Node n: neighbors){
                if (!visited[n.level][n.row][n.column]){
                    nodeQ.add(n);
                    visited[n.level][n.row][n.column] = true;
                }
            }
        }
        return -1;
    }

    public static class Node {
        int level;
        int row;
        int column;
        int distance;

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
                        startPoint.distance = 0;
                        visited[i][j][k] = true;
                    }
                }
            }
        }
    }




    public static void main(String[] args) throws FileNotFoundException {
        //File f = new File("C:\\Users\\matth\\Documents\\School\\S4_W22\\COMP 251\\A3\\src");
        File f = new File("C:\\Users\\Kalvin\\Documents\\GitHub\\COMP251\\Assignement3\\src\\testFilesQ1");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        int totalTests = files.length;
        int numCorrect = 0;
        assert files != null;
        for (File file : files) {
            System.out.println("\nNow testing " + file.getName() + "\n" + "\n==================================================================================");
            int expectedScore = -1;
            String[][][] jail = new String[0][0][0];
            String filePath = file.getPath();
            try {
                File inputFile = new File(filePath);
                Scanner fileScanner = new Scanner(inputFile);
                expectedScore = Integer.parseInt(fileScanner.nextLine());
                int rows = 0;
                int columns = 0;
                int levels = 0;
                int rowNum = 0;
                int levelNum = 0;
                boolean found = false;
                do {

                    String line = fileScanner.nextLine();
                    if (line.length() == 0) {
                        levels++;
                        found = true;
                    } else {
                        columns = line.length();
                    }
                    if (!found) {
                        rows++;
                    }
                } while (fileScanner.hasNext());
                jail = new String[levels + 1][rows][columns];
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            try {
                File inputFile = new File(filePath);
                Scanner fileScanner = new Scanner(inputFile);
                fileScanner.nextLine();
                int rowNum = 0;
                int levelNum = 0;
                do {
                    String line = fileScanner.nextLine();
                    if (rowNum == jail[0].length) {
                        rowNum = 0;
                        levelNum += 1;
                        continue;
                    }
                    for (int i = 0; i < jail[0][0].length; i++) {
                        jail[levelNum][rowNum][i] = line.charAt(i) + "";

                    }
                    rowNum++;

                } while (fileScanner.hasNext());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            int result = find_exit(jail);
            if (expectedScore == result) {
                System.out.println("Test Passed (expected " + expectedScore + " and got " + result + ")\n" + "\n==================================================================================");
                numCorrect++;
            }else{
                System.out.println("Test Failed (expected " + expectedScore + " and got " + result + ")\n" + "\n==================================================================================");
            }

        }
        System.out.println("You got " + numCorrect + " out of " + totalTests + " total tests correct!");
    }

}
