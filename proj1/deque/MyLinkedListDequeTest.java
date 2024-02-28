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
    @Test
    public void testAddFirstAndRemoveLast2() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(Integer.valueOf(0), deque.removeFirst());
    }

    @Test
    public void testAddFirstAndRemoveLast3() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(0);
        deque.addLast(1);
        assertEquals(Integer.valueOf(0), deque.removeFirst());
    }

    @Test
    public void testIterativeGet() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        assertEquals(Integer.valueOf(2), deque.get(2));
    }

    @Test
    public void testRecursiveGet() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        assertEquals(Integer.valueOf(4), deque.removeFirst());
        assertEquals(Integer.valueOf(3), deque.removeFirst());
        assertEquals(Integer.valueOf(2), deque.removeLast());
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("deque.MyLinkedListDequeTest");
    }
}
