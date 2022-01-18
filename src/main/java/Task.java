public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String switchMark() {
        this.isDone = !this.isDone;
        if (isDone) {
            return "Nice! I've marked this task as done:\n  " + toString();
        }
        return "OK, I've marked this task as not done yet:\n  " + toString();
    }

    public String getStatusIcon() {
        return isDone? "X": " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }
}
