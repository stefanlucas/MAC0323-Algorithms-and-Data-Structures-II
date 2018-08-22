/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Numero Usp: 8531612
 ******************************************************************************/
public class BulgingSquares {
	public static void main (String[] args) {
		int i, j;
		double radius = .15, delta = .25;
		int[] lastPosition = {6, 4, 3, 2, 2, 1};
		StdDraw.setXscale (0, 15);
		StdDraw.setYscale (0, 15);

		/* Desenho dos quadrados maiores*/
		for (i = 1; i <= 15; i++) {
			for (j = 1; j <= 15; j++) {
				if ((i + j)%2 != 0)
					StdDraw.setPenColor (StdDraw.WHITE);
				else 
					StdDraw.setPenColor (StdDraw.BLACK);
				StdDraw.filledSquare (.5 + j - 1, .5 + i - 1, .5);
			}
		}

		/* Desenho da cruz que contém os quadrados menores*/
		for (i = 2; i <= 7; i++) {
			if (i%2 == 0)
				StdDraw.setPenColor  (StdDraw.WHITE);
			else
				StdDraw.setPenColor (StdDraw.BLACK);

			StdDraw.filledSquare (7 + delta, i - delta, radius);
			StdDraw.filledSquare (8 - delta, i - delta, radius);

			StdDraw.filledSquare (7 + delta, 15 - i + delta,  radius);
			StdDraw.filledSquare (8 - delta, 15 - i + delta, radius);

			StdDraw.filledSquare (i - delta, 7 + delta, radius);
			StdDraw.filledSquare (i - delta , 8 - delta, radius);

			StdDraw.filledSquare (15 - i + delta, 7 + delta, radius);
			StdDraw.filledSquare (15 - i + delta, 8 - delta, radius);
		}

		/* Desenho do resto dos quadrados menores*/
		for (i = 2; i <= 7; i++) {
			for (j =  6; j >= lastPosition[i-2]; j--) {
				if ((i + j)%2 == 0)
					StdDraw.setPenColor  (StdDraw.BLACK);
				else
					StdDraw.setPenColor (StdDraw.WHITE);

				StdDraw.filledSquare (j + 1 - delta, i - 1 + delta, radius);
				StdDraw.filledSquare (j + delta, i - delta, radius);

				StdDraw.filledSquare (15 - (j + 1 - delta), i - 1 + delta, radius);
				StdDraw.filledSquare (15 - (j + delta), i - delta, radius);

				StdDraw.filledSquare (15 - (j + 1 - delta), 15 - (i - 1 + delta), radius);
				StdDraw.filledSquare (15 - (j + delta), 15 - (i - delta), radius);

				StdDraw.filledSquare (j + 1 - delta, 15 - (i - 1 + delta), radius);
				StdDraw.filledSquare (j + delta, 15 - (i - delta), radius);

			}
		}
	}
}