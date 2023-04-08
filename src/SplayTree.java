import java.util.Comparator;
import java.util.NoSuchElementException;

public class SplayTree<T> {
    private final Comparator<T> comparator;

    public SplayTree(TreeNode<T> root, Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = root;
    }

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
        TreeNode<T> target = findNode(value);
        splay(target);
        SplayTree<T> other = new SplayTree<>(root.getRight(), comparator);
        this.root = root.getLeft();
        this.mergeWith(other);
    }

    private TreeNode<T> findNode(T value) {
        TreeNode<T> current = root;
        while (current != null) {
            if (current.isValueOf(value)) {
                return current;
            }
            if (current.isGreater(value)) {
                current=current.getLeft();
            } else if (current.isLower(value)) {
                current=current.getRight();
            }
        }
        throw new NoSuchElementException("Cannot find node with value " + value.toString());
    }

    public void mergeWith(SplayTree<T> bigger) {
        findMax();
        root.setRight(bigger.root);
    }
    public void add(T value) {
        if (root == null) {
            root = new TreeNode<>(value, null, this);
            return;
        }
        TreeNode<T> target = findToInsert(value);
        TreeNode<T> additionalNode = new TreeNode<>(value, target, this);
        if (target.isLower(value)) {
            target.setRight(additionalNode);
        } else {
            target.setLeft(additionalNode);
        }
        splay(additionalNode);
    }
    private TreeNode<T> findToInsert(T value) {
        TreeNode<T> current = root;
        while (true) {
            if (current.isGreater(value)) {
                if (current.hasLeft())
                    current=current.getLeft();
                else
                    return current;
            } else {
                if (current.hasRight())
                    current=current.getRight();
                else
                    return current;
            }
        }
    }

    private void findMax() {
        TreeNode<T> current = root;
        while (current.hasRight()) {
            current = current.getRight();
        }
        splay(current);
    }

    private void splay(TreeNode<T> targetNode) {
        while (!targetNode.isRoot()) {
            targetNode = targetNode.splay();
        }
        this.root = targetNode;
    }

    public String print() {
        if (root == null)
            return "Empty tree";
        return "[" + root.print() + "]";
    }

    public void clear() {
        if (root == null)
            return;
        root.clear();
        root=null;
    }
}
