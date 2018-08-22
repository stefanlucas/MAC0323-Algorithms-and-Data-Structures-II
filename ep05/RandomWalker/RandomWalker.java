/***************************************************************
* Nome: Lucas Stefan Abe
* Nº Usp: 8531612
*
* Compilação: javac-algs4 RandomWalker.java
* Execução: java-algs4 RandomWalker N L
*
* O que o programa faz?
*
* Teste da class RandomWalker. Imprime a distancia e as 
* coordenadas x, y de um random walker após N passos, iniciando
* da origem, em um quadrado de lado 2*L.
*
****************************************************************/

public class RandomWalker {
	private int x, y;  

	public RandomWalker (int L) {
		x = y = L;
	}

	public int getXaxis () {
		return x;
	}

	public int getYaxis () {
		return y;
	}

	public void setXaxis (int x) {
		this.x = x;
	}

	public void setYaxis (int y) {
		this.y = y;
	}

	public void step (int L, int[] vx, int[] vy) {
		int r;
 		do {
			r = StdRandom.uniform (4);
		}
		while ((x + vx[r] >= 2*L) || (x + vx[r] < 0) ||
			(y + vy[r] >= 2*L) || (y + vy[r] < 0));
		x += vx[r];
		y += vy[r];
	}

	public double distance () {
		return Math.sqrt (x*x + y*y);
	}

	public static void main (String[] args) {
		int N = Integer.parseInt (args[0]);
		int L = Integer.parseInt (args[1]);
		int i;
		int[] vx = {1, -1, 0, 0};
		int[] vy = {0, 0, 1, -1}; 

		RandomWalker walker = new RandomWalker (L);
		for (i = 0; i < N; i++) {
			walker.step (L, vx, vy);
		}
		StdOut.println ("distancia = " + walker.distance ());
		StdOut.println ("x = " + walker.getXaxis () + "\ny = " + walker.getYaxis ());
	}
}