package duke.task;

/**
 * Inherit from Task.
 * This is a DeadlineTask.
 * Has extra deadline field for the deadline of this task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * DeadlineTask constructor.
     *
     * @param taskName A String parameter to capture the name of this task.
     * @param deadline A String parameter to capture this deadline of this task.
     */
    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * DeadlineTask constructor.
     *
     * @param taskName A String parameter to capture the name of this task.
     * @param deadline A String parameter to capture this deadline of this task.
     * @param isDone A boolean parameter to capture if this task is done or not done.
     */
    public DeadlineTask(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(%s)", this.deadline.replaceFirst(" ", ": "));
    }
}
