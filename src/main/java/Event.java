public class Event extends Task {

    public Duke.TaskType type = Duke.TaskType.EVENT;
    public String taskName;
    public String eventTime;

    public Event(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.done ? "X" : " ", this.taskName, this.eventTime);
    }

    public String exportToString() {
        return String.format("%s %s %s %s", this.type, this.taskName, this.done, this.eventTime);
    }
}