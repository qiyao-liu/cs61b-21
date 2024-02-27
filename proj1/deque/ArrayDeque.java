package deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    private T[] items;
    public int size;
    private int nextFirst;
    private int nextLast;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[capacity];
        int start = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[start];
            start = addOne(start);
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private int addOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }

   /* public boolean isEmpty() {
        return size == 0;
    }*/
   @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        int start = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[start] + " ");
            start = addOne(start);
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = addOne(nextFirst);
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int start = addOne(nextFirst);
        for (int i = 0; i < index; i++) {
            start = addOne(start);
        }
        return items[start];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        public ArrayDequeIterator() {

            index = 0;
        }

        public boolean hasNext() {

            return index < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T currentItem = get(index);
            index++;
            return currentItem;
        }

        // Implement remove() method if needed
        public void remove() {

            throw new UnsupportedOperationException();
        }
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    /*
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(2);
        deque.addLast(5);
        deque.printDeque(); // Should print: 2 3 4 5
        System.out.println("Size: " + deque.size()); // Should print: 4
        deque.removeFirst();
        deque.removeLast();
        deque.printDeque(); // Should print: 3 4
    }
     */
}

