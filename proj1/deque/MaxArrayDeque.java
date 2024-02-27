package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator ;
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.comparator = c;

    }

    public T max(){
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (comparator.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (c.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }
/*
    public static void main(String[] args) {
        // Example usage and testing
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(Comparator.naturalOrder());
        deque.addLast(3);
        deque.addLast(7);
        deque.addLast(1);
        deque.addLast(5);

        System.out.println("Max element using natural order comparator: " + deque.max()); // Expected output: 7

        // Custom comparator to find max even number
        Comparator<Integer> evenComparator = (a, b) -> {
            if (a % 2 == 0 && b % 2 != 0) {
                return 1;
            } else if (a % 2 != 0 && b % 2 == 0) {
                return -1;
            } else {
                return Integer.compare(a, b);
            }
        };

        System.out.println("Max even element: " + deque.max(evenComparator)); // Expected output: 7 (since 7 is the only even number)
    } */
}
