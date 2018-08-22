/******************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 SAT.java 
* Execucação: java-algs4 SAT
*
* Programa que resolve o problema de 2-Satisfability e dá uma resposta caso
* a expressão tenha uma solução.
*
* Obs: Estou assumindo que todos os 2n literais serão usados
******************************************************************************/

import edu.princeton.cs.algs4.*;

public class SAT {
    public static void main (String[] args) {
        int n = StdIn.readInt ();
        int m = StdIn.readInt ();
        /*boolean validVertex[2*n];*/

        /* Criando o digraph*/
        Digraph G = new Digraph (2*n);
        for (int i = 0; i < m; i++) {
            int x = StdIn.readInt ();
            int y = StdIn.readInt ();
            int notx = 0, noty = 0;
            if (x > 0) {
                x--;
                notx = x + n;
            }
            else {
                x = -x - 1 + n;
                notx = x - n;
            } 
            if (y > 0){
                y--;
                noty = y + n;
            }
            else {
                y = -y - 1 + n;
                noty = y - n;  
            }

            G.addEdge(notx, y);
            G.addEdge(noty, x);
        }

        /* verificando se a expressão pode ser satisfeita*/
        KosarajuSharirSCC sc = new KosarajuSharirSCC (G);
        boolean isSAT = true;
        for (int i = 0; i < n; i++) {
            if (sc.stronglyConnected (i, n+i)) {
                isSAT = false;
                break;
            }
        }

        if (isSAT) {
            StdOut.println ("VERDADE");

            /* Encontrando uma valoração para os literais*/
            boolean[][] component = new boolean[sc.count()][2*n]; 
            int[] componentValue = new int[sc.count()];
            for (int i = 0; i < sc.count(); i++) {
                componentValue[i] = -1;
            }
            for (int i = 0; i < 2*n; i++) {
                component[sc.id(i)][i] = true;
            }
            for (int i = 0; i < sc.count(); i++) {
                if (componentValue[i] == -1) {
                    componentValue[i] = 1;
                    for (int j = 0; j < n; j++) {
                        if(component[i][j]) {
                            if (componentValue[sc.id (n+j)] == 1) /*StdOut.println("erro"); para testar*/
                            componentValue[sc.id (n+j)] = 0;
                        }     
                    }
                    for (int j = n; j < 2*n; j++) {
                        if(component[i][j]) {
                            if (componentValue[sc.id (j-n)] == 1) /*StdOut.println("erro"); para testar*/
                            componentValue[sc.id(j-n)] = 0;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                StdOut.print (componentValue[sc.id (i)] + " ");
            }
            StdOut.println ();
        } 
        else {
            StdOut.println ("MENTIRA");
        }

    }
}