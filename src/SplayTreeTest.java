import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {
    @Test
    void testLinearInsertTree() {
        SplayTree<Double> tree = new SplayTree<>(new DoublesComparator());
        tree.add(1.0);
        tree.add(2.0);
        tree.add(3.0);
        tree.add(4.0);
        tree.add(5.0);
        tree.add(6.0);
        tree.add(7.0);
        tree.add(8.0);
        tree.add(9.0);
        assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]", tree.print());
    }

    @Test
    void testRandomInsertTree() {
        SplayTree<Double> tree = new SplayTree<>(new DoublesComparator());
        Double[] doubles = new Double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(doubles));
        for (int i = 0; i < 20; i++) {
            Collections.shuffle(list);
            System.out.println(list);
            for (Double d : doubles) {
                tree.add(d);
            }
            assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]", tree.print());
        }
    }

    @Test
    void testAddingOrder() {
        String v = "4.0, 3.0, 6.0, 8.0, 1.0, 2.0, 5.0, 7.0, 9.0";
        SplayTree<Double> tree = new SplayTree<>(new DoublesComparator());
        String[] nums = v.split(", ");
        for (String n : nums) {
            tree.add(Double.parseDouble(n));
        }
        assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]", tree.print());
    }
}