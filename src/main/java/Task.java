public class Task {
    boolean done;
    String task;

    Task(String action) {
        this.done = false;
        this.task = action;
    }

    void mark() {
        this.done = true;
    }

    void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
