package duke.task;

import java.util.StringTokenizer;

/**
 * Tasks without any date/time attached to it.
 *
 * <p>e.g., visit new theme park.</>
 */
public class Todo extends Task {
    static final char TODO_SYMBOL = 'T';

    /**
     * Default constructor for Todo.
     *
     * <p>Calls super class, Task, default constructor.</>
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
        return TODO_SYMBOL + "|" + isDone + "|" + taskDescription + "\n";
    }

    /**
     * Extracts and initializes data to Todo task from a string.
     *
     * @param data The string to extract data and initialize data from.
     *             Data format: T|true/false|taskDescription
     */
    @Override
    public void extractFileData(String data) {
        StringTokenizer st = new StringTokenizer(data, "|");

        st.nextToken(); // remove the type symbol
        isDone = Boolean.parseBoolean(st.nextToken());
        taskDescription = st.nextToken();
    }

    /**
     * Gets the Todo information in string format.
     *
     * @return Todo information in the following string format:
     * [T][ ] taskDescription
     */
    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + super.toString();
    }
}
