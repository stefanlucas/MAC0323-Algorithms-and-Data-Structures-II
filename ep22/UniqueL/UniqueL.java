/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *
 *  Compilação:  javac-algs4 UniqueL.java
 *  Execução:
 *  Se L != 0    
 *  java-algs4 UniqueL L < input.txt
 *  Se L = 0 
 *  java-algs4 UniqueL 0 < input.txt
 *  ou 
 *  java-algs4 UniqueL 0 N
 *  
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.lang.Math;

public class UniqueL {
    public static void main (String[] args) {
        int L = Integer.parseInt (args[0]);
        int N = 0;
        TST<Integer> st = new TST<Integer> ();
        double time, start = System.currentTimeMillis (), elapsed;
        
        if (L == 0) {
            String s;
            int maiorL = -1;

            if (args.length == 1) {
                s = StdIn.readString ();
                N = s.length ();
            }
            else {
                N = Integer.parseInt (args[1]);
                StringBuilder sb = new StringBuilder ();
                for (int i = 0; i < N; i++)
                    sb.append (StdRandom.uniform (10));
                s = sb.toString ();
            }

            for (L = 1; (int)Math.pow(10, L) <= N; L++) {
                for (int i = 0; i <= N - L; i++) 
                    st.put (s.substring (i, i + L), i);
                if (st.size () == (int)Math.pow (10, L)) maiorL = L;
                st = new TST<Integer> ();
            }
            StdOut.println (maiorL);
        }
        else {
            String s = StdIn.readString ();
            N = s.length ();
            for (int i = 0; i <= N - L; i++) 
                st.put (s.substring (i, i + L), i);
            StdOut.println(st.size ());
        }

        elapsed = System.currentTimeMillis ();
        time = (elapsed - start)/1000.0;
    }
}