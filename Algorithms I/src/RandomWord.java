import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        double count = 1;
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (StdRandom.bernoulli(1/count)) {
                champion = input;
            }
            count += 1.0;
        }
        StdOut.println(champion);
    }
}
