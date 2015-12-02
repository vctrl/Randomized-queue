import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;
    public RandomizedQueue() { // construct an empty randomized queue
        a = (Item[]) new Object[2];
    }
    public boolean isEmpty() { // is the queue empty?
        return N == 0;
    }
    public int size() { // return the number of items on the queue
        return N;
    }
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void enqueue(Item item) { // add the item
        if (item == null) throw new NullPointerException();
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int random = StdRandom.uniform(0, N);
        Item temp;
        temp = a[random];
        a[random] = a[N - 1];
        a[N - 1] = null;
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return temp;
    }
    public Item sample() { // return (but do not remove) a random item
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return a[StdRandom.uniform(0, N)];
    }
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new RandomArrayIterator();
    }
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;

        public RandomArrayIterator() {
            StdRandom.shuffle(a, 0, N - 1);
            i = N - 1;
        }
        public boolean hasNext() {
            return i >= 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() { // make random
            return a[i--];
        }
    }
    public static void main(String[] args) { // unit testing
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        s.enqueue("AA");
        s.enqueue("BB");
        s.enqueue("CC");
        s.enqueue("DD");
        s.enqueue("EE");
        s.enqueue("FF");
        s.enqueue("GG");
        s.enqueue("HH");
        s.enqueue("II");
        s.enqueue("JJ");
        for(String a : s)
            StdOut.println(a);
    }
}