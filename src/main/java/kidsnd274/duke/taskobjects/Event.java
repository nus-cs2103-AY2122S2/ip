package kidsnd274.duke.taskobjects;

public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public Event(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + this.time + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[E]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.EVENT;
    }

    @Override
    public String getDetails() {
        return time;
    }
}
