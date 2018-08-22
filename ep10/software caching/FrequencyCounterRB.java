/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *
 *  Compilação:  javac FrequencyCounterRB.java
 *  Execução:    java FrequencyCounterRB L < input.txt
 *  Dependências: StdIn.java StdOut.java RedBlackBST.java
 *  Arquivos de dados:   http://algs4.cs.princeton.edu/31elementary/tnyTale.txt
 *                       http://algs4.cs.princeton.edu/31elementary/tale.txt
 *                       http://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 *                       http://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 *                       http://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 *
 *  Lê uma lista de palavras da entrada padrão e imprime a palavra mais 
 *  frequente tal que seu tamanho seja maior que o do threshold L.
 *
 *  % java FrequencyCounterRB 1 < tinyTale.txt
 *  it 10
 *
 *  % java FrequencyCounterRB 8 < tale.txt
 *  business 122
 *
 *  % java FrequencyCounterRB 10 < leipzig1M.txt
 *  government 24763
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounterRB {

    // Não instancia
    private FrequencyCounterRB() { }

    /**
     * Lê um inteiro da linha de comando, uma sequência de palavras
     * da entrada padrão e imprime a palavra (cujo tamanho é maior
     * que L) que ocorre mais frequentemente na entrada padrão.
     * Também imprime o numero de palavras cujo tamanho excede L
     * e imprime o número de palavras distintas dos mesmos.
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int L = Integer.parseInt(args[0]);
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        double time, start = System.currentTimeMillis(), elapsed;
          
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < L) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        elapsed = System.currentTimeMillis();
        time = (elapsed - start)/1000.0;
        StdOut.println(max + " " + st.get(max));
    }
}
