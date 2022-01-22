public class Event extends Task {
    private final String eventTime;
    private static final String taskType = "E";
    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public Event(String name, String eventTime, boolean isDone) {
        super(name, isDone);
        this.eventTime = eventTime;
    }

    public Event markAsDone() {
        return new Event(this.name, this.eventTime, true);
    }

    public Event markAsUndone() {
        return new Event(this.name, this.eventTime, false);
    }

    public String convertToStoredListFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|%s", taskType, doneIndicator, super.name, this.eventTime);
        return storedListFormat;
    }

    @Override
    public String toString() {
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
