/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *
 *  Compilação:  javac-algs4 FasterWordLadders.java
 *  Execução:    java-algs4 FasterWordLadders wordlist.txt
 *  Dependências: Graph.java IndexSET.java In.java BreadthFirstPaths.java
 *
 *  Cria uma word ladder de tamanho mínimo conectando duas palavras
 *
 ***********************************************************************/

import edu.princeton.cs.algs4.*;

public class FasterWordLadders {

    // retorna verdadeiro se duas palavras de mesmo tamanho diferem em uma palavra
    // ou se a palavra a é prefixo de b, e b tem uma letra a mais.
    public static boolean isNeighbor(String a, String b) {
        if(a.length()+1 == b.length()) {
            for (int cont = 0; cont < a.length(); cont++)
                if (a.charAt(cont) != b.charAt(cont)) return false;
            return true;
        }
        int differ = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) differ++;
            if (differ > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {

       /*******************************************************************
        *  Lê uma lista de strings, podem ter tamanhos diferentes
        *******************************************************************/
        In in = new In(args[0]);
        IndexSET<String> words = new IndexSET<String>();
        while (!in.isEmpty()) {
            String word = in.readString();
            words.add(word);
        }

        /*******************************************************************
        *  Construindo o grafo
        *******************************************************************/

        Graph G = new Graph(words.size());
        for (String word1 : words.keys()) {
            for (String word2 : words.keys()) {
                if ((word1.length() != word2.length()) && (word1.length()+1 != word2.length()))
                    continue; 
                if (word1.compareTo(word2) < 0 && isNeighbor(word1, word2)) {
                    G.addEdge(words.indexOf(word1), words.indexOf(word2));
                }
            }
        }

       /*******************************************************************
        *  Executando a busca em largura
        *******************************************************************/
        while (!StdIn.isEmpty()) {
            String from = StdIn.readString();
            String to   = StdIn.readString();
            if (!words.contains(from)) throw new RuntimeException(from + " is not in word list");
            if (!words.contains(to))   throw new RuntimeException(to   + " is not in word list");

            BreadthFirstPaths bfs = new BreadthFirstPaths(G, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {
                StdOut.println(bfs.distTo(words.indexOf(to)));
                for (int v : bfs.pathTo(words.indexOf(to))) {
                    StdOut.println(words.keyOf(v));
                }
            }
            else StdOut.println("NOT CONNECTED");
            StdOut.println();
        }
    }
}
