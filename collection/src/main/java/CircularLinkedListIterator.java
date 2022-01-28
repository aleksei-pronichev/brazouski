import java.util.Iterator;

public class CircularLinkedListIterator<E> implements Iterator<E> {
    private Node<E> current;

    public CircularLinkedListIterator(Node<E> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E data = current.getData();
        current = current.getNext();
        return data;
    }
}
