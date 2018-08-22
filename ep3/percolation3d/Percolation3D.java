/***************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 BooleanMatrix3D.java
* Execução: java-algs4 BooleanMatrix3D
****************************************************************/
public class Percolation3D {

    // dada uma matrix NxNxN de cubos abertos, retorna uma
    // matriz NxNxN de cubos alcançaveis pelo topo
	public static boolean[][][] flow (boolean[][][] open) {
        int N = open.length;
        boolean[][][] full = new boolean[N][N][N];
        for (int j = 0; j < N; j++) {
        	for (int k = 0; k < N; k++) {
            	flow (open, full, 0, j, k);
        	}
        }
        return full;
    }

    // determina o conjunto de sites cheios usando busca em profundidade
    public static void flow (boolean[][][] open, boolean[][][] full, int i, int j, int k) {
        int N = open.length;

        if (i < 0 || i >= N) return;    
        if (j < 0 || j >= N) return;   
        if (k < 0 || k >= N) return;   
        if (!open[i][j][k]) return;        
        if (full[i][j][k]) return;         

        full[i][j][k] = true;

        flow(open, full, i+1, j, k);  
        flow(open, full, i, j+1, k);   
        flow(open, full, i, j-1, k);   
        flow(open, full, i-1, j, k);   
        flow(open, full, i, j, k-1); 
        flow(open, full, i, j, k+1);
    }

    // determina se o sistema percola
    public static boolean percolates (boolean[][][] open) {
        int N = open.length;
        boolean[][][] full = flow (open);
        for (int j = 0; j < N; j++) {
        	for (int k = 0; k < N; k++)
            	if (full[N-1][j][k]) return true;
        }
        return false;
    }

}