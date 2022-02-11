import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedListImpl<E> implements CircularLinkedList<E> {

    private Node<E> head;
    private transient int size = 0;

    @Override
    public void addToHead(E data) {
        if (head == null) {
            Node<E> node = new Node<E>(null, data, null);
            node.previous = node;
            node.next = node;
            head = node;
        } else {
            Node<E> oldHead = head;
            Node<E> tail = head.previous;
            Node<E> node = new Node<>(tail, data, oldHead);
            oldHead.previous = node;
            tail.next = node;
            head = node;
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
        return searchNode(index).data;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
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
        return head.previous;
    }

    private void unlinkNode(Node<E> node) {
        if (size == 1) {
            head = null;
            size = 0;
        } else {
            Node<E> before = node.previous;
            Node<E> after = node.next;
            before.next = after;
            after.previous = before;
            size--;
        }
    }

    private Node<E> searchNode(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index < size >> 2) {
            return searchNodeFromHead(index);
        } else {
            return searchNodeFromTail(index);
        }
    }

    private Node<E> searchNodeFromHead(Integer index) {
        Node<E> currentNode = head;
        for (int i = 0; i < size - 1; i++) {
            if (index == i) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        throw new IndexOutOfBoundsException();
    }

    private Node<E> searchNodeFromTail(Integer index) {
        Node<E> currentNode = head.previous;
        for (int i = size - 1; i >= 0; i--) {
            if (index == i) {
                return currentNode;
            }
            currentNode = currentNode.previous;
        }
        throw new IndexOutOfBoundsException();
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

    private static class Node<E> {

        private Node<E> previous;
        private E data;
        private Node<E> next;


        Node(Node<E> prev, E data, Node<E> next) {
            this.previous = prev;
            this.data = data;
            this.next = next;
        }
    }

    private static class CircularLinkedListIterator<E> implements Iterator<E> {
        private Node<E> current;

        CircularLinkedListIterator(Node<E> current) {
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
}
