package ari.command;

import ari.tasks.Task;

/**
 * Deletes a Task from TaskList
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private static final String DELETE_MESSAGE = "Yes Master, I have removed this task:\n" + "\t    %s\n"
            + "\tYou have %d task(s) currently";
    private static final String EMPTY_MESSAGE = "Sorry Master, the item you chose is not in the list";

    private int indexToDelete;

    public DeleteCommand(int index) {
        this.indexToDelete = index - 1;
    }

    @Override
    public String execute() {
        if (indexToDelete < 0 || indexToDelete >= taskList.getSize()) {
            return EMPTY_MESSAGE;
        }

        Task task = taskList.getTask(indexToDelete);
        taskList.deleteTask(indexToDelete);
        return String.format(DELETE_MESSAGE, task, taskList.getSize());
    }
}
