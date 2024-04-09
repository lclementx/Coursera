import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static class Node<Item> {
        private final Item item;
        public Node<Item> next;
        public Node<Item> previous;

        public Node(Item item) {
            this.item = item;
        }

    }

    private static class QueueIterator<Item> implements Iterator<Item> {

        private Node<Item> nodeIterate;

        public QueueIterator(Node<Item> node){
            this.nodeIterate = node;
        }

        @Override
        public boolean hasNext() {
            return this.nodeIterate != null;
        }

        @Override
        public Item next() {
            if (this.nodeIterate == null) {
                throw new NoSuchElementException("No Nodes");
            }
            Item item = nodeIterate.item;
            this.nodeIterate = this.nodeIterate.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
    private Node<Item> currentNode;
    private Node<Item> lastNode;
    private int size;
    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("No Null Arguments");
        }
        if (size == 0) {
            currentNode = new Node<>(item);
            lastNode = currentNode;
        }
        else {
            Node<Item> newNode = new Node<>(item);
            currentNode.previous = newNode;
            newNode.next = currentNode;
            currentNode = newNode;
        }
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("No Null Arguments");
        }
        if (size == 0) {
            lastNode = new Node<>(item);
            currentNode = lastNode;
        }
        else {
            Node<Item> newNode = new Node<>(item);
            lastNode.next = newNode;
            newNode.previous = lastNode;
            lastNode = newNode;
        }
        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (currentNode == null) {
            throw new NoSuchElementException("No Current Node");
        }
        Node<Item> nextNode = currentNode.next;
        Item item = currentNode.item;
        if (nextNode == null) {
            lastNode = null;
        }
        currentNode = nextNode;
        size -= 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (lastNode == null) {
            throw new NoSuchElementException("No Last Node");
        }
        Node<Item> prevNode = lastNode.previous;
        Item item = lastNode.item;
        if (prevNode != null) {
            prevNode.next = null;
            lastNode = prevNode;
        } else {
            //reset cause the lastNode is firstNode if prev = null
            lastNode = null;
            currentNode = null;
        }
        size -= 1;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new QueueIterator<>(this.currentNode);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.isEmpty();
        deque.isEmpty();
        deque.addLast(3);
        deque.removeFirst();
        deque.addLast(5);
        deque.removeFirst();
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addLast(5);
        deque.removeLast();
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());

    }

}
