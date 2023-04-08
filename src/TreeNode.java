public class TreeNode<T> {
    private final T value;
    private TreeNode<T> left;
    private final SplayTree<T> tree;
    private TreeNode<T> right;
    private TreeNode<T> parent;

    public TreeNode(T value, TreeNode<T> parent, SplayTree<T> tree) {
        this.left = null;
        this.value = value;
        this.tree = tree;
        this.parent = parent;
    }
    private boolean isRoot() {
        return this==tree.root;
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
        if (hasRight()) {
            TreeNode<T> targetNode = right.splay(target);
        }
        if (hasLeft()) {
            TreeNode<T> targetNode = left.splay(target);
        }
        return null;
    }

    private boolean isRightChild() {
        if (isRoot())
            return false;
        return this == parent.right;
    }
    private boolean isLeftChild() {
        if (isRoot())
            return false;
        return this == parent.left;
    }
    private void leftSplay() {
        if (isRoot()) {
            zigLeft();
            return;
        }
        if (isLeftChild()) {
            zigZigLeft();
            return;
        }
        if (isRightChild()) {
            zigZagLeft();
            return;
        }
        throw new IllegalStateException("Node is not a root and a child node.");
    }
    private void rightSplay() {
        if (isRoot()) {
            zigRight();
            return;
        }
        if (isRightChild()) {
            zigZigRight();
            return;
        }
        if (isLeftChild()) {
            zigZagRight();
            return;
        }
        throw new IllegalStateException("Node is not a root and a child node.");
    }
    private void zigZagRight() {
    }

    private void zigZigRight() {
        
    }
    private void zigLeft() {

    }
    private void zigRight() {

    }
    private void zigZigLeft() {

    }
    private void zigZagLeft() {

    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }
}
