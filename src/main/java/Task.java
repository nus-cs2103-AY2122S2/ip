public class Task {

    public boolean done = false;
    public String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setDone(boolean newDone) {
        this.done = newDone;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.taskName);
    }
}