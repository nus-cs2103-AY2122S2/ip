package kidsnd274.duke.taskobjects;

public class Deadline extends TaskWithDate {
    public Deadline(String name, String deadline) {
        super(name, deadline);
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + super.getDate() + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[D]" + super.getCurrentStatus();
    }

    @Override
    public String getType() {
        return "Deadline Task";
    }
}
