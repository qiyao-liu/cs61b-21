package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private static class IntNode<T> {
        IntNode<T> prev;
        T item;
        IntNode<T> next;

        IntNode(IntNode<T> m, T i, IntNode<T> n) {
            prev = m;
            item = i;
            next = n;
        }
    }

    private IntNode<T> sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        IntNode<T> newNode = new IntNode<>(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode; // Update the previous reference of the current first node
        sentinel.next = newNode; // Update sentinel's next reference to the new first node
    }
    

    @Override
    public void addLast(T item) {
        size += 1;
        IntNode<T> newNode = new IntNode<>(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = newNode;
        sentinel.prev = newNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode<T> p = sentinel;
        while (p.next != sentinel) {
            System.out.println(p.item);
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        IntNode<T> p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        IntNode<T> p = sentinel.next;
        if (index == 0) {
            return p.item;
        } else {
            p = p.next;
            return getRecursive(index - 1);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private IntNode<T> current;

        private LinkedListDequeIterator() {
            current = sentinel.next;
        }

        public boolean hasNext() {
            return current != sentinel;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<?> other = (LinkedListDeque<?>) o;
        if (size() != other.size()) {
            return false;
        }

        Iterator<T> thisIterator = iterator();
        Iterator<?> otherIterator = other.iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            T thisElement = thisIterator.next();
            Object otherElement = otherIterator.next();

            if (thisElement == null ? otherElement != null : !thisElement.equals(otherElement)) {
                return false;
            }
        }

        // Check if both iterators have reached the end
        return !thisIterator.hasNext() && !otherIterator.hasNext();
    }
}

    /* public static void main(String[] args) {
        // Create a new LinkedListDeque
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        // Test adding elements to the front and back of the deque
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        // Print the deque
        System.out.println("Deque after adding elements:");
        deque.printDeque(); // Expected output: 3 1 2 4

        // Test removing elements from the front and back of the deque
        Integer removedFirst = deque.removeFirst();
        Integer removedLast = deque.removeLast();

        // Print the removed elements
        System.out.println("Removed first element: " + removedFirst); // Expected output: 3
        System.out.println("Removed last element: " + removedLast);   // Expected output: 4

        // Print the deque after removal
        System.out.println("Deque after removing elements:");
        deque.printDeque(); // Expected output: 1 2

        // Test getting elements by index
        System.out.println("Element at index 0: " + deque.get(0)); // Expected output: 1
        System.out.println("Element at index 1: " + deque.get(1)); // Expected output: 2


        ; // Expected output: true

        // Test isEmpty and size
        System.out.println("Is the deque empty? " + deque.isEmpty()); // Expected output: false
        System.out.println("Size of the deque: " + deque.size());      // Expected output: 2
    }
} **/
