import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {
    @Test
    void testSplayTree() {
        SplayTree<Double> tree = new SplayTree<Double>(new DoublesComparator());
        tree.add(1.0);
        tree.add(2.0);
        tree.add(3.0);
        tree.add(4.0);
        tree.add(5.0);
        tree.add(6.0);
        tree.add(7.0);
        tree.add(8.0);
        tree.add(9.0);
        System.out.print(tree.print());
    }
}