public class Node<E> {

    private Node<E> previous;
    private E data;
    private Node<E> next;


    public Node(Node<E> prev, E data, Node<E> next) {
        this.previous = prev;
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
}
