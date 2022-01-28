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
        if (this.status == 0) {
            return "[" + type + "][ ] " + activity;
        } else {
            return "[" + type + "][X] " + activity;
        }
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|\n";
    }

}
