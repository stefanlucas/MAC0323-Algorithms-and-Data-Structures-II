/**************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
***************************************************************************/

import java.util.NoSuchElementException;

public class RandomQueue<Item> {
	private Item[] a;
	private int N;
	
	// resize aumenta o tamanho da RandomQueue
	private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // Construtor da RandomQueue
	public RandomQueue () {
		a = (Item[]) new Object[52];
		N = 0;
	}

	// verifica se a RandomQueue esta vazia
	public boolean isEmpty () {
		return N == 0;
	}

	// insere um item na RandomQueue
	public void enqueue (Item item) {
		if (N  == a.length) resize (2 * N);
		a[N++] = item;
	}

	// retira um elemento aleatoria da RandomQueue
	Item dequeue () {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		int index = StdRandom.uniform (N);
		Item item = a[index];
		a[index] = a[N-1];
		a[N-1] = null;
		N--;
		return item;
	}

	// retorna um elemento aleatorio da RandomQueue (nao retira)
	Item sample () {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return a[StdRandom.uniform (N)];
	}
}