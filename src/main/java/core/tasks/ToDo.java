package core.tasks;

import core.exceptions.ToDoEmptyException;
import utilities.OutputFormatter;

/**
 * The ToDo class.
 *
 * @author s7manth
 * @version 0.1
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     *
     * @param description The description of the ToDo task.
     */
    private ToDo(String description) {
        super(description);
    }

    /**
     * Factory method to obtain an instance of the ToDo class.
     *
     * @param description The description of the ToDo task.
     * @return An instance of the ToDo task with the given description.
     * @throws ToDoEmptyException Throws an exception if the description is blank or empty.
     */
    public static ToDo getInstance(String description) throws ToDoEmptyException {
        if (description.isEmpty() || description.isBlank()) {
            throw new ToDoEmptyException();
        }
        return new ToDo(description);
    }

    /**
     * Method to obtain the label for the ToDo class.
     *
     * @return The label of ToDo.
     */
    @Override
    public String getLabel() {
        return "T";
    }

    /**
     * Obtains a string format of the ToDo task.
     *
     * @return The formatted string to represent the ToDo task.
     */
    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[T]", super.toString());
        return outputFormatter.getFormattedOutput();
    }
}
