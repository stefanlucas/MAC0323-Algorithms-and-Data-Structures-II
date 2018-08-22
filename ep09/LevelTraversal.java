/**************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilacao: javac-algs4 LevelTraversal.java
* Execucao: java-algs4 LevelTraversal
*
* Descricao do programa:
* Constroi uma arvore r com N inteiros, e imprime M subarvores
* da arvore r em ordem de nivel, da esquerda pra direita.
*
***************************************************************************/

import java.util.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LevelTraversal {
    private Node root;

    private class Node {
        private int key;
        private Node left, right;

        public Node (int key) {
            this.key = key;
        }
    }

    // Construtor da arvore
    public LevelTraversal () {    
    }

    // insere um elemento na arvore
    public void insert (int key) {
        root = insert (root, key);
    }

    private Node insert (Node x, int key) {
        if (x == null) return new Node (key);
        else if (key < x.key) 
            x.left = insert (x.left, key);
        else if (key > x.key) 
            x.right = insert (x.right, key);
        return x;
    }

    // retorna o no com a chave key, se nao existir
    // no com tal chave, retorna null
    public Node findNode (int key) {
        return findNode (root, key);
    }

    private Node findNode (Node x, int key) {
        if (x == null) 
            return null;
        else if (key < x.key) 
            return findNode (x.left, key);
        else if (key > x.key) 
            return findNode (x.right, key); 
        return x;
    }

    // imprime a subarvore com a chave key
    // em ordem de nivel da esquerda pra direta
    public void printLevel (int key) {
        Node x = findNode (key);
        int i;
        Queue<Node> queue = new LinkedList<Node> ();
        queue.add (x);
        while (!queue.isEmpty ()) {
            x = queue.remove ();
            if (x == null) continue;
            StdOut.print (x.key + " ");
            queue.add (x.left);
            queue.add (x.right);
        }
        StdOut.println ();
    }

    public static void main (String[] args) {
        int N, M, i, key; 
        LevelTraversal r = new LevelTraversal ();

        N = StdIn.readInt ();
        M = StdIn.readInt ();
        for (i = 0; i < N; i++) {
            key = StdIn.readInt ();
            r.insert (key);
        }
        for (i = 0; i < M; i++) {
            key = StdIn.readInt ();
            r.printLevel (key);
        }
    }
}