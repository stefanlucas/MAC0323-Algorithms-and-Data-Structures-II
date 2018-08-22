/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Numero Usp: 8531612
 ******************************************************************************/
public class Transition {
    public static void main (String[] args) {
        int N = StdIn.readInt ();           
        int A = StdIn.readInt ();
        int H = StdIn.readInt ();
        int[][] counts = new int[N][N];    
        int[] outDegree = new int[N];      

        while (!StdIn.isEmpty ())  {
            int i = StdIn.readInt (); 
            int j = StdIn.readInt (); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println (N + " " + N);
        StdOut.println (A + " " + H); 
        for (int i = 0; i < N; i++)  {
            for (int j = 0; j < N; j++) {
                double p = 0;
                if (outDegree[i] != 0)
                    p = .90*counts[i][j] / outDegree[i];
                p += 0.1 / N;
                StdOut.printf ("%7.5f ", p); 
            } 
            StdOut.println (); 
        } 
    } 
} 

