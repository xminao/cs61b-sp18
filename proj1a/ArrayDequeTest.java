import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public static void expandTest() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            array.addFirst(i);
        }
        for (int i = -5; i < 0; i++) {
            array.addLast(i);
        }
        assertEquals(10, array.size());
        array.printDeque();
    }

    @Test
    public static void shrinkTest() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            array.addFirst(i);
        }
        for (int i = 0; i < 15; i++) {
            array.removeFirst();
        }
        assertEquals(5, array.size());
        array.printDeque();
    }

    @Test
    public static void removeTest() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            array.addFirst(i);
        }
        array.removeFirst();
        assertEquals(4, array.size());
        array.printDeque();
    }

    @Test
    public static void getTest(){
        ArrayDeque<Integer> array = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            array.addFirst(i);
        }
        assertEquals(3, (Object) array.get(1));
    }

    public static void main(String[] args) {
        //expandTest();
        //shrinkTest();
        //removeTest();
        getTest();
    }

}
