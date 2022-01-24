import java.util.Iterator;

public interface ICircularLinkedList<E> extends Iterable<E> {

    void add(E data);

    void remove(E data);

    E get(int index);

    void clear();

    boolean empty();

    int length();

    Iterator<E> iterator();
}
