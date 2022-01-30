package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command that adds a task.
 *
 * @author Peter
 */
public class AddCommand extends Command {
    /**
     * Task that is to be added.
     */
    private final Task task;

    /**
     * Constructor for a task add command.
     *
     * @param task Task that is to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds the associated task to a given list of tasks, displays a response message, and writes
     * to a local file associated with the list of tasks.
     *
     * @param taskList List of tasks that is to be appended.
     * @param ui       UI responsible for displaying the response message.
     * @param storage  Storage responsible for writing to local file.
     * @return <code>true</code> upon successful execution.
     * @throws DukeException If write to file is unsuccessful.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(this.task);
        ui.showMessage("TASK ADDED:\n"
                + task + "\n"
                + taskList.size() + " TASK(S) NOW.");
        storage.writeToFile(taskList);
        return true;
    }
}
