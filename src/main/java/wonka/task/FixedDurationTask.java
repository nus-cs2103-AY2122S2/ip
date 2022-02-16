package wonka.task;

public class FixedDurationTask extends Task {
    private static final String TYPE = "F";
    private final String name;
    private final String duration;
    private boolean isDone = false;

    /**
     * Constructor for a task that needs takes a fixed duration to be completed.
     * Format: task_name (needs duration)
     * e.g. Doing work (needs 2 hours)
     * @param name Name of the task.
     * @param duration Duration required for task to be completed.
     */
    public FixedDurationTask(String name, String duration) {
        super(name);
        this.name = name;
        this.duration = duration;
    }

    /**
     * Returns the duration required for the task to be completed.
     * @return Duration of the task.
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * Returns the name of the task.
     * @return Name of the task.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the type of the task.
     * @return Type of the task.
     */
    @Override
    public String track() {
        return "[" + TYPE + "]";
    }

    /**
     * Returns the String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return this.name + " " + "(needs " + this.getDuration() + ")";
    }
}
