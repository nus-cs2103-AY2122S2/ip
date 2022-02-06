package duke.task;

/**
 * Represents a ToDo. A ToDo is a type of task that does not need to be completed by a certain date or time.
 */
public class ToDo extends Task{
    public ToDo(String name) {
        super("T", name);
    }

    /**
     * Overrides toString to display type of Task, whether or not it has been completed, and its name.
     *
     * @return String containing above information
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
