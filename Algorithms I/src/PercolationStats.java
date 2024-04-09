import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] trialResults;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        trialResults = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            trialResults[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.trialResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.trialResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(trialResults.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(trialResults.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        if (n < 1) {
            throw new IllegalArgumentException("n has to be larger than 0");
        }

        if (trials < 1) {
            throw new IllegalArgumentException("trials has to be larger than 0");
        }

        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.printf("mean = %f\n", percolationStats.mean());
        StdOut.printf("stddev = %f\n", percolationStats.stddev());
//        StdOut.printf("   confidenceLow %10.3f\n", percolationStats.confidenceLo());
//        StdOut.printf("    confidenceHi %10.3f\n", percolationStats.confidenceHi());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
//        StdOut.printf("     probability %10.3f\n", percolationStats.mean() / (n * n));
    }

}

