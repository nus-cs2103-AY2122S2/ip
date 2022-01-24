package kidsnd274.duke.taskobjects;

public class Deadline extends TaskWithDate {
    public Deadline(String name, String deadline) {
        super(name, deadline);
    }

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
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
    public Types getType() {
        return Types.DEADLINE;
    }

    @Override
    public String getDetails() {
        return deadline;
    }
}
