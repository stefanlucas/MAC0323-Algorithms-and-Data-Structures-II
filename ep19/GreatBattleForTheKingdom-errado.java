/******************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 GreatBattleForTheKingdom.java 
* Execucação: java-algs4 GreatBattleForTheKingdom
*
* Programa que verifica quais cidades são seguras para os herois se encontrarem,
* caso exista cidade(s) segura(s), imprime as cidades na saída padrão.
******************************************************************************/

import edu.princeton.cs.algs4.*;

    class BreadthFirst{
        boolean[] isSafe;
        boolean hasSafeCities;
        int[] initialPosition;
        int[][] marked;
        int K;        
        EdgeWeightedDigraph kingdom;

        // construtor
        public BreadthFirst (EdgeWeightedDigraph kingdom, int[] initialPosition, int K) {
            this.kingdom = kingdom;
            this.initialPosition = initialPosition;
            this.K = K;
            isSafe = new boolean[kingdom.V()];
            marked = new int[K+1][kingdom.V()];
            for (int i = 0; i < K + 1; i++) {
                for (int j = 0; j < kingdom.V(); j++) {
                    marked[i][j] = -1;    
                }
            }

            for (int i = 0; i < K + 1; i++) {
                bfs (initialPosition[i], i);
            }

            verifySafeCities ();
        }

        // mapeia o tempo que derterminada pessoa demora para chegar em cada cidade
        private void bfs (int p0, int person) {
            Queue<Integer> q = new Queue<Integer>();
            marked[person][p0] = 0;
            q.enqueue(p0);

            while (!q.isEmpty()) {
                int v = q.dequeue();
                for (DirectedEdge w : kingdom.adj(v)) {
                    if (marked[person][w.to()] == -1) {
                        marked[person][w.to()] = marked[person][v] + (int)w.weight();
                        q.enqueue(w.to());
                    }
                }
            }
        }

        // verifica quais cidades são seguras
        private void verifySafeCities () {
            hasSafeCities = true;
            for (int i = 0; i < kingdom.V(); i++) {
                isSafe[i] = true;
            }
            int count = 0;
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < kingdom.V(); j++) {
                    if ((marked[K][j] <= marked[i][j]) && (isSafe[j] == true)) {
                        isSafe[j] = false;
                        count++;
                    }
                }
            }

            if (count == kingdom.V()) hasSafeCities = false;
        } 

    }

public class GreatBattleForTheKingdom {

    // procura o nome de uma cidade em um vetor de nomes de cidade
    public static int search (String city, String[] cities, int n) {
        for (int i = 0; i < n; i++) {
            if (city.compareTo(cities[i]) == 0) return i;
        }
        return -1;
    }

    public static void main (String[] args) {
        int N = StdIn.readInt (); // número de cidades
        int M = StdIn.readInt (); // número de estradas
        int K = StdIn.readInt (); // número de herois
        String[] cities = new String[N]; // nome das cidades de 0..N-1
        int count = 0; // contador para indexar as cidades no grafo
        int[] initialPosition = new int[K+1]; // posição inicial de cada pessoa (heroi ou mago)

        EdgeWeightedDigraph kingdom = new EdgeWeightedDigraph(N);
        for (int i = 0; i < M; i++) {
            String cityA = StdIn.readString ();
            String cityB = StdIn.readString ();
            int T = StdIn.readInt();
            int indexA = search (cityA, cities, count);
            int indexB = search (cityB, cities, count);
            if (indexA == -1) {
                indexA = count;
                cities[indexA] = cityA;
                count++; 
            }
            if (indexB == -1) {
                indexB = count;
                cities[indexB] = cityB;
                count++; 
            }
            DirectedEdge e = new DirectedEdge(indexA, indexB, T);
            kingdom.addEdge (e);
        }

        for (int i = 0; i < K + 1; i++) {
            String city = StdIn.readString ();
            int index = search (city, cities, count);
            initialPosition[i] = index;
        }

        BreadthFirst  b = new  BreadthFirst(kingdom, initialPosition, K);
        if (b.hasSafeCities) {
            StdOut.println ("VICTORY AND HAPPY EVER AFTER");
            for (int i = 0; i < kingdom.V(); i++) {
                if (b.isSafe[i]) {
                    StdOut.println(cities[i]);
                }
            }
        }
        else {
            StdOut.println ("DEMISE OF THE KINGDOM");
        }
    }
}