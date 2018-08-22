/********************************************************
* Nome: Lucas Stefan Abe
* Nº USP: 8531612
* 
* Implementação da API List. Para realiza-la foram 
* utilizadas duas RBBST, sendo uma indexada pelos 
* índices em double, e a outra indexada pelos Items
* da list. Como as operações realizadas na list
* são feitas através dessas duas estruturas (que possuem
* complexidade logn) e há um  número constante e pequeno 
* dessas operações para cada método da list, a 
* complexidade dos métodos não passa de O(logn).
* Os métodos isEmpty() e size() são O(1). 
*
*
* Decisão de projeto:
* Mudei o nome do método "public void delete(Item item)"
* para "public void deleteItem(Item item)"
*
*********************************************************/


import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class List<Item extends Comparable<Item>> implements Iterable<Item> {
    private RedBlackBST<Double, Item> tabelaDeIndices;
    private RedBlackBST<Item, Double> tabelaDeItens;
    
    // cria a lista
    public List() {
        tabelaDeIndices = new RedBlackBST<Double, Item>();
        tabelaDeItens = new RedBlackBST<Item, Double>();
    } 


    // devolve o i-ésimo item da lista
    public Item get(int i) {
        double indice = tabelaDeIndices.select(i);
        return tabelaDeIndices.get(indice);
    }

    // a chave está na lista?
    public boolean contains(Item item) {
        return tabelaDeItens.contains(item);
    }

    // adiciona um item na frente
    public void addFront(Item item) {
        int n = tabelaDeIndices.size();
        if (n == 0) {
            tabelaDeIndices.put(0.0, item);
            return;
        }
        else if (n == 1) {
            tabelaDeIndices.put(1.0, item);
            return;
        }
        
        double indiceAnterior = tabelaDeIndices.select (n-2);
        double indiceMax = tabelaDeIndices.max ();
        double indice = (indiceAnterior + indiceMax)/((double)2.0);
        Item itemMax = tabelaDeIndices.get(indiceMax);
        
        tabelaDeIndices.deleteMax();

        tabelaDeIndices.put(indiceMax, item);
        tabelaDeIndices.put(indice, itemMax);
        
        tabelaDeItens.put(itemMax, indice);
        tabelaDeItens.put(item, indiceMax);

    }

    // adiciona um item atrás
    public void addBack(Item item) {
        int n = tabelaDeIndices.size();
        if (n == 0) {
            tabelaDeIndices.put(0.0, item);
            tabelaDeItens.put(item, 0.0);
            return;
        }
        else if (n == 1) {
            double indiceAux = tabelaDeIndices.select(0);
            Item itemAux = tabelaDeIndices.get(indiceAux);
            tabelaDeIndices.delete(indiceAux);
            tabelaDeIndices.put(1.0, itemAux);
            tabelaDeItens.put(itemAux, 1.0);

            tabelaDeIndices.put(0.0, item);
            tabelaDeItens.put(item, 0.0);
            return;
        }
        
        
        double indiceMin = tabelaDeIndices.min ();
        double indiceProximo = tabelaDeIndices.select (1);
        double indice = (indiceMin + indiceProximo)/((double)2.0);
        Item itemMin = tabelaDeIndices.get(indiceMin);
        
        tabelaDeIndices.deleteMin();
        tabelaDeIndices.put(indiceMin, item);
        tabelaDeIndices.put(indice, itemMin);
       
        tabelaDeItens.put(itemMin, indice);
        tabelaDeItens.put(item, indiceMin);
    }

    // adiciona um item como o i-ésimo elemento da lista
    public void add(int i, Item item) {
        int n = tabelaDeIndices.size();
        if (i > n || i < 0)  {
            StdOut.println ("Erro ao adicinar na pos " + i + " porque é maior que o tamanho da lista  ou menor que 0!!");
            return;
        }
        else if (i == 0) {
            addBack(item);
            return;
        }
        else if (i == n) {    
            addFront(item);
            return;
        }

        double indice1 = tabelaDeIndices.select(i-1);
        double indice2 = tabelaDeIndices.select(i);
        double indice = (indice1 + indice2)/((double)2.0);

        tabelaDeIndices.put(indice, item);
        tabelaDeItens.put(item, indice);
    }

    // remove o item da frente
    public Item deleteFront() {
        int n = tabelaDeIndices.size();
        if (n == 0) {
            StdOut.println("Erro!! Não é possível remover um elemento pois a lista está vazia!!!");
            return null;
        }
        
        double indiceMax = tabelaDeIndices.max();
        Item item = tabelaDeIndices.get(indiceMax);
        tabelaDeIndices.deleteMax();
        tabelaDeItens.delete(item);

        if (n == 1) 
            return item;

        double indiceAux = tabelaDeIndices.max();
        Item itemAux = tabelaDeIndices.get(indiceAux);
        tabelaDeIndices.deleteMax();
        tabelaDeIndices.put(indiceMax, itemAux);
        tabelaDeItens.put(itemAux, indiceMax);

        return item;
    }

    // remove o item de trás
    public Item deleteBack() {
        int n = tabelaDeIndices.size();
        if (n == 0) {
            StdOut.println("Erro!! Não é possível remover um elemento pois a lista está vazia!!!");
            return null;
        }
        
        double indiceMin = tabelaDeIndices.min();
        Item item = tabelaDeIndices.get(indiceMin);
        tabelaDeIndices.deleteMin();
        tabelaDeItens.delete(item);

        if (n == 1) 
            return item;
        
        double indiceAux = tabelaDeIndices.min();
        Item itemAux = tabelaDeIndices.get(indiceAux);
        tabelaDeIndices.deleteMin();
        tabelaDeIndices.put(indiceMin, itemAux);
        tabelaDeItens.put(itemAux, indiceMin);
        
        return item;
    }

    // remove o item da lista
    public void deleteItem(Item item) {
        int n = tabelaDeIndices.size();
        if (n == 0) {
            StdOut.println("Erro!! Não é possível remover um elemento pois a lista está vazia!!!");
            return;
        }

        double indice = tabelaDeItens.get(item);    
        int i = tabelaDeIndices.rank(indice);

        if (i == 0) {
            deleteBack();
            return;
        }
        else if (i == n-1) {
            deleteFront();
            return;
        }

        tabelaDeIndices.delete(indice);
        tabelaDeItens.delete(item);
        
    }

    // remove o i-ésimo elemento da lista
    public Item delete(int i) {
        int n = tabelaDeIndices.size();
        if (n == 0 || i < 0 || i >= n) {
            StdOut.println("Erro!! Não é possível remover um elemento pois a lista está vazia ou o índice é invalido!!!");
            return null;
        }

        if (i == 0) {
            return deleteBack();
        }
        else if (i == n-1) {
            return deleteFront();
        }

        double indice = tabelaDeIndices.select(i);
        Item item = tabelaDeIndices.get(indice);
        tabelaDeIndices.delete(indice);
        tabelaDeItens.delete(item);
        return item;
    }

    // a lista está vazia?
    public boolean isEmpty() {
        return tabelaDeIndices.isEmpty();
    }

    // número de elementos na lista
    public int size() {
        return tabelaDeIndices.size();
    }

    // promessa de que um iterador será feito
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    // criando o iterador
    private class listIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() { 
            return i < size();    
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() { 
            Item item = get(i++);
            return item;
        }
    }
    
    /***** métodos para verificar se a lista está correta *****/
    private void tamanhosIguais() {
        if (tabelaDeIndices.size() == tabelaDeItens.size())
            StdOut.println("Os tamanhos estão iguais");
        else 
            StdOut.println("Os tamanhos estão diferentes");
    }

    private void imprimeTabelaDeIndices() {
        StdOut.println("Tabela de indices:");
        for (Double indice : tabelaDeIndices.keys())
            StdOut.printf("%18.14f\n", indice);
    }

    public static void main(String args[]) {
        List<Integer> list = new List<Integer>();
        int item, i;
        for (i = 0; i < 10; i++) {
            item = 10+i;
            list.add (i, item);
        }

        list.addBack(-100);
        list.addFront(100);
        list.add(1, 2142);

        StdOut.println("Itens da lista:");
        for (int aux : list) {
            StdOut.println(aux);
        }

        item = list.deleteFront();
        StdOut.println("Item " + item + " removido");

        item = list.deleteBack();
        StdOut.println("Item " + item + " removido");
        
        item = list.delete(5);
        StdOut.println("Item " + item + " removido");

        list.deleteItem(11);
        StdOut.println("Item " + 11 + " removido");

        StdOut.println("Itens da lista:");
        for (int aux : list) {
            StdOut.println(aux);
        }

        list.tamanhosIguais();
    }
}