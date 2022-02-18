package duke.tasks;

/**
 * Represents a todo task. A task that needs to be done but has no specific deadline.
 */
public class Todo extends Task {

    /**
     * Instantiates a new To-do by taking in a description.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method that converts a todo to its storage file format.
     * This format is what you will see in the storage file stored in the data folder.
     * @return A string that describes a todo with its storage file format.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }

    /**
     * Method that converts a todo to its UI format.
     * This format is what will be shown to the user on the GUI.
     * @return A string that describes a todo with its UI format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
