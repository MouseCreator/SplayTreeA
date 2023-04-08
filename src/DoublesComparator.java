import java.util.Comparator;

/**
 * Клас для порівняння двох дійсних чисел
 */
public class DoublesComparator implements Comparator<Double> {
    @Override
    public int compare(Double o1, Double o2) {
        if (Math.abs(o1 - o2) < 0.001)
            return 0;
        return (o1 > o2) ?
             1 :-1;
    }
}
