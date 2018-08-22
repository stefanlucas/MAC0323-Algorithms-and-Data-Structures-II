/******************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 Pontes.java
* Execução: java-algs4 Pontes.java
*
* Programa que encontra todas as pontes de um grafo com n vértices e m arestas.
*
* Entrada: Dois inteiros n e m, seguidos de m pares de inteiros. Sendo n o 
* número de vértices e m o número de arestas do grafo, e cada par de inteiros
* a e b indica que os vértices a e b são conectados por uma aresta.
* Saída: Pares de inteiros, representando as pontes do grafo.
*
* Exemplo:
*
* Entrada:
* 4 4
* 0 1
* 0 2
* 1 2
* 2 3
* 
* Saída:
* 2 3
*
******************************************************************************/


import edu.princeton.cs.algs4.*;

public class Pontes {
    private int bridges;      // número de pontes
    private int cnt;          // contador
    private int[] pre;        // pre[v] = ordem na qual dfs examina v
    private int[] low;        // low[v] = menor preordem de qualquer vértice conectado a v

    public Pontes(Graph G) {
        low = new int[G.V()];
        pre = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            low[v] = -1;
        for (int v = 0; v < G.V(); v++)
            pre[v] = -1;
        
        for (int v = 0; v < G.V(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

    public int components() { return bridges + 1; }

    private void dfs(Graph G, int u, int v) {
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : G.adj(v)) {
            if (pre[w] == -1) {
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    StdOut.println(v + " " + w);
                    bridges++;
                }
            }

            // atualiza o número low - ignora o reverso da aresta levando à v
            else if (w != u)
                low[v] = Math.min(low[v], pre[w]);
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int m = StdIn.readInt();
        Graph g = new Graph (n);
        for (int i = 0; i < m; i++) {
            int u = StdIn.readInt();
            int v = StdIn.readInt();
            g.addEdge (u, v);
        }

        Pontes bridge = new Pontes (g);
    }
}