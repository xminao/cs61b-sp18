public class LinkedListDeque<T> implements Deque<T>{
    private Node sentinel;
    private int size;
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T t, Node p, Node n) {
            item = t;
            prev = p;
            next = n;
        }

        public Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node n = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = n;
        sentinel.next = n;
        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        Node n = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.println(n.item + " ");
            n = n.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        T item;
        Node n = sentinel.next;
        int i = 0;
        while (i != index) {
            n = n.next;
            i++;
        }
        item = n.item;

        return item;
    }

    private T getRecursiveHelp(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelp(n.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }


}
