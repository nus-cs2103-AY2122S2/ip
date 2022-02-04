package meep.task;


/**
 * Represents the todo task.
 */
public class ToDo extends Task {
    /**
     * Constructor for class ToDo.
     *
     * @param title title of rodo task.
     */
    public ToDo(String title) {
        super(title);
    }


    /**
     * Constructor for class ToDo.
     *
     * @param title title of todo task.
     * @param done  status of the task.
     */
    public ToDo(String title, boolean done) {
        super(title, done);
    }

    @Override
    public String toString() {

        return "[ T ]" + super.toString();
    }
}
