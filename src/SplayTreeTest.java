import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {
    @Test
    void testSplayTree() {
        SplayTree<Double> tree = new SplayTree<>(new DoublesComparator());
        tree.add(1.0);
        tree.add(2.0);
        System.out.println(tree.print());
        tree.add(3.0);
        System.out.println(tree.print());
        tree.add(4.0);
        tree.add(5.0);
        tree.add(6.0);
        tree.add(7.0);
        tree.add(8.0);
        tree.add(9.0);
        System.out.println(tree.print());
        assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]", tree.print());
    }
}