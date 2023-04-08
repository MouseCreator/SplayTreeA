import java.util.Comparator;

public class SplayTree<T> {
    private final Comparator<T> comparator;

    public Comparator<T> getComparator() {
        return comparator;
    }
    TreeNode<T> root;
    public SplayTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean get(T value) {
        return false;
    }

    public void remove(T value) {

    }

    public void add(T value) {

    }

    private void splay(T value) {

    }

    void setRoot(TreeNode<T> root) {
        this.root = root;
    }
}
