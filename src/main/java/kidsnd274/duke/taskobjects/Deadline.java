package kidsnd274.duke.taskobjects;

public class Deadline extends TaskWithDate {
    public Deadline(String name, String deadline) {
        super(name, deadline);
    }

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone, deadline);
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + super.getFormattedDate() + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[D]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.DEADLINE;
    }
}
