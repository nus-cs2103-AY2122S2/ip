package kidsnd274.duke.taskobjects;

public class Deadline extends Task {
    private String deadline; // can maybe encapsulate to TaskWithDate

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + this.deadline + ")";
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
