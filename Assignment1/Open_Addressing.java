import java.io.*;
import java.util.*;

public class Open_Addressing {
    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    protected Open_Addressing(int w, int seed, int A) {

        this.w = w;
        this.r = (int) (w-1)/2 +1;
        this.m = power2(r);
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        }

    }

    /** Calculate 2^w*/
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        if(seed>=0){
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max-min-1);
        return i+min+1;
    }
    /**Implements the hash function g(k)*/
    public int probe(int key, int i) {
        // Using multiplication method as initial hash
        int hashValue = ((A*key) % power2(w)) >> (w-r);
        int value = (hashValue + i) % power2(r);
        return value;

    }


    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int insertKey(int key){
        int counterProb = 0;
        int indexArray = probe(key,counterProb);
        //Adds to the counter for each collisions
        while (Table[indexArray] > -1){
            counterProb++;
            indexArray = probe(key,counterProb);
        }
        Table[indexArray] = key;
        return counterProb;
    }

    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public int removeKey(int key){
        int counterProb = 0;
        int indexArray = probe(key,counterProb);
        // Checks for collisions anc returns value if it doesn't exist
        while (Table[indexArray] != key){
            if (Table[indexArray] == -1){
                return counterProb+1;
            }
            counterProb++;
            indexArray = probe(key,counterProb);
            if (counterProb == m ) {
                return counterProb;
            }
        }
        Table[indexArray] = -2;

        return counterProb;
    }
}