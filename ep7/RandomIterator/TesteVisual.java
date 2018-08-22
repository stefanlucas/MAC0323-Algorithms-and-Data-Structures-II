/**************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 TesteVisual.java 
* Execução: java-algs4 TesteVisual N T
*
* Descrição do programa:
*
* Programa que recebe como argumentos inteiros N e T,
* gera T permutacoes aleatorias dos numeros de 1..N, e 
* imprime a frequencia de cada uma das N! permutacoes 
* em forma de um histograma.
*
***************************************************************************/

public class TesteVisual {
	// funcao que retorna o fatorial de um numero n
	static int factorial (int n) {
		int factorial = 1;
		for (int i = 2; i <= n; i++) {
			factorial *= i; 
		}
		return factorial;
	}

	public static void main (String[] args) {
		int N = Integer.parseInt (args[0]);
		int T = Integer.parseInt (args[1]);
		int[] permutation = new int[N];
		int factorialN = factorial (N);
		int i, j, k, index;
		boolean found;
		int[][] allPermutations = new int[factorialN][N+1];
		RandomQueue<Integer> numbers = new RandomQueue<Integer>();
		
		for (i = 1; i <= N; i++) {
			numbers.enqueue (i);
		}
		index = 0;

		// realizando as T permutacoes aleatorias,
		// contabilizando suas frequencias
		for (k = 0; k < T; k++) {
			i = 0;
			for (Integer num : numbers) {
				permutation[i++] = num;
			}
			found = false;
			for (i = 0; i < index; i++) {
				for (j = 0, found = true;  j < N; j++) {
					if (permutation[j] != allPermutations[i][j+1]) {
						found = false;					
					}
				}
				if (found) {
					allPermutations[i][0]++;
					break;
				}
			}
			if (!found) {
				allPermutations[index][0] = 1;
				for (i = 0; i < N; i++) {
					allPermutations[index][i+1] = permutation[i];
				}
				index++;
			}
		}

		// impressao do histograma com a frequencia de cada permutacao
		Histogram histogram = new Histogram (factorialN + 1);
		for (i = 0; i < factorialN; i++) {
			for (j = 0; j < allPermutations[i][0]; j++) {
				histogram.addDataPoint (i);
			}
		}
		StdDraw.setCanvasSize(1000, 300);
        histogram.draw();
	}
}