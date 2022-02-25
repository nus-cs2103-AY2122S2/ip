package jarvis.tasks;

public class Todo extends Task {
    public static final String TASK_CODE = "T";

    /**
     * Constructor for the Todo object.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Get the csv representation of the task.
     *
     * @return string in csv format
     */
    public String toCsv(String delimiter) {
        return String.format("%s,%s,%s", TASK_CODE, this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", TASK_CODE, super.toString());
    }
}
