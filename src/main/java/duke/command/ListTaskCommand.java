package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Used to show tasks from the task list when user inputs "list".
 */
public class ListTaskCommand extends Command {

    /**
     * Executes the listing of tasks within the Task List.
     *
     * @param tasks Tasklist that was declared in the Duke class.
     * @param ui Ui that was declared in the Duke class.
     * @param storage Storage that was declared in the Duke class.
     * @throws DukeException When there is no task in the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("Please add some tasks first *(^.^)*");
        } else {
            String topLine = ("Here are your tasks:\n");
            String output = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task currentTask = tasks.getTask(i);
                String message = currentTask.getTask();
                output = output + ("\n" + (i + 1) + ". " + message);
            }
            return topLine + output;
        }
    }
}
