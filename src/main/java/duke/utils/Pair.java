package duke.utils;

public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Creates a new Pair with the two input elements.
     *
     * @param first The first element of the Pair.
     * @param second The second element of the Pair.
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
