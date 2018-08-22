/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Numero Usp: 8531612
 ******************************************************************************/
public class RandomWeb {
	public static void main (String[] args) {
		int N = Integer.parseInt (args[0]); 
		int M = Integer.parseInt (args[1]); 
		int H = Integer.parseInt (args[2]); 
		int A = Integer.parseInt (args[3]); 
		/* matrix[i][j] é a quantidade de links da página i para a página j */
		int[][] matrix = new int[N + H + A][N + H + A]; 
		int i, j, l, cont, page1, page2;
	
		/* Criando os authorities, que apontam para o teto de 10% das paginas */
		for (i = N; i < N + A; i++) {
			for (j = 0; j < Math.ceil (0.1 * (double) N); j++) {
				do {
					page2 = (int) (Math.random () * (double) N);
				}
				while (i == page2 || matrix[i][page2] != 0);
				matrix[i][page2]++;
			} 
		}
		/* Criando os hubs, que são apontados pelo teto de 10% das páginas*/
		for (i = N + A; i < N + A + H; i++) {
			for (j = 0; j < Math.ceil (0.1 * (double) N); j++) {
				do {
					page1 = (int) (Math.random () * (double) N);
				}
				while (i == page1 || matrix[page1][i] != 0);
				matrix[page1][i]++;
			} 
		}
		/* Criando o resto dos links aleatórios*/
		for (l = 0; l < M; l++) {
			page1 = (int) (Math.random () * (double) N);
			do {
				page2 = (int) (Math.random () * (double) N);
			}
			while (page1 == page2);
			matrix[page1][page2]++;
		}
		StdOut.println (N + A + H);
		StdOut.println (A + " " + H );		
		for (i = 0; i < N + A + H; i++) {
			for (j = 0; j < N + A + H; j++) {
				for (cont = 0; cont < matrix[i][j]; cont++)
					StdOut.print(i + " " + j + " ");
				if (matrix[i][j] != 0)
					StdOut.println ();
			}
		} 
	}
}