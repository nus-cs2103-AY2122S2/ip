public abstract class Task {
    private final String name;
    private boolean done;

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public Task(String name) {
        this(name, false);
    }

    public void mark() {
        this.done = !this.done;
    }

    @Override
    public String toString() {
        return "[" + (this.done? "X" : " ") + "] " + this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

    public abstract String toText();
}
