package kidsnd274.duke.taskobjects;

public class Event extends TaskWithDate {
    public Event(String name, String time) {
        super(name, time);
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + super.getDate() + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[E]" + super.getCurrentStatus();
    }

    @Override
    public String getType() {
        return "Event Task";
    }
}
