import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A2_Q1 {

    public static int[] game(String[][] board){

        int balls = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals("o")){
                    balls++;
                }
            }
        }

        return recursiveGame(board,0, 0, balls, 0);

    }

    public static int[] recursiveGame(String[][] board, int positionX, int positionY,
                                      int balls, int jumps){

        ArrayList<int[]> possibleSolutions = new ArrayList<>();
        if ((positionX == board.length-1 && positionY == board[0].length-1)|| balls==1){
            int[] temp = {balls, jumps};
            return temp;
        }
        if (positionY == board[0].length-1){
            positionY = 0;
            positionX++;
        }

        if (board[positionX][positionY].equals("o")) {
            try {
                if (board[positionX + 1][positionY].equals("o") && board[positionX + 2][positionY].equals(".")) {
                    possibleSolutions.add(recursiveGame(board, positionX, positionY+1, balls, jumps));
                    board[positionX][positionY] = ".";
                    board[positionX + 1][positionY] = ".";
                    board[positionX + 2][positionY] = "o";
                    balls--;
                    jumps++;

                }
            } catch (Exception e) {
            }

            try {
                if (board[positionX - 1][positionY].equals("o") && board[positionX - 2][positionY].equals(".")) {
                    board[positionX][positionY] = ".";
                    board[positionX - 1][positionY] = ".";
                    board[positionX - 2][positionY] = "o";
                    balls--;
                    jumps++;
                }
            } catch (Exception e) {
            }

            try {
                if (board[positionX][positionY + 1].equals("o") && board[positionX][positionY + 2].equals(".")) {
                    possibleSolutions.add(recursiveGame(board, positionX, positionY+1, balls, jumps));
                    board[positionX][positionY] = ".";
                    board[positionX][positionY + 1] = ".";
                    board[positionX][positionY + 2] = "o";
                    balls--;
                    jumps++;

                }
            } catch (Exception e) {
            }

            try {
                if (board[positionX][positionY - 1].equals("o") && board[positionX][positionY - 2].equals(".")) {
                    board[positionX][positionY] = ".";
                    board[positionX][positionY - 1] = ".";
                    board[positionX][positionY - 2] = "o";
                    balls--;
                    jumps++;
                }
            } catch (Exception e) {
            }

        }

        int[] temp = recursiveGame(board, positionX, positionY+1, balls, jumps);
        for (int[] a: possibleSolutions) {
            if (a[0] < temp[0]){
                temp[0] = a[0];
                temp[1] = a[1];
            }
        }
        return temp;

//        return recursiveGame(board, positionX, positionY+1, balls, jumps);

    }

    final static int HEIGHT = 5;
    final static int WIDTH = 9;

    final static String TEST_FOLDER = "Open_Tests/Q1";
    public static void main(String[] args) {
        File f = new File(TEST_FOLDER);
        for (String name : f.list()) {
            if (name.endsWith(".in")) {
                try {
                    File in = new File(TEST_FOLDER + "/" + name);
                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
                    Scanner in_scan = new Scanner(in);
                    Scanner out_scan = new Scanner(out);
                    System.out.printf("Attempting file %s\n", name);
                    int n = in_scan.nextInt();
                    in_scan.nextLine();
                    for (int cs = 0; cs < n; cs++) {
                        String[][] board = new String[HEIGHT][WIDTH];
                        for (int i = 0; i < HEIGHT; i++) {
                            String line = in_scan.nextLine();
                            for (int j = 0; j < WIDTH; j++) {
                                board[i][j] = new String(new char[]{line.charAt(j)});
                            }
                        }
                        int[] got = game(board);
                        int expected_0 = out_scan.nextInt();
                        int expected_1 = out_scan.nextInt();
                        if (got[0] != expected_0 || got[1] != expected_1) {
                            System.out.printf("Expected %d %d but got %d %d\n", expected_0, expected_1, got[0], got[1]);
                        } else {
                            System.out.printf("Passed test %d\n", cs);
                        }
                        try {
                            in_scan.nextLine(); // Skip empty line
                        } catch (NoSuchElementException e) {

                        }
                    }

                    in_scan.close();
                    out_scan.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            }
        }
    }

}