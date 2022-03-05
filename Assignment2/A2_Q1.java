import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A2_Q1 {

    public static int[] game(String[][] board){
        ArrayList<int[]> possibleSolutions = new ArrayList<>();
        int jumps = 0;
        recursive(board, 0, 0,0, jumps, possibleSolutions, false);
        int[] mostEfficient = {1000, 1000};
        // Chooses the most efficient path
        for (int i = 0; i < possibleSolutions.size(); i++) {
            if (possibleSolutions.get(i)[0] < mostEfficient[0]){
                mostEfficient[0] = possibleSolutions.get(i)[0];
                mostEfficient[1] = possibleSolutions.get(i)[1];
            }
            else if (possibleSolutions.get(i)[0] == mostEfficient[0]){
                if (possibleSolutions.get(i)[1] < mostEfficient[1]){
                    mostEfficient[0] = possibleSolutions.get(i)[0];
                    mostEfficient[1] = possibleSolutions.get(i)[1];
                }
            }
        }
        return mostEfficient;

    }

    private static void recursive(String[][] board, int positionX, int positionY, int directionIndex, int numberOfJumps, ArrayList<int[]> arrayList, boolean movedBall){
        int i = positionX;
        int j = positionY;
        int k = directionIndex;
        for( ;i < board.length; i++){
            for ( ;j < board[0].length; j++) {
                // Checks for all possibles paths
                for (; k < 4; k++) {
                    int directionY = 0;
                    int directionX = 0;
                    switch (k){
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
                    }
                    // try catch for OutOfBoundsException
                    try{
                        if (board[i+directionY][j+directionX].equals("o") && board[i+2*directionY][j+2*directionX].equals(".") && board[i][j].equals("o")){
                            String[][] temp;
                            temp = deepCopy(board);
                            // Checks if there's other possible paths
                            recursive(temp, i, j+(k==3?1:0),k == 3?0:k+1, numberOfJumps, arrayList, movedBall);
                            board[i][j] = ".";
                            board[i+directionY][j+directionX] = ".";
                            board[i+2*directionY][j+2*directionX] = "o";
                            movedBall = true;
                            numberOfJumps++;
                            break;
                        }
                    } catch (Exception e) {}
                }
                if(movedBall){
                    break;
                }
                k = 0;
            }
            if(movedBall){
                break;
            }
            j = 0;

        }


        if (movedBall){
            recursive(board, 0, 0, 0, numberOfJumps, arrayList, false);
            return;
        }

        int[] temp = {numberOfBalls(board), numberOfJumps};
        arrayList.add(temp);


        return;
    }

    /*
    * Checks the amount of balls in the board
    * Input: Board
    * Output: Number of balls in the board
    * */
    private static int numberOfBalls(String[][] board){
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals("o")){
                    counter++;
                }
            }
        }
        return counter;
    }


    // Creates a deep copy
    private static String[][] deepCopy(String[][] array){
        String[][] newArray = new String[5][9];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                newArray[i][j] = array[i][j];
            }
        }

        return newArray;
    }



}