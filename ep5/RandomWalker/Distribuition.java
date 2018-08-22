/***************************************************************
* Nome: Lucas Stefan Abe
* Nº Usp: 8531612
*
* Compilação: javac-algs4 Distribuition.java
* Execução: java-algs4 Distribuition N M L
*
* O que o programa faz?
*
* O programa deverá simular vários random walkers que
* podem se mover para cima, baixo, esquera ou direita
* em uma area 2*L x 2*L. Cada random walker começa na 
* origem.
*
* Entrada: Um inteiro N de passos, um inteiro M de random
* walkers e por último um inteiro L, onde 2*L é o tamanho
* do quadrado onde os random walkers caminharão. Cada passo
* do random walker tem tamanho 1.
*
* Saida: Uma matriz 2*L x 2*L pintada de três cores, indicando
* a frequencia de visita de cada quadradinho de tamanho 1.
* As frequencias são separadas em três categorias:
* Acima da média (preto), abaixo da média (cinza),
* não teve visitas (branco).
***************************************************************/


public class Distribuition {
	public static void main (String[] args) {
		int N = Integer.parseInt (args[0]);
		int M = Integer.parseInt (args[1]);
		int L = Integer.parseInt (args[2]);
		int[][] matrix = new int[2*L][2*L];
		int i, j, average;
		int[] vx = {1, -1, 0, 0};
		int[] vy = {0, 0, 1, -1}; 
		RandomWalker walker = new RandomWalker (L);

		for (i = 0; i < M; i++) {
			for (j = 0; j < N; j++) {
				walker.step (L, vx, vy);
				matrix[walker.getYaxis ()][walker.getXaxis ()]++;
			}
			walker.setXaxis (L);
			walker.setYaxis (L);
		}
		average = 0;
		for (i = 0; i < 2*L; i++) {
			for (j = 0; j < 2*L; j++) {
				average += matrix[i][j];
			}
		} 
		average /= (2*L*2*L);
		StdDraw.setXscale (0, 2*L);
		StdDraw.setYscale (0, 2*L);
		for (i = 0; i < 2*L; i++) {
			for (j = 0; j < 2*L; j++) {
				if (matrix[i][j] > average) {
					StdDraw.setPenColor (StdDraw.BLACK);
					StdDraw.filledSquare (i + 0.5, j + 0.5, 0.5);
				}
				else if (matrix[i][j] > 0) {
					StdDraw.setPenColor (StdDraw.GRAY);
					StdDraw.filledSquare (i + 0.5, j + 0.5, 0.5);
				}
			}
		}

	}
}