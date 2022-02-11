import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CircularLinkedListTest {

    @Test
    public void lengthMethodShouldReturnActualSizeOfCollection() {
        CircularLinkedListImpl<String> list = new CircularLinkedListImpl<String>();
        list.addToHead("1");
        list.addToHead("2");
        list.addToHead("3");

        assertEquals(3, list.length());
    }


    @Test
    public void clearShouldRemoveAllElementsFromCollection() {
        CircularLinkedListImpl<String> list = new CircularLinkedListImpl<String>();
        list.addToHead("1");
        list.addToHead("2");
        list.addToHead("3");

        list.clear();

        assertEquals(0, list.length());
    }

    @Test
    public void emptyMethodShouldReturnTrueWhenCollectionWasCleaned() {
        CircularLinkedListImpl<String> list = new CircularLinkedListImpl<String>();
        list.addToHead("1");
        list.addToHead("2");
        list.addToHead("3");

        list.clear();
        assertEquals(0, list.length());
        assertTrue(list.isEmpty());

    }

    @Test
    public void addToHeadMethodShouldInsertElementsInHeadOfCollection() {
        CircularLinkedList<String> list = new CircularLinkedListImpl<String>();
        list.addToHead("third");
        list.addToHead("second");
        list.addToHead("first");

        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
        assertEquals("third", list.get(2));
    }

    @Test
    public void makeSureThatIfOnlyOneElementInCollectionItLinkedOnItself() {
        CircularLinkedListImpl<String> list = new CircularLinkedListImpl<String>();
        list.addToHead("first");
        assertEquals(list.getHead(), list.getTail());
    }

    @Test
    public void ifWeRemainOnlyOneElementInCollectionItShouldLinksOnItself() {
        CircularLinkedListImpl<String> list = new CircularLinkedListImpl<String>();
        list.addToHead("first");
        list.addToHead("second");
        list.remove("first");
        assertEquals(list.getHead(), list.getTail());
    }
}
