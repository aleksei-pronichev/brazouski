import java.util.Iterator;

public interface CircularLinkedList<E> extends Iterable<E> {

    void addToHead(E data);

    void remove(E data);

    E get(int index);

    void clear();

    boolean isEmpty();

    int length();

    Iterator<E> iterator();
}
