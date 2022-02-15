package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;
import duke.task.Task;

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
     * @param taskList  List of tasks that is to be appended.
     * @param ui        UI responsible for displaying response from Duke.
     * @param storage   Storage responsible for writing to local file.
     * @param taskStack Stack of tasks to use with undo command.
     * @return String response from Duke upon successful execution.
     * @throws DukeException If write to file is unsuccessful.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) throws DukeException {
        TaskList copiedTaskList = new TaskList();
        copiedTaskList.copy(taskList);
        taskStack.push(copiedTaskList);
        taskList.add(this.task);
        storage.writeToFile(taskList);
        String output = "TASK ADDED:\n"
                + task + "\n"
                + taskList.size() + " TASK(S) NOW.";
        ui.showMessage(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
