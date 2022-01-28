package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.task.Task;

/**
 * AddCommand executes to add a task to the task list, and save the newly updated task list.
 */
public class AddCommand extends Command {
    /**
     * The task to be added to task list
     */
    private final Task task;

    /**
     * Constructor for an AddCommand
     *
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add instruction given by the user. Adds the task stored in the field to the
     * task list. Newly updated task list is saved, and ui outputs a response string to let user
     * know that AddCommand is successfully executed.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @return false to not terminate the session
     */
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        String out = tasks.add(task);
        tasks.save(storage);
        ui.outputMessage(out);
        return false;
    }
}
