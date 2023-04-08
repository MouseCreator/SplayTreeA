public class TreeNode<T> {
    private T value;
    private TreeNode<T> left;

    private TreeNode<T> right;

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

}
