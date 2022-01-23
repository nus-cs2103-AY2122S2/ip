public class Event extends Task {

    protected String time;

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    String getTaskType() {
        return "E";
    }

    public String toString() {
        return String.format("[%s]", getTaskType())
                + super.toString()
                + String.format(" (at: %s)", time);
    }
}