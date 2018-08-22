/***************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
*
* Compilação: javac-algs4 WordFrequencies.java
* Execução: java-algs4 WordFrequencies
* 
* Descrição do programa: 
* Entrada: Recebe texto da entrada padrão, podendo conter várias
* linhas.
* Saida: Mostra na saida padrão as palavras que apareceram no texto 
* e a frequencia delas. Letras maiusculas e minusculas são 
* consideradas iguais, e as palavras são impressas com
* letras minusculas minuscula. 
*
* Obs: são consideradas palavras sequencias de caracteres contendo 
* a-z, A-Z, hifen.
****************************************************************/

import java.util.*;

class Word implements Comparable<Word>{

	private String wordString;
	private int freq;

	public Word (String wordString ) {
		this.wordString = wordString;
		freq = 1;
	}

	public String getWord () {
		return wordString;
	}

	public int getFreq () {
		return freq;
	}

	public void incrementFreq () {
		freq++;
	}

	public String toString () {
		return this.getWord () + " " + this.getFreq ();
	}

	/* Definindo método de comparação para usar o método
	   sort de Collections*/
	public int compareTo(Word anotherWord) {
		if (this.freq < anotherWord.freq) {
			return 1;
		}
		if (this.freq > anotherWord.freq) {
			return -1;
		}
		if (this.wordString.compareTo (anotherWord.getWord()) < 0)
			return -1;
		return 0;
	}

}

public class WordFrequencies {

	public static void main (String args[]) {
		Scanner input = new Scanner (System.in);
		input.useDelimiter ("[^a-zA-Z\\-]+");
		ArrayList<Word> dic = new ArrayList<Word>();
		Word currentWord;
		int i; 

		/* Lendo as palavras */
		while (input.hasNext ()) {
			String nextWord = input.next ();
			for (i = 0; i < dic.size (); i++) {
				if (dic.get (i).getWord ().compareTo (nextWord.toLowerCase ()) == 0) {
					dic.get (i).incrementFreq ();
					break;
				}
			}
			if (i == dic.size ()) {
				currentWord = new Word (nextWord.toLowerCase ());
				dic.add (currentWord);
			}
		}

		/* Ordenando por ordem de frequencia, e por ordem lexicografica,
		   no caso de mesma frequencia*/
		Collections.sort (dic);

		for (i = 0; i < dic.size (); i++)
			StdOut.println (dic.get (i));
	}
}