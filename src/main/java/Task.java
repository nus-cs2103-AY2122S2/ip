public class Task {
    private String objective;
    private boolean done;

    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + " " + this.objective;
    }
}
