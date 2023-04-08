import java.util.Comparator;

public class SplayTree<T> {
    private final Comparator<T> comparator;
    public SplayTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }
}
