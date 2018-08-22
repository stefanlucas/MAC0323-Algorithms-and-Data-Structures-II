/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *
 *  Compilação:  javac-algs4 UniqueLRK.java
 *  Execução:
 *  Se L != 0    
 *  java-algs4 UniqueLRK L < input.txt
 *  Se L = 0 
 *  java-algs4 UniqueLRK 0 < input.txt
 *  ou 
 *  java-algs4 UniqueLRK 0 N
 *  
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.lang.Math;

public class UniqueLRK {
    public static void main (String[] args) {
        int L = Integer.parseInt (args[0]);
        int N = 0;
        double time, start = System.currentTimeMillis (), elapsed;

        if (L == 0) {
            String txt;
            int maiorL = -1;

            if (args.length == 1) {
                txt = StdIn.readString ();
                N = txt.length ();
            }
            else {
                N = Integer.parseInt (args[1]);
                StringBuilder sb = new StringBuilder ();
                for (int i = 0; i < N; i++)
                    sb.append (StdRandom.uniform (10));
                txt = sb.toString ();
            }
            maiorL = 0;
            for (L = 1; Math.pow (10, L) < N; L++) {
                RabinKarp r = new RabinKarp (L);
                if (r.search (txt) == Math.pow (10, L)) maiorL = L;
            }
            StdOut.println (maiorL);
        }
        else {
            String txt = StdIn.readLine ();
            RabinKarp r = new RabinKarp (L);
            StdOut.println (r.search (txt));                      
        }

        elapsed = System.currentTimeMillis ();
        time = (elapsed - start)/1000.0;
        StdOut.println(time);
    }
}