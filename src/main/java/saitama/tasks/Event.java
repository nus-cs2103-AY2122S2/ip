package saitama.tasks;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getTaskData() {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        return "E " + isDone + " " + this.description + " /at " + this.at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
