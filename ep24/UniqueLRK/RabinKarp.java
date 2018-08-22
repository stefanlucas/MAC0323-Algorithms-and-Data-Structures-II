/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *
 *  Obs: Fiz algumas modificações na classe para que ela pudesse realizar
 *  a operação de encontrar a quantidade de substrings de tamanho M de forma
 *  mais eficiente.
 ******************************************************************************/

import edu.princeton.cs.algs4.SET;
import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private SET<String> pat;      // conjunto de padrões de tamanho M
    private SET<Long> patHash;    // conjunto de hashes dos padrões
    private int M;           // pattern length
    private long Q;          // a large prime, small enough to avoid long overflow
    private int R;           // radix
    private long RM;         // R^(M-1) % Q

    public RabinKarp(int M) {
        this.pat = new SET<String> ();      // inicialmente o conjunto de padrões é vazio
        this.patHash = new SET<Long> ();    // inicialmente o conjunto de hash de padrões é vazio
        R = 256;
        this.M = M;
        Q = longRandomPrime();

        // precompute R^(M-1) % Q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= M-1; i++)
            RM = (R * RM) % Q;
    } 

    // Compute hash for key[0..M-1]. 
    private long hash(String key, int M) { 
        long h = 0; 
        for (int j = 0; j < M; j++) 
            h = (R * h + key.charAt(j)) % Q; 
        return h; 
    } 
 
    public int search(String txt) {
        int N = txt.length(); 
        if (N < M) return N;
        long txtHash = hash(txt, M); 

        // inserindo a primeira substring de tamanho
        // M e seu respectivo hash nos conjuntos
        String subs = txt.substring(0, M);
        pat.add (subs);
        patHash.add (txtHash);

        // check for hash match; if hash match, check for exact match
        for (int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; 
            txtHash = (txtHash*R + txt.charAt(i)) % Q; 

            int offset = i - M + 1;
            subs = txt.substring(offset, offset + M);

            // quando o hash da substring está no conjunto de hashes 
            // deve-se adicionar a substring no conjunto de substrings
            // se não estiver lá.
            if (patHash.contains(txtHash)) { 
                if (!pat.contains(subs)) pat.add(subs);
            }
            // caso não exista hash da substring, inserimos
            // a substring e o seu hash nos conjuntos
            else {
                patHash.add (txtHash);
                pat.add (subs);
            }
        }

        // retornando a quantidade de padrões de tamanho igual a M
        return pat.size ();
    }


    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
