package duke.misc;

/**
 * Simple class to return two objects from a method instead of one
 */
public class Pair<T, V> {
    private final T first;
    private final V second;

    /**
     * Default constructor for Pair
     *
     * @param first First item
     * @param second Second item
     */
    public Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public V second() {
        return second;
    }
}
