import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<E> implements ICircularLinkedList<E> {

    Node<E> head;
    Node<E> tail;
    int modCount = 0;
    transient int size = 0;

    @Override
    public void add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (head == null) {
            Node<E> node = new Node<E>(null, data, null);
            node.previous = node;
            node.next = node;
            head = node;
            tail = node;
        } else {
            Node<E> newNode = new Node<E>(head, data, tail);
            head.next = newNode;
            tail.previous = newNode;
            tail = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void remove(E e) {
        unlinkNode(searchNode(e));
    }

    public void remove(int index) {
        unlinkNode(searchNode(index));
    }

    @Override
    public E get(int index) {
        return searchNode(index).data;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x.previous = null;
            x = next;
        }
        head = tail = null;
        size = 0;
        modCount++;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator<E>(head);
    }

    private void unlinkNode(Node<E> node) {
        if (size == 1) {
            head.data = null;
            head.previous = null;
            head.next = null;
            head = tail = null;
            size = 0;
        } else {
            Node<E> before = node.previous;
            Node<E> after = node.next;
            before.next = after;
            after.previous = before;
            if (size == 2) {
                if (node == head) {
                    head = tail;
                } else {
                    tail = head;
                }
            }
            size--;
        }
        modCount++;
    }

    private Node<E> searchNode(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size >> 2) {
            Node<E> currentNode = head;
            for (int i = 0; i < size - 1; i++) {
                if (index == i) {
                    return currentNode;
                }
                currentNode = currentNode.previous;
            }
        } else {
            Node<E> currentNode = tail;
            for (int i = size - 1; i >= 0; i--) {
                if (index == i) {
                    return currentNode;

                }
                currentNode = currentNode.next;
            }
        }
        throw new NoSuchElementException();
    }

    private Node<E> searchNode(E data) {
        for (Node<E> x = head; x != null; ) {
            if (x.data.equals(data)) {
                return x;
            }
            x = x.next;
        }
        throw new NoSuchElementException();
    }
}
