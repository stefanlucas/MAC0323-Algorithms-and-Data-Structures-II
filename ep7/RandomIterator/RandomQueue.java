/**************************************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
***************************************************************************/

import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {
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

    // construtor da RandomQueue
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

	// retira um elemento aleatorio da RandomQueue
	Item dequeue () {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		int index = StdRandom.uniform (N);
		Item item = a[index];
		a[index] = a[N-1];
		a[N-1] = null;
		N--;
		return item;
	}

	// retorna uma amostra aleatoria da RandomQueue (nao retira)
	Item sample () {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return a[StdRandom.uniform (N)];
	}

	// promessa de que um iterador sera feito
	public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // criando o iterador aleatorio
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private boolean[] wasChoosen = new boolean[N];
        public boolean hasNext()  { return i < N;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index;
            do {
            	index = StdRandom.uniform (N);
            }while (wasChoosen[index] == true);
            wasChoosen[index] = true;
            i++;
            return a[index]; 
        }
    }
}