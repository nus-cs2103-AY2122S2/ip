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

    private static String LIST_TASK_HEADER = "Here are your tasks:\n";
    private static String ADD_TASK_PROMPT = "Please add some tasks first *(^.^)*";
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
        boolean isTaskListEmpty = tasks.getSize() == 0;
        if (isTaskListEmpty) {
            throw new DukeException(ADD_TASK_PROMPT);
        } else {
            String output = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                Task currentTask = tasks.getTask(i);
                String message = currentTask.getTask();
                output = output + ("\n" + (i + 1) + ". " + message);
            }
            return LIST_TASK_HEADER + output;
        }
    }
}
