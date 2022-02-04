package duke;

/**
 * This is a child class of Task, Todo.
 * Todo class accepts only a title and if it is done as parameter
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class ToDo extends Task {

    /**
     * Creates Todo object
     * @param name of the Task (description)
     * @param done whether the Task is completed or not
     */
    public ToDo(String name, int done) {
        super(name, done);
        super.type = 'T';
    }

    /**
     * Producing a user-friendly view of a Todo Task's information
     */
    @Override
    public String toString() {
        StringBuilder successMessage = new StringBuilder();
        successMessage.append(getTaskIcon()).append(" - ");
        successMessage.append(done).append(" - ");
        successMessage.append(name).append("\n");;
        return successMessage.toString();
    }
}
