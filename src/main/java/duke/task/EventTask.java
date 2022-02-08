package duke.task;

/**
 * Inherit from Task.
 * This is an EventTask.
 * Has extra deadline field to capture the date of event of this task.
 */
public class EventTask extends Task {
    private String deadline;

    /**
     * EventTask constructor.
     *
     * @param taskName A String parameter to capture the name of this task.
     * @param deadline A String parameter to capture this deadline of this task.
     */
    public EventTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * EventTask constructor.
     *
     * @param taskName A String parameter to capture the name of this task.
     * @param deadline A String parameter to capture this deadline of this task.
     * @param isDone A boolean parameter to capture if this task is done or not done.
     */
    public EventTask(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(%s)", this.deadline.replaceFirst(" ", ": "));
    }
}
