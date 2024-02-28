package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyLinkedListDequeTest {

    @Test
    public void testAddFirstAndRemoveLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.removeLast();
        deque.addFirst(3);
        deque.addFirst(4);
        deque.isEmpty();
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        assertEquals(Integer.valueOf(1), deque.removeLast());
    }

    @Test
    public void testIsEmpty() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
    }

    // Add more test methods for other operations...

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("deque.MyLinkedListDequeTest");
    }
}
