import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int[] grid;
    private final WeightedQuickUnionUF uf;

    private int openSites;

    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n has to be larger than 0");
        }
        this.openSites = 0;
        this.n = n;
        int length = (n * n) + 2;
        this.grid = new int[length];
        for (int i = 0; i < grid.length; i++) {
            this.grid[i] = 0;
        }
        this.uf = new WeightedQuickUnionUF(length);
        //let node 0 and node n-1 be the virtual nodes.
        for (int i = 1; i <= n; i++) {
            uf.union(0, i); //first row
            uf.union(length - 1 - i,length - 1); //last row
        }
    }

    private int getNode(int row, int col) {
        return ((row - 1) * n + (col - 1)) + 1;
    }

    private boolean isInvalidCoord(int value) {
        return value < 1 || value > this.n;
    }

    private void checkOutOfBounds(int row, int col) {
        if (this.isInvalidCoord(row) || this.isInvalidCoord(col)) {
            throw new IllegalArgumentException("Illegal row or column value - out of bounds.");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.checkOutOfBounds(row, col);
        if ( isOpen(row,col) ){
            return;
        }
        this.grid[getNode(row, col)] = 1;
        this.openSites += 1;
        //connect neighbours
        //ABOVE
        if (!isInvalidCoord(row - 1)) {
            if (isOpen(row - 1, col)) this.uf.union(getNode(row - 1, col),getNode(row, col));
        }

        //RIGHT
        if(!isInvalidCoord(col + 1)){
            if (isOpen(row, col + 1)) this.uf.union(getNode(row,col+1),getNode(row, col));
        }
        //BELOW
        if(!isInvalidCoord(row + 1)){
            if (isOpen(row + 1, col)) this.uf.union(getNode(row + 1,col),getNode(row, col));
        }
        //LEFT
        if(!isInvalidCoord(col - 1)){
            if (isOpen(row, col - 1)) this.uf.union(getNode(row,col - 1),getNode(row,col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.checkOutOfBounds(row,col);
        return this.grid[this.getNode(row,col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        this.checkOutOfBounds(row,col);
        return (this.uf.find(getNode(row,col)) == this.uf.find(0)) & this.isOpen(row,col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.uf.find((this.n * this.n) + 1) == this.uf.find(0);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 10;
        Percolation percolation = new Percolation(n);
        while(!percolation.percolates()) {
            int row = StdRandom.uniformInt(n) + 1;
            int col = StdRandom.uniformInt(n) + 1;
            if(!percolation.isOpen(row,col)) {
                percolation.open(row,col);
            }
        }
        System.out.println("Open Sites: " + percolation.openSites);
    }
}