import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class A2_Q2 {

    public static int weight(int[] plates) {
        return dpWeight(plates, 1000);

    }

    private static int dpWeight(int[] plates, int sum){

        int[][] dpTable = new int[plates.length+1][sum*2+1];

        // Find all possible weights combinations
        for (int i = 0; i < plates.length+1; i++) {
            for (int j = 0; j < sum*2+1; j++) {
                if (i == 0 || j == 0){
                    dpTable[i][j] = 0;
                }
                else if (plates[i-1] <= j){
                    int pick = plates[i-1]+dpTable[i-1][j-plates[i-1]];
                    int leave = dpTable[i-1][j];
                    dpTable[i][j] = closestToSum(pick, leave, sum);
                }
                else{
                    dpTable[i][j] = dpTable[i-1][j];
                }
            }
        }

        // Find the closest value out of all the weights
        int closestValue = 0;
        for (int i = 0; i < plates.length+1; i++) {
            for (int j = 0; j < sum*2+1; j++) {
                closestValue = closestToSum(closestValue, dpTable[i][j], sum);
            }
        }
        return closestValue;

    }


    // Returns the biggest number out of two numbers
    private static int maxValue (int first, int second){
        if (first > second){
            return first;
        }
        return second;
    }

    // Returns the absolute value of an integer
    private static int absoluteValue (int number){
        if (number < 0){
            return -number;
        }
        return number;
    }

    // Returns the closest value of the sum (priority to the bigger number)
    private static int closestToSum (int first, int second, int sum){
        int distanceFirst = absoluteValue(sum-first);
        int distanceSecond = absoluteValue(sum-second);

        if (distanceFirst == distanceSecond){
            return maxValue(first, second);
        }
        else if (distanceFirst < distanceSecond){
            return first;
        }
        return second;
    }



    final static String TEST_FOLDER = "Open_Tests/Q2";
    public static void main(String[] args) {
        File f = new File(TEST_FOLDER);
        System.out.println(f);
        System.out.println(f.list());
        for (String name : f.list()) {
            if (name.endsWith(".in")) {
                try {
                    File in = new File(TEST_FOLDER + "/" + name);
                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
                    Scanner in_scan = new Scanner(in);
                    Scanner out_scan = new Scanner(out);
                    int n = in_scan.nextInt();
                    int[] weights = new int[n];
                    for (int i = 0; i < n; i++) {
                        weights[i] = in_scan.nextInt();
                    }
                    int got = weight(weights);
                    int expected = out_scan.nextInt();
                    if (got != expected) {
                        System.out.printf("Expected %d but got %d\n", expected, got);
                    } else {
                        System.out.printf("Passed test %s\n", name);
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
