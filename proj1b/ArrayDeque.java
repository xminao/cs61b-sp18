/**
 * second part of project1a.
 * deque implement by array.
 * @author xmina
 */
public class ArrayDeque<T> implements Deque<T>{
    private T[] array;
    private int length;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        nextFirst = 4;
        nextLast = 4;
    }

    /**
     * judge if the deque is empty.
     * @return true if the deque is empty, else false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return size of the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return index - 1
     */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    /**
     * @return the index + 1
     */
    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private void expandArray() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = nextFirst;
        int ptr2 = 0;
        while (ptr1 != nextLast) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        array = newArray;
        nextFirst = 0;
        nextLast = size;
        length = length * 2;
    }

    private void shrinkArray() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = nextFirst;
        int ptr2 = 0;
        while (ptr1 != nextLast) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        array = newArray;
        nextFirst = 0;
        nextLast = size;
        length = length / 2;
    }

    /**
     * add one item at the front of the deque.
     * @param item the item we want to add
     */
    @Override
    public void addFirst(T item) {
        if (size == length - 1) {
            expandArray();
        }
        nextFirst = minusOne(nextFirst);
        array[nextFirst] = item;
        size = size + 1;
    }

    /**
     * add one item at the last of the deque.
     * @param item the item we want to add
     */
    @Override
    public void addLast(T item) {
        if (size == length - 1) {
            expandArray();
        }
        array[nextLast] = item;
        nextLast = plusOne(nextLast, length);
        size = size + 1;
    }

    /**
     * remove one item at the front of the deque.
     * @return the item removed.
     */
    @Override
    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrinkArray();
        }
        if (size == 0) {
            return null;
        }
        T item = array[nextFirst];
        array[nextFirst] = null;
        nextFirst = plusOne(nextFirst, length);
        size = size - 1;
        return item;
    }

    /**
     * remove one item at the last of deque.
     * @return the item removed
     */
    @Override
    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrinkArray();
        }
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T item = array[nextLast];
        array[nextLast] = null;
        size = size - 1;
        return item;
    }

    /**
     * get the item in position index.
     * @param index
     * @return the item got
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int ptr = nextFirst;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }

    /**
     * print all item in list.
     */
    @Override
    public void printDeque() {
        int ptr = nextFirst;
        while (ptr != nextLast) {
            System.out.println(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
}