package deque;
class IntNode<T> {
    public IntNode<T> prev;
    public T item;
    public IntNode<T> next;

    public IntNode(IntNode<T> m, T i, IntNode<T> n) {
        prev = m;
        item = i;
        next = n;
    }

}
public class LinkedListDeque<T> {
    private IntNode<T> sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        sentinel.next = new IntNode<>(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.prev = new IntNode<>(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;

    }

    public boolean isEmpty() {
        return size == 0;

    }

    public int size() {
        return size;

    }

    public void printDeque() {
        IntNode<T> p = sentinel;
        while (p.next != sentinel) {
            System.out.println(p.item);
            p = p.next;
        }
        System.out.println();
    }

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

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        return removedItem;
    }

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

    public LinkedListDequeIterator<T> iterator() {
        return new LinkedListDequeIterator<>(sentinel);
    }

    public class LinkedListDequeIterator<T> {
        private IntNode<T> current;

        public LinkedListDequeIterator(IntNode<T> start) {
            current = start.next;
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
        if (this.getClass() != o.getClass()) {
            return false;
        }

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }

        LinkedListDequeIterator<T> thisIterator = new LinkedListDequeIterator<>(this.sentinel);
        LinkedListDequeIterator<T> otherIterator = new LinkedListDequeIterator<>(other.sentinel);

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            T thisElement = thisIterator.next();
            T otherElement = otherIterator.next();

            if (thisElement == null ? otherElement != null : !thisElement.equals(otherElement)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
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

        // Test iterator
        System.out.println("Iterating through deque using iterator:");
        LinkedListDeque<Integer>.LinkedListDequeIterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // Expected output: 1 2
        }
        System.out.println();

        // Test equality
        LinkedListDeque<Integer> anotherDeque = new LinkedListDeque<>();
        anotherDeque.addLast(1);
        anotherDeque.addLast(2);
        System.out.println("Are the deques equal? " + deque.equals(anotherDeque)); // Expected output: true

        // Test isEmpty and size
        System.out.println("Is the deque empty? " + deque.isEmpty()); // Expected output: false
        System.out.println("Size of the deque: " + deque.size());      // Expected output: 2
    }
}

