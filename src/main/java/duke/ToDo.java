package duke;

/**
 * Represents a task without a time or date and has the same behavior as parent class duke.Task.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class ToDo extends Task {

    /**
     * Constructor for Todo class
     *
     * @param d a string representing a description of the task
     */
    public ToDo(String d) {
        super(d);
    }


    /**
     * Returns the task properties in the format of the task to be saved onto hard disk
     *
     * @return String representing the task toString in hard-disk format
     */
    @Override
    public String toStringInFileFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
