public class Task {
    private final String name;
    private boolean isDone;

    public Task(String taskName) {
        this.name = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isDone ? 'X' : ' ', this.name);
    }
}
