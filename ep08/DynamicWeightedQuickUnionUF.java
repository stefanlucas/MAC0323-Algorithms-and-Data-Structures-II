/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *  Compilation:  javac-algs4 DynamicWeightedQuickUnionUF.java
 *  Execution:  java-algs4 DynamicWeightedQuickUnionUF
 *
 *  Weighted quick-union com compressao de caminho e alocacacao dinamica.
 *
 ******************************************************************************/

public class DynamicWeightedQuickUnionUF {
    private int[] parent;  // parent[i] = pai de i
    private int[] size;    // size[i] = numero de sitos na arvore com raiz i
    private int N;

    // Construtor da Weighted quick-union
    public DynamicWeightedQuickUnionUF(int N) {
        this.N = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // retorna o tamanho da Weighted quick-union
    public int getN () {
    	return N;
    }

    // redimensiona a Weighted quick-union e retorna 
    // seu novo tamanho
    public int newSite (int newN) {
    	int[] tempParent = new int[newN];
    	int[] tempSize = new int[newN];
    	for (int i = 0; i < N; i++) {
    		tempParent[i] = parent[i];
    		tempSize[i] = size[i]; 
    	}
    	for (int i = N; i < newN; i++) {
    		tempParent[i] = i;
    		tempSize[i] = 1;
    	}
    	parent = tempParent;
    	size = tempSize;
    	this.N = newN;
    	return newN;
    }

    // Retorna o indentificador de componente da componente contendo o sitio p
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

   // Retorna verdadeiro se os dois sitios estao na mesma componente
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // verifica se p eh um indice valido
    private void validate(int p) {
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));  
        }
    }  

    // Junta a componente contendo o sitio p com a componente contendo o sitio q
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public static void main(String[] args) {
        DynamicWeightedQuickUnionUF uf = new DynamicWeightedQuickUnionUF(100);
    	int max;
    	int component;

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            int identifier;
            if (p > q) max = p;
            else max = q;
            if (max >= uf.getN ()) {
            	identifier = uf.newSite (4*max);
            }
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }

        for (int i = 0; i < uf.getN (); i++) {
        	if ((component = uf.find (i)) != i)
        		StdOut.println (i + " " + component);
        }
    }

}