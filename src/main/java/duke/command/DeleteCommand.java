package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Used to delete tasks from the task list when user inputs "delete".
 */
public class DeleteCommand extends Command {
    private String deleteId;
    static final String OOB_RESPONSE = "Sorry, I could not find the item \\(T.T)/\n"
            + "Please type 'list' to view your current entries.";

    /**
     * Constructor for the DeleteCommand.
     *
     * @param id Index of the task that the user would like to delete.
     */
    public DeleteCommand(String id) {
        deleteId = id;
    }

    /**
     * Executes the deletion of the task.
     *
     * @param tasks   Tasklist that was declared in the Duke class.
     * @param ui      Ui that was declared in the Duke class.
     * @param storage Storage that was declared in the Duke class.
     * @throws DukeException When the Index of the task is non-existent.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task currTask = tasks.removeTask(Integer.parseInt(deleteId) - 1);
            return ("I have removed this from your tasks:\n" + currTask.getTask()
                    + "\nYou now have " + tasks.getSize() + " tasks");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }
}
