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
            if (targetNode != null) {
                this.right = targetNode;
                return rightSplay();
            }
        }
        if (hasLeft()) {
            TreeNode<T> targetNode = left.splay(target);
            if (targetNode != null) {
                this.left = targetNode;
                return leftSplay();
            }
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
    private TreeNode<T> leftSplay() {
        if (isRoot()) {
            return zigLeft();
        }
        if (isLeftChild()) {
            return zigZigLeft();
        }
        if (isRightChild()) {
            return zigZagLeft();
        }
        throw new IllegalStateException("Node is not a root and a child node.");
    }
    private TreeNode<T> rightSplay() {
        if (isRoot()) {
            return zigRight();
        }
        if (isRightChild()) {
            return zigZigRight();
        }
        if (isLeftChild()) {
            return zigZagRight();
        }
        throw new IllegalStateException("Node is not a root and a child node.");
    }
    private void setLeftChild(TreeNode<T> parentNode, TreeNode<T> childNode) {
        parentNode.left = childNode;
        childNode.parent = parentNode;
    }
    private void setRightChild(TreeNode<T> parentNode, TreeNode<T> childNode) {
        parentNode.right = childNode;
        childNode.parent = parentNode;
    }
    private TreeNode<T> zigLeft() {
         assert isRoot();
         TreeNode<T> XNode = this.left;
         tree.setRoot(XNode);
         setLeftChild(this, XNode.right);
         setRightChild(XNode, this);
         return XNode;
    }
    private TreeNode<T> zigRight() {
        assert isRoot();
        TreeNode<T> XNode = this.right;
        tree.setRoot(XNode);
        setRightChild(this, XNode.right);
        setLeftChild(XNode, this);
        return XNode;
    }
    private TreeNode<T> zigZigLeft() {
        assert !isRoot();
        return null;
    }
    private TreeNode<T> zigZagLeft() {
        assert !isRoot();
        return null;
    }
    private TreeNode<T> zigZagRight() {
        assert !isRoot();
        return null;
    }

    private TreeNode<T> zigZigRight() {
        assert !isRoot();
        return null;
    }
}

