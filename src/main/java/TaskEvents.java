public class TaskEvents extends Task {
    String startAt;

    public TaskEvents(boolean isDone, String name, String startAt) {
        super(isDone, name);
        this.startAt = startAt;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", name, startAt);
    }
}
