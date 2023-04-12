/**
 * Вузол розширюваного дерева
 * @param <T> - тип даних для зберігання у дереві
 */
class TreeNode<T> {
    private final T value; //значення ключа
    private TreeNode<T> left; //лівий син
    private TreeNode<T> right; //правий син
    private TreeNode<T> parent; //батько

    private final SplayTree<T> tree; //вказівник на дерево (для порівнянь)

    /**
     * Конструктор вузла по значенню
     * @param value - значення
     * @param parent - батько вузла
     * @param tree - дерево, якому належить вузол
     */
    public TreeNode(T value, TreeNode<T> parent, SplayTree<T> tree) {
        this.left = null;
        this.value = value;
        this.tree = tree;
        this.parent = parent;
    }

    /**
     *
     * @return true, якщо вузол є коренем дерева
     */
    boolean isRoot() {
        return this.parent == null;
    }

    /**
     *
     * @return true, якщо вузол має лівого сина
     */
    public boolean hasLeft() {
        return left != null;
    }
    /**
     *
     * @return true, якщо вузол має правого сина
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     *
     * @return рядкове представлення вузла
     */
    @Override
    public String toString() {
        return "[T=" + value.toString() + "]";
    }

    /**
     *
     * @param value - значення для порівняння
     * @return true, якщо вузол має значення рівне за {@param value}
     */
    boolean isValueOf(T value) {
        return tree.getComparator().compare(this.value, value) == 0;
    }

    /**
     *
     * @param value - значення для порівняння
     * @return true, якщо вузол має значення більше за {@param value}
     */
    boolean isGreater(T value) {
        return tree.getComparator().compare(this.value, value) > 0;
    }
    /**
     *
     * @param value - значення для порівняння
     * @return true, якщо вузол має значення менше за {@param value}
     */
    boolean isLower(T value) {
        return tree.getComparator().compare(this.value, value) < 0;
    }

    /**
     * Відновлення балансу дерева, перенесення певного вузла у корінь
     * @return активний вузол, що переноситься у корінь, у ближчій позиції до кореня відносно заданого вузла
     */
    public TreeNode<T> splay() {
        assert !isRoot();
        if (isLeftChild())
            return parent.leftSplay();
        if (isRightChild())
            return parent.rightSplay();
        throw new IllegalStateException("Target node is neither a root nor a child node!");
    }

