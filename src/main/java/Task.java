public class Task {
    protected String objective;
    protected boolean done;

    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmarked() {
        this.done = false;
    }

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + " " + this.objective;
    }
}


