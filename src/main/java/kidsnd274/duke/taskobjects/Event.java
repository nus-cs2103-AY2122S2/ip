package kidsnd274.duke.taskobjects;

public class Event extends TaskWithDate {
    public Event(String name, String time) {
        super(name, time);
    }

    public Event(String name, boolean isDone, String time) {
        super(name, isDone, time);
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + super.getFormattedDate() + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[E]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.EVENT;
    }
}
