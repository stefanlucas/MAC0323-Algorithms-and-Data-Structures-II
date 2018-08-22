/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *
 *  Compilação:  javac-algs4 SeparateChainingHashST.java
 *  Execução:    java-algs4 SeparateChainingHashST K
 *  
 *  Uma tabela de símbolos implementada com uma separate-chaining hash table.
 *  Na main é executado um teste simples dessa estrutura, recebendo como parâmetro
 *  um inteiro K, que é o tamanho médio limite para as chains da hash table.
 * 
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

/**
 *  The <tt>SeparateChainingHashST</tt> class represents a symbol table of generic
 *  key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be <tt>null</tt>&mdash;setting the
 *  value associated with a key to <tt>null</tt> is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a separate chaining hash table. It requires that
 *  the key type overrides the <tt>equals()</tt> and <tt>hashCode()</tt> methods.
 *  The expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 *  operation is constant, subject to the uniform hashing assumption.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For other implementations, see {@link ST}, {@link BinarySearchST},
 *  {@link SequentialSearchST}, {@link BST}, {@link RedBlackBST}, and
 *  {@link LinearProbingHashST},
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private static final int[] primes = {2, 3, 5,
        7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
        32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
        8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
        536870909, 1073741789, 2147483647
     };

    private int N;                                // number of key-value pairs
    private int M;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables
    private int K;                                // Limite para o tamanho médio das chains da tabela
    private int logM;                             // guarda o valor de logM
    
    /**
     * Initializes an empty symbol table.
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
        K = 10;
    } 

    /**
     * Initializes an empty symbol table with <tt>M</tt> chains.
     * @param M the initial number of chains
     */
    public SeparateChainingHashST(int M) {
        this.M = M;
        K = 10;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    /* Construtor que inicializa uma hash table de tamanho M, com um limite de tamanho médio das chains K*/
    public SeparateChainingHashST(int M, int K) {
        this.M = M;
        if (K <= 0)
            this.K = 10;
        else
            this.K = K;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        logM = log (M);
        
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    } 

    /* Retorna um vetor v de inteiros de tamanho M, onde cada posição tem o tamanho da chain da tabela*/
    public int[] getSizes() {
        int[] size = new int[this.M];
        int i;
        for (i = 0; i < this.M; i++) {
            size[i] = st[i].size ();    
        }

        return size;
    }

    public int log (int n) {
        int pow = 1;
        int i;
        for (i = 0; pow < n; i++, pow *= 2);
        return i;
    }

    // resize the hash table to have the given number of chains b rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains, K);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.logM = temp.logM;
        this.st = temp.st;
    }

    // hash value between 0 and M-1
    private int hash(Key key) {
        int t  = (key.hashCode() & 0x7fffffff) % M;
        if (this.logM < 26) t = t % primes[this.logM+5];
        return t % M;
    } 

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return N;
    } 

    /**
     * Returns true if this symbol table is empty.
     *
     * @return <tt>true</tt> if this symbol table is empty;
     *         <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt>;
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    } 

    /**
     * Returns the value associated with the specified key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with <tt>key</tt> in the symbol table;
     *         <tt>null</tt> if no such value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    } 

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is <tt>null</tt>.
     *
     * @param  key the key
     * @param  val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= K
        if (N >= K*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    } 

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    } 

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    } 


    /**
     * Unit tests the <tt>SeparateChainingHashST</tt> data type.
     */
    public static void main(String[] args) { 
        int K = Integer.parseInt(args[0]);
        if (K <= 0) {
            StdOut.println("Valor invalido de K, mudando pra 10");
            K = 10;
        }
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>(4, K);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) 
            StdOut.println(s + " " + st.get(s));

        int[] chainSize = st.getSizes();
        int M = chainSize.length;
        int N = st.size();
        StdOut.println("N = " + N);
        StdOut.println("M = " + M);
        StdOut.println("K = " + K);
        StdOut.println("M*K = " + M*K);
        StdOut.println("Chain         Tamanho");
        for (int i = 0; i < M; i++) 
            StdOut.printf("%5d%16d\n", i,chainSize[i]); 

    }

}

/******************************************************************************
 *  Copyright 2002-2015, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
