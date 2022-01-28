import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<E> implements ICircularLinkedList<E> {

    private Node<E> head;
    private transient int size = 0;

    @Override
    public void add(E data) {
        if (head == null) {
            Node<E> node = new Node<E>(null, data, null);
            node.setPrevious(node);
            node.setNext(node);
            head = node;
        } else {
            Node<E> newNode = new Node<E>(head, data, head.getPrevious());
            head.setNext(newNode);
        }
        size++;
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
        return searchNode(index).getData();
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.getNext();
            x.setData(null);
            x.setNext(null);
            x.setPrevious(null);
            x = next;
        }
        head = null;
        size = 0;
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

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return head.getPrevious();
    }

    private void unlinkNode(Node<E> node) {
        if (size == 1) {
            head.setData(null);
            head.setPrevious(null);
            head.setNext(null);
            head = null;
            size = 0;
        } else {
            Node<E> before = node.getPrevious();
            Node<E> after = node.getNext();
            before.setNext(after);
            after.setPrevious(before);
            size--;
        }
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
                currentNode = currentNode.getPrevious();
            }
        } else {
            Node<E> currentNode = head.getPrevious();
            for (int i = size - 1; i >= 0; i--) {
                if (index == i) {
                    return currentNode;
                }
                currentNode = currentNode.getNext();
            }
        }
        throw new NoSuchElementException();
    }

    private Node<E> searchNode(E data) {
        for (Node<E> x = head; x != null; ) {
            if (x.getData().equals(data)) {
                return x;
            }
            x = x.getNext();
        }
        throw new NoSuchElementException();
    }
}
