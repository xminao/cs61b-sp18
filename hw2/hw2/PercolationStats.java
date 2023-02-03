package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fraction;
    /** perform T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        fraction = new double[T];
        Percolation perc;
        for (int i = 0; i < T; i += 1) {
            perc = pf.make(N);
            while (!perc.percolates()) {
                int randomRow = StdRandom.uniform(0, N);
                int randomCol = StdRandom.uniform(0, N);
                perc.open(randomRow, randomCol);
            }
            fraction[i] = (double) perc.numberOfOpenSites() / (N * N);
        }
    }

    /** sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(fraction);
    }

    /** sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(fraction);
    }

    /** low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        double right = 1.96 * stddev() / Math.sqrt(fraction.length);
        return mean() - right;
    }

    /** high endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        double right = 1.96 * stddev() / Math.sqrt(fraction.length);
        return mean() + right;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 10, new PercolationFactory());
        System.out.println(ps.mean());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
    }
}
