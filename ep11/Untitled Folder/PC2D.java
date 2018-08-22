/**************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
* 
**************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class PC2D {
    double x;
    double y;
    int index;

    public PC2D(double x, double y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public double distanceTo(PC2D q) {
        double dx = this.x - q.x;
        double dy = this.y - q.y;
        double d = Math.sqrt(dx*dx + dy*dy);
        return d; 
    }

    public boolean equalTo(PC2D q) {
        return ((this.x == q.x) && (this.y == q.y));
    }
    
    public static void main(String[] args) {
        int n = StdIn.readInt ();
        int N;
        boolean duplicated;
        double d = StdIn.readDouble ();
        int G = (int) (Math.ceil(1.0) / d);
        double x, y;
        double time, start = System.currentTimeMillis(), elapsed;
        PC2D p = null;
        Queue<PC2D>[][] grid = (Queue<PC2D>[][])  new Queue[G+2][G+2];
        Queue<Integer>[] adjList = (Queue<Integer>[]) new Queue[n];
        
        for (int i = 0; i < n; i++) 
            adjList[i] = new Queue<Integer>();

        for (int i = 0; i <= G + 1 ; i++) 
            for (int j = 0; j <= G + 1; j++)
                grid[i][j] = new Queue<PC2D>();

        /* Construindo o grid, juntamente com a lista de adjacentes */
        N = 0;
        for (int cont = 0; cont < n; cont++) {
            duplicated = false;
            x = StdIn.readDouble();
            y = StdIn.readDouble();
            p = new PC2D(x, y, N);
            int row = 1 + (int) (p.x * G);
            int col = 1 + (int) (p.y * G);

            for (PC2D q: grid[row][col]) {
                if (p.equalTo(q)) {
                    duplicated = true;
                }
            }

            if (duplicated == false) { 
                for (int i = row-1; i <= row+1; i++) {
                    for (int j = col-1; j <= col+1; j++) {
                        for (PC2D w : grid[i][j]) {
                            if ((p.equalTo(w) == false) && p.distanceTo(w) <= d) {
                                adjList[N].enqueue(w.index);
                            }
                        }
                    }
                }
                grid[row][col].enqueue(p);
                N++;
            }
        }

        /* Usando um grafo para calcular a quantidade de componentes da lista 
           de pontos adjacentes */
        Graph graph = new Graph (adjList, N);
        int dconex = graph.CC ();

        elapsed = System.currentTimeMillis();
        time = (elapsed - start)/1000.0;

        /* Caso a quantidade de componentes seja <= 1, os pontos são d-conexos*/
        if (dconex <= 1)
            StdOut.println ("Sim");
        else 
            StdOut.println ("Nao");
    }
}