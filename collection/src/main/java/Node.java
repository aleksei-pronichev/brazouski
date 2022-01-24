public class Node<E> {

    Node<E> previous;
    E data;
    Node<E> next;


    Node(Node<E> prev, E data, Node<E> next) {
        this.previous = prev;
        this.data = data;
        this.next = next;
    }
}
