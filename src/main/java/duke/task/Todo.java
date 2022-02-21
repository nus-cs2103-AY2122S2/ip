package duke.task;

/**
 * Represents a todo - a task with a description and no time limit.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class Todo extends Task {

    /**
     * Constructor for Todo specifying Task with description.
     *
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Default toString method that returns the description of Todo.
     * Includes completion status and Todo marker.
     *
     * @return formatted string of the description and completeness
     * status of Todo with a Todo marker.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parses contents of Todo into a csv-like format delimited by '|'.
     *
     * @return formatted string of Todo, its completion status and Todo marker
     */
    @Override
    public String writeToFile() {
        return "T | " + super.writeToFile();
    }
}