/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Numero Usp: 8531612
 ******************************************************************************/
public class RandomSurfer {
    public static void main (String[] args) {
        int T = Integer.parseInt(args[0]);    // numero de de movimentos
        int M = StdIn.readInt ();              
        int N = StdIn.readInt ();              
        int A = StdIn.readInt ();
        int H = StdIn.readInt ();
        if (M != N) throw new RuntimeException ("M does not equal N");

        double[][] p = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble (); 
        int[] freq = new int[N];
        int page = 0;
        for (int t = 0; t < T; t++) {
            double r = Math.random (); 
            double sum = 0.0; 
            for (int j = 0; j < N; j++) {
                sum += p[page][j]; 
                if (r < sum) {
                    page = j;
                    break;
                } 
            }
            if (sum == 0.0)
                page = (int) (Math.random () * (double) N);

            freq[page]++; 
        } 
        for (int i = 0; i < N; i++)
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        StdOut.println ();
        StdOut.println ("             Média de Acessos (porcentagem)"); 
        StdOut.print ("Autorithies: ");
        double soma = 0;
        for (int i = A; i > 0; i--)
            soma += freq[N-H-i];
        StdOut.printf ("%30.5f\n", soma / T / A); 
        StdOut.print ("Hubs: ");
        soma = 0;
        for (int i = H; i > 0; i--)
            soma += freq[N-i];
        StdOut.printf ("%37.5f\n", soma / T / H);
    }  
} 