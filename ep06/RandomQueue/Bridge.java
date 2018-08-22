/**************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 Bridge.java
* Execução: java-algs4 Bridge
* 
* Programa que imprime 13 cartas aleatorias de bridge usando 
* a estrutura RandomQueue.
***************************************************************************/

public class Bridge { 
    private int suit;      // clubs = 0, diamonds = 1, hearts = 2, spades = 3
    private int rank;      // deuce = 0, three = 1, four = 2, ..., king = 11, ace = 12
    
    // create a new Bridge based on integer 0 = 2C, 1 = 3C, ..., 51 = AS
    public Bridge (int card) {
        rank = card % 13;
        suit = card / 13;
    }
 
    public int rank() { return rank; }
    public int suit() { return suit; }

    // represent Bridges like "2H", "9C", "JS", "AD"
    public String toString() {
        String ranks = "23456789TJQKA";
        String suits = "CDHS";
        return ranks.charAt(rank) +  "" + suits.charAt(suit);
    }

	public static void main (String[] args) {
		RandomQueue<Bridge> deck = new RandomQueue<Bridge> ();
		int i;

        // coloca as cartas na RandomQueue
		for (i = 0; i < 52; i++) {
			Bridge card = new Bridge (i);
			deck.enqueue (card);
		}

        // retira e imprime 13 cartas da RandomQueue
		for (i = 0; i < 13; i++) {
			Bridge card = deck.dequeue ();
			StdOut.println (card);
		}

	}
}