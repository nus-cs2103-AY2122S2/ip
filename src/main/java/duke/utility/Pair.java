package duke.utils;

public class Pair<V, W> {
    private V first;
    private W second;

    public Pair(V first, W second) {
        this.first = first;
        this.second = second;
    }

    public W getSecond() {
        return second;
    }

    public V getFirst() {
        return first;
    }

}
