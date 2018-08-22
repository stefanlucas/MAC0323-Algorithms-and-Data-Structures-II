/***************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 BooleanMatrix3D.java
* Execução: java-algs4 BooleanMatrix3D N
*
* O que o programa faz?
* Programa que recebe um número N pela linha de comando, 
* e plota a probabilidade de uma matriz de cubos NxNxN percolar em
* função da probabilidade de um cubo da matriz estar aberto.
* 
****************************************************************/
public class BooleanMatrix3D {

	// retorna uma matrix booleana aleatoria NxNxN, onde
    // cada entrada é verdadeira com probabilidade p
    public static boolean[][][] random (int N, double p) {
        boolean[][][] a = new boolean[N][N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            	for (int k = 0; k < N; k++)
                	a[i][j][k] = StdRandom.bernoulli (p);
        return a;
    }

    // faz M experimentos e retorna a fração que percola
    public static double eval (int N, double p, int M) {
        int count = 0;
        for (int k = 0; k < M; k++) {
            boolean[][][] open = BooleanMatrix3D.random (N, p);
            if (Percolation3D.percolates (open))
                count++;
        }
        return (double) count / M;
    }

    // plotagem recursiva da curva
    public static void curve (int N, double x0, double y0, double x1, double y1) {
        double gap = .01;
        double err = .0025;
        int M = 10000;
        double xm = (x0 + x1) / 2;
        double ym = (y0 + y1) / 2;
        double fxm = BooleanMatrix3D.eval (N, xm, M);
        if (x1 - x0 < gap || Math.abs (ym - fxm) < err) {
            StdDraw.line (x0, y0, x1, y1);
            return;
        }
        curve (N, x0, y0, xm, fxm);
        StdDraw.filledCircle (xm, fxm, .005);
        curve (N, xm, fxm, x1, y1);
    }

    public static void main (String[] args) {
    	int N = Integer.parseInt (args[0]);
 
    	BooleanMatrix3D.curve (N, 0.0, 0.0, 1.0, 1.0);
    }
}