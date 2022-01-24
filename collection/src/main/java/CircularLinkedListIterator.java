import java.util.Iterator;

public class CircularLinkedListIterator<E> implements Iterator<E> {
    Node<E> current;

    public CircularLinkedListIterator(Node<E> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E data = current.data;
        current = current.next;
        return data;
    }
}
