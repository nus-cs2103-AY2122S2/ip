package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to add a task to a task list.
 */
public class AddTaskCommand extends Command {

    private final Task task;

    /**
     * Initialises an AddTaskCommand instance given a task.
     *
     * @param task the task to add.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds this AddTaskCommand's task into a given task list.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the text UI of Duke.
     * @param storage the storage of Duke.
     * @return a message to show a task was sucessfully added.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        return ui.showTaskAdded(task, taskList) + this.saveData(taskList, ui, storage);
    }

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
