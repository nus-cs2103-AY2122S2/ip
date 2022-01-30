package duke;

/**
 * This is a child class of duke.Task, Todo.
 * Todo class accepts only a title and if it is complete as parameter
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class ToDo extends Task {

    public ToDo(String name, int isDone) {
        super(name, isDone);
        super.type = 'T';
    }

    @Override
    public String toString() {
        StringBuilder successMessage = new StringBuilder();
        successMessage.append(getTaskIcon()).append(" - ");
        successMessage.append(getIsDoneIcon()).append(" - ");
        successMessage.append(name).append("\n");;
        return successMessage.toString();
    }
}
