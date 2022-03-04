import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A2_Q1 {

    public static int[] game(String[][] board){
        ArrayList<int[]> possibleSolutions = new ArrayList<>();
        ArrayList<int[]> empty = new ArrayList<>();
        int marbles = numberOfBalls(board);
        int jumps = 0;
        possibleSolutions = recursive(board, 0, 0, marbles, jumps, empty, false);

        int[] mostEfficient = {1000, 1000};
        for (int i = 0; i < possibleSolutions.size(); i++) {
            if (possibleSolutions.get(i)[0] < mostEfficient[0]){
                mostEfficient[0] = possibleSolutions.get(i)[0];
                mostEfficient[1] = possibleSolutions.get(i)[1];
            }
        }


        return mostEfficient;

    }

    private static ArrayList<int[]> recursive(String[][] board, int positionX, int positionY, int numberOfMarbles, int numberOfJumps, ArrayList<int[]> arrayList, boolean movedBall){
        for(int i = positionX; i < board.length; i++){
            for (int j = positionY; j < board[0].length; j++) {
                    try{
                        if(board[i+1][j].equals("o") && board[i+2][j].equals(".") && board[i][j].equals("o")){
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                            movedBall = true;
                            numberOfJumps++;
                            numberOfMarbles--;
                            board[i][j] = ".";
                            board[i+1][j] = ".";
                            board[i+2][j] = "o";
                            recursive(board, i, j+1, numberOfMarbles, numberOfJumps, arrayList, movedBall);
                        }
                    } catch (Exception e) {}
                    try{
                        if(board[i-1][j].equals("o") && board[i-2][j].equals(".") && board[i][j].equals("o")){
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                            movedBall = true;
                            numberOfJumps++;
                            numberOfMarbles--;
                            board[i][j] = ".";
                            board[i-1][j] = ".";
                            board[i-2][j] = "o";
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                        }
                    } catch (Exception e) {}
                    try{
                        if(board[i][j+1].equals("o") && board[i][j+2].equals(".") && board[i][j].equals("o")){
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                            movedBall = true;
                            numberOfJumps++;
                            numberOfMarbles--;
                            board[i][j] = ".";
                            board[i][j+1] = ".";
                            board[i][j+2] = "o";
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                        }
                    } catch (Exception e) {}
                    try{
                        if(board[i][j-1].equals("o") && board[i][j-2].equals(".") && board[i][j].equals("o")){
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                            movedBall = true;
                            numberOfJumps++;
                            numberOfMarbles--;
                            board[i][j] = ".";
                            board[i][j-1] = ".";
                            board[i][j-2] = "o";
                            recursive(board, i, j+1, numberOfBalls(board), numberOfJumps, arrayList, movedBall);
                        }
                    } catch (Exception e) {}

            }
            positionY = 0;
        }

        if (movedBall){
            return recursive(board, 0, 0, numberOfBalls(board), numberOfJumps, arrayList, false);
        }

        int[] temp = {numberOfBalls(board), numberOfJumps};
        if (!arrayList.contains(temp)){
            arrayList.add(temp);
        }

        System.out.println(temp[0] + "," + temp[1]);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+",");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
        return arrayList;
    }

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



    final static int HEIGHT = 5;
    final static int WIDTH = 9;

    final static String TEST_FOLDER = "Open_Tests/Q1";
    public static void main(String[] args) {
//        File f = new File(TEST_FOLDER);
//        for (String name : f.list()) {
//            if (name.endsWith(".in")) {
//                try {
//                    File in = new File(TEST_FOLDER + "/" + name);
//                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
//                    Scanner in_scan = new Scanner(in);
//                    Scanner out_scan = new Scanner(out);
//                    System.out.printf("Attempting file %s\n", name);
//                    int n = in_scan.nextInt();
//                    in_scan.nextLine();
//                    for (int cs = 0; cs < n; cs++) {
//                        String[][] board = new String[HEIGHT][WIDTH];
//                        for (int i = 0; i < HEIGHT; i++) {
//                            String line = in_scan.nextLine();
//                            for (int j = 0; j < WIDTH; j++) {
//                                board[i][j] = new String(new char[]{line.charAt(j)});
//                            }
//                        }
//                        int[] got = game(board);
//                        int expected_0 = out_scan.nextInt();
//                        int expected_1 = out_scan.nextInt();
//                        if (got[0] != expected_0 || got[1] != expected_1) {
//                            System.out.printf("Expected %d %d but got %d %d\n", expected_0, expected_1, got[0], got[1]);
//                        } else {
//                            System.out.printf("Passed test %d\n", cs);
//                        }
//                        try {
//                            in_scan.nextLine(); // Skip empty line
//                        } catch (NoSuchElementException e) {
//
//                        }
//                    }
//
//                    in_scan.close();
//                    out_scan.close();
//                } catch (FileNotFoundException e) {
//                    System.out.println(e);
//                }
//            }
//        }
        String[][] temp = {
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
                {"o", "o", ".", ".", "o", ".", "o", "o", "."},
                {".", "o", ".", "o", ".", ".", ".", "o", "o"},
                {".", ".", ".", ".", ".", "o", ".", ".", "."},
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
        };
        int[] tempArray = game(temp);
        System.out.println(tempArray[0] + "," + tempArray[1]);



    }

}