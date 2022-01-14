public class Task {
    String name;
    boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    void mark() {
        this.done = true;
    }

    void unmark() {
        this.done = false;
    }

    public String toString() {
        String doneState = done ? "X" : " ";
        return String.format("[%s] %s", doneState, this.name);
    }
}
