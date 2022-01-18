public class Event extends Task {
    private final String eventTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public Event(String name, String eventTime, boolean isDone) {
        super(name, isDone);
        this.eventTime = eventTime;
    }

    public Deadline markAsDone() {
        return new Deadline(this.name, this.eventTime, true);
    }

    public Deadline markAsUndone() {
        return new Deadline(this.name, this.eventTime, false);
    }

    @Override
    public String toString() {
        String taskType = "E";
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        return String.format("[%s][%s] %s (at: %s)",
                taskType, doneMark, super.name, eventTime);
    }
}
