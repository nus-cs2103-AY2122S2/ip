package duke.misc;

/**
 * Simple class to return two objects from a method instead of one.
 */
public class Pair<T, V> {
    private final T first;
    private final V second;

    /**
     * Creates a Pair object.
     *
     * @param first First item.
     * @param second Second item.
     */
    public Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first item.
     * @return First item.
     */
    public T first() {
        return first;
    }

    /**
     * Returns the second item.
     * @return Second item.
     */
    public V second() {
        return second;
    }
}
