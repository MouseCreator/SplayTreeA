import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Клас розширюваного дерева
 * @param <T> - дані, якого типу потрібно зберігати у дереві
 */
public final class SplayTree<T> {
    private final Comparator<T> comparator; //клас для порівняння об'єктів в класі

    /**
     * Конструктор дерева відносно кореня
     * @param root - новий корінь дерева
     * @param comparator - клас порівняння
     */
    SplayTree(TreeNode<T> root, Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = root;
    }
    public Comparator<T> getComparator() {
        return comparator;
    }
    TreeNode<T> root; //кореневий вузол
    public SplayTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Пошук ключа в дереві (з переміщенням його в корінь)
     * @param value - значення, що потрібно знайти
     * @return true, якщо значення наявне у дереві
     */
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
                current = current.getRight();
            }
        }
        return false;
    }

    /**
     * Вилучення вузла з дерева
     * @param value - значення, яке потрібно вилучити
     * @throws NoSuchElementException - якщо значення відсутнє у дереві
     */
    public void remove(T value) {
        TreeNode<T> target = findNode(value);
        splay(target);
        if (this.root.hasLeft() && this.root.hasRight()) {
            SplayTree<T> other = new SplayTree<>(root.getRight(), comparator);
            this.root = root.getLeft();
            this.mergeWith(other);
        } else {
            if (this.root.hasLeft()) {
                this.root = root.getLeft();
            } else {
                this.root = root.getRight();
            }
            this.root.setParent(null);
        }

    }
    /**
     * Поділ дерева відносно елемента
     * @param value - значення, по якому ділиться дерево
     * @return більше піддерево
     */
    public SplayTree<T> split(T value) {
        TreeNode<T> target = findToSplit(value);
        splay(target);
        SplayTree<T> other;
        if (this.root.isGreater(value)) {
            other = new SplayTree<>(root.getRight(), comparator);
            this.root.setRight(null);
        } else {
            other = new SplayTree<>(root.getLeft(), comparator);
            this.root.setLeft(null);
        }
        other.root.setParent(null);
        return other;
    }

    /**
     * Пошук ключа у дереві
     * @param value - значення, яке потрібно знайти
     * @return вузол, у якому знаходиться значення
     */
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

    /**
     * Об'єднання дерева з більшим деревом.
     * @param bigger - дерево для об'єднання.
     * @throws IllegalArgumentException - якщо не виконується умова про те, що інше дерево є більшим
     */
    public void mergeWith(SplayTree<T> bigger) {
        findMax();
        if (this.root.isGreater(bigger.getMin())) {
            throw new IllegalArgumentException("The other tree is not greater than target tree in merge");
        }
        root.setRight(bigger.root);
    }

    /**
     * Додавання значення у корінь дерева
     * @param value - значення, що потрібно додати
     */
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

    /**
     * Пошук вузла для вставки значення
     * @param value - значення, що потрібно знайти
     * @return вузол, після якого має бути вставлене нове значення
     */
    private TreeNode<T> findToInsert(T value) {
        TreeNode<T> current = root;
        while (true) {
            if (current.isLower(value)) {
                if (current.hasRight())
                    current=current.getRight();
                else
                    return current;
            } else {
                if (current.hasLeft())
                    current=current.getLeft();
                else
                    return current;
            }
        }
    }

    /**
     * Пошук вузла для вставки значення
     * @param value - значення, що потрібно знайти
     * @return вузол, після якого має бути вставлене нове значення
     */
    private TreeNode<T> findToSplit(T value) {
        TreeNode<T> current = root;
        while (true) {
            if (current.isValueOf(value)) {
                return current;
            }
            if (current.isLower(value)) {
                if (current.hasRight())
                    current=current.getRight();
                else
                    return current;
            } else {
                if (current.hasLeft())
                    current=current.getLeft();
                else
                    return current;
            }
        }
    }
    /**
     * Пошук максимального елемента у дереві
     */
    private void findMax() {
        TreeNode<T> current = root;
        while (current.hasRight()) {
            current = current.getRight();
        }
        splay(current);
    }

    /**
     * Пошук мінімального елемента у дереві (без splay)
     */
    private T getMin() {
        TreeNode<T> current = root;
        while (current.hasRight()) {
            current = current.getRight();
        }
        return current.getValue();
    }

    private void splay(TreeNode<T> targetNode) {
        while (!targetNode.isRoot()) {
            targetNode = targetNode.splay();
        }
        this.root = targetNode;
    }

    /**
     * Симетричний обхід дерева
     * @return предствлення дерева як відсортованого списку його компонентів
     */
    public String print() {
        if (root == null)
            return "Empty tree";
        return "[" + root.print() + "]";
    }

    /**
     * Видалення усіх елементів з дерева
     */
    public void clear() {
        if (root == null)
            return;
        root.clear();
        root=null;
    }

    /**
     *
     * @return Кількість елементів у дереві
     */
    public int size() {
        return root == null ? 0 : root.size();
    }
}
