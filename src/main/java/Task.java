public class Task {
    private String taskName;
    private boolean done;

    Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "]" + " " + this.taskName;
    }

    void setDone() {
        this.done = true;
    }

    void setUndone() {
        this.done = false;
    }
}
