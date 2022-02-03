package duke.task;

/**
 * Inherit from Task.
 * This is an EventTask.
 * Has extra deadline field to capture the date of event of this task.
 */
public class EventTask extends Task {
    private String deadline;

    public EventTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public EventTask(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(%s)", this.deadline.replaceFirst(" ", ": "));
    }
}
