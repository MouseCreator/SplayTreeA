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

    public boolean find(T value) {
        TreeNode<T> current = root;
        while (current != null) {
            if (current.isValueOf(value)) {
                splay(current);
                return true;
            }
            if (current.isGreater(value)) {
                current=current.getLeft();
            } else if (current.isLower(value)) {
                current=current.getRight();
            }
        }
        return false;
    }


    public void remove(T value) {

    }

    public void add(T value) {

    }

    private void splay(TreeNode<T> targetNode) {
        while (!targetNode.isRoot()) {
            targetNode = targetNode.splay();
        }
        this.root = targetNode;
    }

    void setRoot(TreeNode<T> root) {
        this.root = root;
    }
}
