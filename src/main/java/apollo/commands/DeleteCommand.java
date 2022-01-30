package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;

/**
 * Deletes a {@code Task} from the taskList.
 * Extends {@code Command} superclass.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructor for {@code DeleteCommand}.
     *
     * @param index Of task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes {@code Task} from the taskList.
     *
     * @return Message for successful execution.
     * @throws ApolloOutOfBoundsException If index supplied is out of bounds.
     */
    @Override
    public String execute() throws ApolloOutOfBoundsException {
        if (index < 0 || index >= taskList.taskCount()) {
            throw new ApolloOutOfBoundsException();
        }
        Task deleted = taskList.deleteTask(index);
        return String.format("Alright, I've removed this task. \n"
                + "  %s\nThere's a total of %d tasks now. ", deleted, taskList.taskCount());
    }
}
