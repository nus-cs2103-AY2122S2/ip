package duke.task;

/**
 * Inherit from Task.
 * This is a DeadlineTask.
 * Has extra time field for the deadline of this task.
 */
public class DeadlineTask extends Task {
    private String time;

    public DeadlineTask(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public DeadlineTask(String taskName, String time, boolean isDone) {
        super(taskName, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(%s)", this.time.replaceFirst(" ", ": "));
    }
}
