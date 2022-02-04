package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Marks the specified task in the Task List.
 */
public class MarkCommand extends Command {
    private String markId;
    static final String OOB_RESPONSE = "Sorry, I could not find the item \\(T.T)/\n"
            + "Please type 'list' to view your current entries.";

    /**
     * Constructor for the MarkCommand.
     *
     * @param id Index of the task that is to be marked in the TaskList.
     */
    public MarkCommand(String id) {
        markId = id;
    }

    /**
     * Executes the marking of the task in the task list.
     *
     * @param tasks   TaskList that is maintained in Ducky.
     * @param ui      Ui that is maintained in Ducky.
     * @param storage Storage that is maintained in Ducky.
     * @throws DukeException thrown when the task cannot be found in the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task currentTask = tasks.getTask(Integer.parseInt(markId) - 1);
            currentTask.setDone();
            String message = currentTask.getTask();
            return ("Ok, I have marked the following task:\n" + message);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }
}
