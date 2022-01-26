import java.util.NoSuchElementException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkedCircularListTest {

    @Test
    public void testSize() {
        CircularLinkedList<String> list = new CircularLinkedList<String>();
        list.add("1");
        list.add("2");
        list.add("3");

        assertEquals(3, list.length());
    }


    @Test
    public void clearShouldReturnZeroSize() {
        CircularLinkedList<String> list = new CircularLinkedList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.length());
        list.clear();
        assertEquals(0, list.length());
    }

    @Test
    public void testIsEmptyCorrecting() {
        CircularLinkedList<String> list = new CircularLinkedList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.clear();
        assertTrue(list.empty());
    }

    @Test
    public void testOrder() {
        CircularLinkedList<Integer> list = new CircularLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(3, (int) list.get(2));

    }

    @Test
    public void testRemoveToZeroSize() {
        CircularLinkedList<String> list = new CircularLinkedList<String>();
        list.add("first");
        list.add("second");
        list.add("third");

        try {
            for (String s : list) {
                list.remove(s);
            }
        } catch (NoSuchElementException e) {
            System.out.println("All items removed");
        }

        assertEquals(0, list.length());
    }

    @Test
    public void testSelfLinkedElement() {
        CircularLinkedList<String> list = new CircularLinkedList<String>();
        list.add("first");
        assertEquals(list.head.data, list.tail.data);
        assertEquals(list.head.next.data, list.head.previous.data);
    }

    @Test
    public void testCorrectLinksAfterRemoving() {
        CircularLinkedList<String> list = new CircularLinkedList<String>();
        list.add("first");
        list.add("second");
        list.remove("first");
        assertEquals(list.head, list.tail);
    }
}
