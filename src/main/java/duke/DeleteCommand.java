package duke;

/**
 * Runs the command for user to delete a task in the list.
 */

public class DeleteCommand extends Command {

    private int index;

    /**
     * This is a constructor to create a new command for the user.
     * @param index is the index of the object that the user wants to remove.
     */
    DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task chosen by the user.
     *
     * @param taskList The TaskList of the current user.
     * @param ui The user interface to show messages to users.
     * @param storage The file system for reading and writing into the database.
     */
    @Override
    String runCommand(TaskList taskList, Ui ui, Storage storage) {
        String indentation = "    ";
        Task t = taskList.getTask(index);
        taskList.deleteTask(index);
        String message = indentation + "Noted. I've removed this task: \n"
                + indentation + "  " + t.toString() + t.getStatus() +  " " + t.getDescription();
        ui.outputMessage(message);
        return message;
    }
}
