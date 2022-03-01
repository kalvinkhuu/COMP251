import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A2_Q1 {

    public static int[] game(String[][] board){
        // [Minimum number of balls that remains in the board, Minimum number of moves performed]
        // String[Height][Width] = "." | "o" | "#"
        ArrayList<int[]> possiblesSolutions = new ArrayList<>();
        int width = board.length;
        int height = board[0].length;
        int numberOfBalls = 0;
        int numberOfMovements = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(board[i][j].equals("o")) {
                    numberOfBalls = numberOfBalls+1;
                    try{
                        if (board[i+1][j].equals("o") && board[i+2][j].equals(".")){
                            board[i][j] = ".";
                            board[i+1][j] = ".";
                            board[i+2][j] = "o";
                            numberOfMovements = numberOfMovements+1;
                            possiblesSolutions.add(game(board));
                        }
                    }catch (Exception e ){}
                    try{
                        if (board[i-1][j].equals("o") && board[i-2][j].equals(".")){
                            board[i][j] = ".";
                            board[i-1][j] = ".";
                            board[i-2][j] = "o";
                            numberOfMovements = numberOfMovements+1;
                            possiblesSolutions.add(game(board));

                        }
                    }catch (Exception e ){}
                    try{
                        if (board[i][j+1].equals("o") && board[i][j+2].equals(".")){
                            board[i][j] = ".";
                            board[i][j+1] = ".";
                            board[i][j+2] = "o";
                            numberOfMovements = numberOfMovements+1;
                            possiblesSolutions.add(game(board));
                        }
                    }catch (Exception e ){}
                    try{
                        if (board[i][j-1].equals("o") && board[i][j-2].equals(".")){
                            board[i][j] = ".";
                            board[i][j-1] = ".";
                            board[i][j-2] = "o";
                            numberOfMovements = numberOfMovements+1;
                            possiblesSolutions.add(game(board));
                        }
                    }catch (Exception e){}

                }
            }
        }
        if(possiblesSolutions.isEmpty())
        {
            int[] finalResults = {numberOfBalls, numberOfMovements};
            return finalResults;
        }
        int[] finalResults = {numberOfBalls, numberOfMovements};
        System.out.println(possiblesSolutions.get(0)[0]);
        return finalResults;

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