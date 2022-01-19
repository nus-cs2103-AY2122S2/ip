/**
 * Tasks that does not have any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Contrustor to create a deadline task.
     *
     * @param description text description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Outputs the string representation of ToDo with
     * description.
     *
     * @return formatted string representing ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}