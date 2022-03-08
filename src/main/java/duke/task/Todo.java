package duke.task;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import duke.exception.DukeException;

/**
 * Tasks without any date/time attached to it.
 *
 * <p>e.g., visit new theme park.</p>
 */
public class Todo extends Task {
    public static final char TODO_SYMBOL = 'T';
    private static final String PARSE_FILE_ERROR = "Error reading todo data from file";

    /**
     * Default constructor for Todo.
     *
     * <p>Calls super class, Task, default constructor.</p>
     */
    public Todo() {
        super();
    }

    /**
     * Overloaded constructor for Todo.
     *
     * @param taskDescription Description of Todo task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns Todo data in a defined save file string format.
     *
     * @return A string with Todo data in the defined save file format: <br>
     * T|true/false|taskDescription\n
     */
    @Override
    public String saveFileFormat() {
        return TODO_SYMBOL + "|" + getIsDone() + "|" + getTaskDescription() + "\n";
    }

    /**
     * Extracts and initializes data to Todo task from a string.
     *
     * @param data The string to extract data and initialize data from.
     *             Data format: T|true/false|taskDescription
     */
    @Override
    public void extractDataFromLine(String data) throws DukeException {
        StringTokenizer st = new StringTokenizer(data, "|");

        try {
            st.nextToken(); // remove the type symbol
            setIsDone(Boolean.parseBoolean(st.nextToken()));
            setTaskDescription(st.nextToken());
        } catch (NoSuchElementException execption) {
            throw new DukeException(PARSE_FILE_ERROR);
        }
    }

    /**
     * Gets the Todo information in string format.
     *
     * @return Todo information in the following string format:
     * [T][ ] taskDescription
     */
    @Override
    public String toString() {
        String classification = "[" + TODO_SYMBOL + "]";

        return classification + super.toString();
    }
}
