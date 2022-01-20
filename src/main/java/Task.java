public class Task {
    boolean done;
    String task;
    String type;

    Task(String task, String type) {
        this.done = false;
        this.task = task;
        this.type = type;
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
            return "[" + this.type + "] [X] " + this.task;
        } else {
            return "[" + this.type + "] [ ] " + this.task;
        }
    }
}
