/******************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
* 
* Compilação: javac-algs4 CoreVertices.java 
* Execução: java-algs4 CoreVertices
*
* Programa que recebe dois inteiros n e m, e lê uma lista de m arestas
* com vértices indexados com inteiros de 0 a n-1, constrói
* um grafo direcionado e imprime os core vertices.
*
* Entrada:
* Na primeira linha, serão dados os inteiros n e m, seguidos de m linhas. 
* Cada linha tem dois inteiros a e b representando uma aresta que vai de a para b.  
*
* Saída:
* A saída é composta pelos core vertices, ou seja, vértices que alcançam todos
* os outros vértices do grafo direcionado
*
******************************************************************************/

import edu.princeton.cs.algs4.*;

public class CoreVertices {
    public static void main (String[] args) {
        int n = StdIn.readInt ();
        int m = StdIn.readInt ();

        /* Criando o Digraph*/
        Digraph g = new Digraph (n);
        for (int i = 0; i < m; i++) {
            int v = StdIn.readInt ();
            int w = StdIn.readInt ();
            g.addEdge(v, w);
        }

        /* Verificando se é possivel ter um core vertice */
        KosarajuSharirSCC sc = new KosarajuSharirSCC (g);
        boolean hasCore = false;
        if (sc.count () == 1) hasCore = true;
        else {
            int first = -1; // first é um elemento da primeira componente da kerneldag
            int last = -1;  // last é um elemento da última componente da kerneldag

            for (int i = 0; i < n; i++) {
                if (sc.id (i) == 0) first = i;
                else if (sc.id (i) == sc.count () - 1) last = i;
            }

            DirectedDFS dfs = new DirectedDFS (g, last);
            if (dfs.marked (first)) hasCore = true;
        }
           

        /* Encontrando os core vertices */
        if (hasCore) {
            for (int i = 0; i < n; i++) {
                if (sc.id(i) == sc.count() - 1)
                    StdOut.print(i + " ");
            }
            StdOut.println();
        }
        
    }
}