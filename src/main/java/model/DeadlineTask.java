package model;

public class DeadlineTask extends Task{
    private final String deadlineTime;

    public DeadlineTask(String task, String deadlineTime) {
        super(task);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadlineTime + ")";
    }
}
