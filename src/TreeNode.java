import java.util.Comparator;

public class TreeNode<T> {
    private final T value;
    private TreeNode<T> left;
    private SplayTree<T> tree;
    private TreeNode<T> right;

    public TreeNode(T value, SplayTree<T> tree) {
        this.left=null;
        this.value = value;
        this.tree = tree;
    }

    public boolean hasLeft() {
        return left != null;
    }
    public boolean hasRight() {
        return right != null;
    }
    public T getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "[T=" + value.toString() + "]";
    }
    private boolean hasValueOf(T value) {
        return tree.getComparator().compare(this.value, value) == 0;
    }
    public TreeNode<T> splay(T target) {
        if (hasValueOf(target)) {
            return this;
        }
        return null;
    }
    private void zig() {

    }
    private void zigZig() {

    }
    private void zigZag() {

    }

}