    /**
     *
     * @return true якщо даний вузол є правим сином свого батька. false інакше або якщо батька немає
     */
    private boolean isRightChild() {
        if (isRoot())
            return false;
        return this == parent.right;
    }
    /**
     *
     * @return true якщо даний вузол є лівим сином свого батька. false інакше або якщо батька немає
     */
    private boolean isLeftChild() {
        if (isRoot())
            return false;
        return this == parent.left;
    }
    /**
     * splay при умові, що активний вузол є лівим сином
     * @return активний вузол операції splay
     */
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
    /**
     * splay при умові, що активний вузол є правим сином
     * @return активний вузол операції splay
     */
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
        throw new IllegalStateException("Node is neither a root nor a child node.");
    }

    /**
     * Утворення зв'язку між батьком та лівим сином
     * @param parentNode - батьківський вузол
     * @param childNode - синівський вузол
     */
    private void setLeftChild(TreeNode<T> parentNode, TreeNode<T> childNode) {
        parentNode.left = childNode;
        if (childNode != null)
            childNode.parent = parentNode;
    }

    /**
     * Утворення зв'язку між батьком та правим сином
     * @param parentNode - батьківський вузол
     * @param childNode - синівський вузол
     */
    private void setRightChild(TreeNode<T> parentNode, TreeNode<T> childNode) {
        parentNode.right = childNode;
        if (childNode != null)
            childNode.parent = parentNode;
    }

    /**
     * Операція zig - переміщення активного вузла в корінь (зліва)
     * @return активний вузол
     */
    private TreeNode<T> zigLeft() {
         assert isRoot();
         TreeNode<T> XNode = this.left;
         rotateLeft(this, XNode);
         XNode.parent=null;
         return XNode;
    }

    /**
     * Поворот наліво
     * @param upper - батьківський вузол
     * @param lower - синівський вузол
     */
    private void rotateLeft(TreeNode<T> upper, TreeNode<T> lower) {
        setLeftChild(upper, lower.right);
        setRightChild(lower, upper);
    }
    /**
     * Поворот направо
     * @param upper - батьківський вузол
     * @param lower - синівський вузол
     */
    private void rotateRight(TreeNode<T> upper, TreeNode<T> lower) {
        setRightChild(upper, lower.left);
        setLeftChild(lower, upper);
    }
    /**
     * Операція zig - переміщення активного вузла в корінь (справа)
     * @return активний вузол
     */
    private TreeNode<T> zigRight() {
        assert isRoot();
        TreeNode<T> XNode = this.right;
        rotateRight(this, XNode);
        XNode.parent=null;
        return XNode;
    }

    /**
     * Зміна батьківського вузла у активного вузла при zig-zig та zig-zag
     * @param XNode - активний вузол
     * @param GNode - дідусь активного вузла
     */
    private void replaceParent(TreeNode<T> XNode, TreeNode<T> GNode) {
        TreeNode<T> GParent = GNode.parent;
        if (GParent == null) {
            XNode.parent = null;
            return;
        }
        if (GNode.isLeftChild()) {
            setLeftChild(GParent, XNode);
        } else {
            setRightChild(GParent, XNode);
        }

    }

    /**
     * zig-zig - операція переміщення активного вузла, якщо зверху є два ліві зв'язки
     * @return активний вузол
     */
    private TreeNode<T> zigZigLeft() {
        assert !isRoot();
        TreeNode<T> XNode = this.left;
        TreeNode<T> GNode = this.parent;
        replaceParent(XNode, GNode);
        rotateLeft(GNode, this);
        rotateLeft(this, XNode);
        return XNode;
    }
    /**
     * zig-zig - операція переміщення активного вузла, якщо зверху є два праві зв'язки
     * @return активний вузол
     */
    private TreeNode<T> zigZigRight() {
        assert !isRoot();
        TreeNode<T> XNode = this.right;
        TreeNode<T> GNode = this.parent;
        replaceParent(XNode, GNode);
        rotateRight(GNode, this);
        rotateRight(this, XNode);
        return XNode;
    }
    /**
     * zig-zag - операція переміщення активного вузла, якщо зверху спочатку лівий, потім правий зв'язок
     * @return активний вузол
     */
    private TreeNode<T> zigZagLeft() {
        assert !isRoot();
        TreeNode<T> XNode = this.left;
        TreeNode<T> GNode = this.parent;
        replaceParent(XNode, GNode);
        rotateLeft(this, XNode);
        rotateRight(GNode, XNode);
        return XNode;
    }
    /**
     * zig-zag - операція переміщення активного вузла, якщо зверху спочатку правим, потім лівий зв'язок
     * @return активний вузол
     */
    private TreeNode<T> zigZagRight() {
        assert !isRoot();
        TreeNode<T> XNode = this.right;
        TreeNode<T> GNode = this.parent;
        replaceParent(XNode, GNode);
        rotateRight(this, XNode);
        rotateLeft(GNode, XNode);
        return XNode;
    }

    /**
     *
     * @return ліве піддерево даного вузла
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     *
     * @return праве піддерево даного вузла
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * Установлює значення правого піддерева
     * @param subtree - нове значення правого піддерева
     */
    public void setRight(TreeNode<T> subtree) {
        this.right = subtree;
    }
    /**
     * Установлює значення лівого піддерева
     * @param subtree - нове значення лівого піддерева
     */
    public void setLeft(TreeNode<T> subtree) {
        this.left = subtree;
    }

    /**
     *
     * @return вивід вузла та піддерев як список упорядкованих даних
     */
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

    /**
     *
     * @return кількість вузлів у піддереві з коренем у даному вузлі (для листів = 1)
     */
    public int size() {
        int mySize = 1;
        if (hasLeft()) {
           mySize += left.size();
        }
        if (hasRight()) {
            mySize += right.size();
        }
        return mySize;
    }

    /**
     * Очищення вузла та піддерев
     */
    public void clear() {
        if (hasLeft()) {
            left.parent=null;
            left.clear();
            left=null;
        }
        if (hasRight()) {
            right.parent=null;
            right.clear();
            right=null;
        }
    }

    /**
     * Установлює нове значення батьківського вузла
     * @param treeNode - нове значення батька
     */
    public void setParent(TreeNode<T> treeNode) {
        this.parent = treeNode;
    }

    /**
     *
     * @return значення, що зберігається у вузлі
     */
    public T getValue() {
        return value;
    }

    /**
     * Вивід вузла та піддерев у табульованому вигляді
     * @param tab - рівень табуляції
     * @return рядкове представлення вузла
     */
    public String asTree(int tab) {
        String result = "\t".repeat(tab);
        result += value.toString();
        result += "\n";
        if (hasLeft()) {
            result += left.asTree(tab+1);
        } else {
            result += "\t".repeat(tab+1) + "-\n";
        }
        if (hasRight()) {
            result += right.asTree(tab+1);
        } else {
            result += "\t".repeat(tab+1) + "-\n";
        }
        return result;
    }
}

