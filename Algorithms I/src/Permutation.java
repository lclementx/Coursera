import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while(!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = randomizedQueue.iterator();
        for(int i = 0; i < k; i++) {
            if(iterator.hasNext()) {
                StdOut.println(iterator.next());
            }
        }
    }
}