import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomIterator<Item> implements Iterator<Item> {

        private Item[] items;
        private int pointer;
        private int[] indices;

        public RandomIterator(Item[] itemArray, int size){
            this.items = itemArray;
            this.indices = new int[size];
            for(int i = 0; i < size; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(this.indices);
        }

        @Override
        public boolean hasNext() {
            return pointer != indices.length;
        }

        @Override
        public Item next() {
            if(indices.length == pointer) {
                throw new NoSuchElementException("Empty Queue");
            }
            Item res = items[indices[pointer]];
            pointer += 1;
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    private Item[] itemArray;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.itemArray = (Item[]) new Object[0];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException("Enqueue can't be null item");
        }
        //Queue is full
        if (itemArray.length == size) {
            Item[] newArray = (Item[]) new Object[(size + 1) * 2];
            for(int i = 0; i < this.itemArray.length; i++) {
                newArray[i] = itemArray[i];
            }
            this.itemArray = newArray;
        }
        size += 1;
        this.itemArray[size - 1] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if(size == 0) {
            throw new NoSuchElementException("Empty Queue");
        }
        int randomIndex = StdRandom.uniformInt(size);
        Item item = itemArray[randomIndex];
        for(int i = randomIndex + 1; i < size; i++) {
            //reshuffle so that there are no nulls
            this.itemArray[i - 1] = this.itemArray[i];
            if (i == size-1) {
                this.itemArray[i] = null;
            }
        }
        this.size -= 1;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(size == 0) {
            throw new NoSuchElementException("Empty Queue");
        }
        int randomIndex = StdRandom.uniformInt(size);
        return itemArray[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator<>(this.itemArray, this.size);
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int j = 1; j <= 100; j++)
            queue.enqueue(j);

        Iterator<Integer> iterator = queue.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());

    }

}