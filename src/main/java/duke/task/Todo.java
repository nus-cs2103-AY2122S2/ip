package duke.task;

/**
 * Represents a todo - a task with a description and no time limit
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class Todo extends Task {

    /**
     * Constructor for Todo that initializes the Todo with a given description.
     *
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Default toString method that returns the description of Todo with its completion status and Todo marker.
     *
     * @return formatted string of the description and completeness status of Todo with a Todo marker
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parses contents of Todo into a csv-like format delimited by '|'
     *
     * @return formatted string of Todo, its completion status and a Todo marker
     */
    @Override
    public String writeToFile() {
        return "T | " + super.writeToFile();
    }
}
