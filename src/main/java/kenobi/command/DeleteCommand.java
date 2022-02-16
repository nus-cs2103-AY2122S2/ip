package kenobi.command;

import kenobi.task.Task;

/**
 * The DeleteCommand class encapsulates the command to delete a Task from a given TaskList.
 */
public class DeleteCommand extends Command {
    private int toDeleteIndex;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     * @param index
     */
    public DeleteCommand(int index) {
        toDeleteIndex = index - 1;
    }

    /**
     * Executes the deletion of a Task into the TaskList.
     *
     * @return a feedback from the execution of the command.
     */
    @Override
    public String execute() {
        try {
            Task deletedTask = tasks.remove(toDeleteIndex);
            return "I removed the following task:\n" + deletedTask;
        } catch (IndexOutOfBoundsException e) {
            return "The task you are trying to delete is not in the archives.";
        }
    }
}
