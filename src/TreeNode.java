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
    boolean isRoot() {
        return this.parent == null;
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
    boolean isValueOf(T value) {
        return tree.getComparator().compare(this.value, value) == 0;
    }
    boolean isGreater(T value) {
        return tree.getComparator().compare(this.value, value) > 0;
    }
    boolean isLower(T value) {
        return tree.getComparator().compare(this.value, value) < 0;
    }
    public TreeNode<T> splay() {
        assert !isRoot();
        if (isLeftChild())
            return parent.leftSplay();
        if (isRightChild())
            return parent.rightSplay();
        throw new IllegalStateException("Target node is neither a root nor a child node!");
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
        throw new IllegalStateException("Node is neither a root nor a child node.");
    }
    private TreeNode<T> rightSplay() {
        if (isRoot()) {
            return zigRight();
        }
        if (isRightChild()) {
            return zigZigRight();
        }
        if (isLeftChild()) {
            return zigZagLeft();
        }
        throw new IllegalStateException("Node is neither a root nor a child node.");
    }
    private void setLeftChild(TreeNode<T> parentNode, TreeNode<T> childNode) {
        parentNode.left = childNode;
        if (childNode != null)
            childNode.parent = parentNode;
    }
    private void setRightChild(TreeNode<T> parentNode, TreeNode<T> childNode) {
        parentNode.right = childNode;
        if (childNode != null)
            childNode.parent = parentNode;
    }
    private TreeNode<T> zigLeft() {
         assert isRoot();
         TreeNode<T> XNode = this.left;
         rotateLeft(this, XNode);
         XNode.parent=null;
         return XNode;
    }

    private void rotateLeft(TreeNode<T> upper, TreeNode<T> lower) {
        setLeftChild(upper, lower.right);
        setRightChild(lower, upper);
    }
    private void rotateRight(TreeNode<T> upper, TreeNode<T> lower) {
        setRightChild(upper, lower.left);
        setLeftChild(lower, upper);
    }

    private TreeNode<T> zigRight() {
        assert isRoot();
        TreeNode<T> XNode = this.right;
        rotateRight(this, XNode);
        XNode.parent=null;
        return XNode;
    }
    private TreeNode<T> zigZigLeft() {
        assert !isRoot();
        TreeNode<T> XNode = this.left;
        TreeNode<T> GNode = this.parent;
        XNode.parent=GNode.parent;
        rotateLeft(GNode, this);
        rotateLeft(this, XNode);
        return XNode;
    }
    private TreeNode<T> zigZagLeft() {
        assert !isRoot();
        TreeNode<T> XNode = this.left;
        TreeNode<T> GNode = this.parent;
        XNode.parent=GNode.parent;
        rotateLeft(this, XNode);
        rotateRight(GNode, XNode);
        return XNode;
    }
    private TreeNode<T> zigZagRight() {
        assert !isRoot();
        TreeNode<T> XNode = this.right;
        TreeNode<T> GNode = this.parent;
        XNode.parent=GNode.parent;
        rotateRight(this, XNode);
        rotateLeft(GNode, XNode);
        return XNode;
    }

    private TreeNode<T> zigZigRight() {
        assert !isRoot();
        TreeNode<T> XNode = this.right;
        TreeNode<T> GNode = this.parent;
        XNode.parent=GNode.parent;
        rotateRight(GNode, this);
        rotateRight(this, XNode);
        return XNode;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }


    public void setRight(TreeNode<T> subtree) {
        this.right = subtree;
    }

    public boolean isNotLeaf() {
        return hasLeft() || hasRight();
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public String print() {
        String result = "";
        if (hasLeft()) {
            result += left.print();
            result += ", ";
        }
        result += this.value.toString();
        if (hasRight()) {
            result += ", ";
            result += right.print();
        }
        return result;
    }
}

