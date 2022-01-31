package duke.task;

/**
 * Inherit from Task.
 * This is an EventTask.
 * Has extra time field to capture the date of event of this task.
 */
public class EventTask extends Task {
    private String time;

    public EventTask(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public EventTask(String taskName, String time, boolean isDone) {
        super(taskName, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(%s)", this.time.replaceFirst(" ", ": "));
    }
}
