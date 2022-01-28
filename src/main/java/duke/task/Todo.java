package duke.task;

/**
 * A task that needs to be done.
 */
public class Todo extends Task {

    /**
     * Constructor for a to-do task.
     * @param activity the activity that needs to be done.
     */
    public Todo(String activity) {
        super(activity, "T");
    }

    /** {@inheritDoc} */
    @Override
    public String printTask() {
        if (this.status) {
            return "[" + type + "][X] " + activity;
        } else {
            return "[" + type + "][ ] " + activity;
        }
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|\n";
    }

}
