import java.math.BigInteger;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class A2_Q4 {

    public static String mod_fibonacci(int N, BigInteger K) {
        // Need to store each element of the fib
        BigInteger[] fib = new BigInteger[N+1];
        for (int i = 0; i <= N; i++){
            if (i == 0){
                fib[i] = BigInteger.valueOf(0);
            }
            else if (i == 1){
                fib[i] = BigInteger.valueOf(1);
            }
            else{
                fib[i] = fib[i-2].add(fib[i-1]);
            }
        }
        return recursiveFib(fib, K, fib.length-1);
    }

    private static String recursiveFib(BigInteger[] fib, BigInteger K, int index){

        if (index <= 2){
            if (index == 1){
                return "X";
            }
            else{
                return "Y";
            }
        }

        if (K.compareTo(fib[index-2]) == -1 || K.compareTo(fib[index-2]) == 0){
            return recursiveFib(fib, K, index-2);
        }
        else {
            return recursiveFib(fib, K.subtract(fib[index-2]), index-1);
        }

    }



    public static void main(String[] args) {

//        System.out.println(mod_fibonacci(6, BigInteger.valueOf(3)));
    }







    @Test
    public void smallTest0() {
        assertEquals("X", A2_Q4.mod_fibonacci(7, BigInteger.valueOf(7)));
    }

    @Test
    public void smallTest1() {
        assertEquals("Y", A2_Q4.mod_fibonacci(3, BigInteger.valueOf(2)));
    }

    @Test
    public void bigTest0() {
        assertEquals("X", A2_Q4.mod_fibonacci(7777, new BigInteger("474150155627499133")));
    }

    @Test
    public void minimalTest0() {
        assertEquals("X", A2_Q4.mod_fibonacci(1, new BigInteger("1")));
    }


}