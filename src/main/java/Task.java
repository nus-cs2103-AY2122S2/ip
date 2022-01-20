public class Task {
    private final String name;
    private boolean marked;

    Task(String name) {
        this.marked = false;
        this.name = name;
    }

    public void mark() {
        this.marked = true;
    }

    @Override
    public String toString() {
        return "[" + (marked? "X" : " ") + "] " + name;
    }
}
