public class Event extends Task {

    protected String at;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getTaskData() {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        if (at != null) {
            return "E " + isDone + " " + this.description + "/at " + this.at + "\n";
        } else {
            return "E " + isDone + " " + this.description + "\n";
        }
    }

    @Override
    public String toString() {
        if (at != null) {
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString();
        }
    }
}
