package duke.task;

/**
 * Inherit from Task.
 * This is a DeadlineTask.
 * Has extra deadline field for the deadline of this task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public DeadlineTask(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(%s)", this.deadline.replaceFirst(" ", ": "));
    }
}
