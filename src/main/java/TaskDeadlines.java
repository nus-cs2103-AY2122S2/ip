public class TaskDeadlines extends Task {
    String doneBy;

    public TaskDeadlines(boolean isDone, String name, String doneBy) {
        super(isDone, name);
        this.doneBy = doneBy;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", name, doneBy);
    }
}
