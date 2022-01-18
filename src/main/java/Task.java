public class Task {
    private final String name;
    private final boolean done;
    // immutable class for best practice

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public Task(String name) {
        this(name, false);
    }

    public Task mark() {
        return new Task(this.name, !this.done);
    }

    @Override
    public String toString() {
        return "[" + (this.done? "X" : " ") + "] " + this.name;
    }

    public boolean getDone() {
        return this.done;
    }
}
