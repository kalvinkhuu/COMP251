import java.util.Scanner;
import java.util.*;

public class A2_Q2 {

    public static int weight(int[] plates) {
        return 0;
        



    }

    static void subsetSums(int[] arr, int l, int r, int sum)
    {

        // Print current subset
        if (l > r) {
            System.out.print(sum + " ");
            return;
        }

        // Subset including arr[l]
        subsetSums(arr, l + 1, r, sum + arr[l]);

        // Subset excluding arr[l]
        subsetSums(arr, l + 1, r, sum);
    }

    // Driver code
    public static void main(String[] args)
    {
        int[] arr = { 5, 4, 3 };
        int n = arr.length;

        subsetSums(arr, 0, n - 1, 0);
    }

}
