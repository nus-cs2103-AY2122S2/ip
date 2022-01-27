package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command for adding a Task object to a taskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Instantiates an AddCommand object with input task and commandArray.
     *
     * @param task         Task any type of task to be added.
     * @param commandArray String[] array of String from String input command.
     */
    public AddCommand(Task task, String[] commandArray) {
        super(commandArray);
        this.task = task;
    }

    /**
     * Executes the addTask from taskList and saves the file.
     *
     * @param taskList TaskList input taskList object from Duke.
     * @param ui Ui input ui object from Duke.
     * @param storage Storage input storage object from Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.save(taskList);
    }

    /**
     * Checks the equality of two Command objects based on Task comparison.
     *
     * @param command Object Another command object to be compared to.
     * @return boolean Boolean value to show if this equals command.
     */
    @Override
    public boolean equals(Object command) {
        if (this.task.compareTo(((AddCommand) command).task) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
