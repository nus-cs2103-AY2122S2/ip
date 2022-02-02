package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed adds a todo task to the task list.
 */
public class AddToDoTaskCommand extends Command {
    private final String description;

    /**
     * Class constructor.
     *
     * @param description description of the todo task.
     */
    public AddToDoTaskCommand(String description) {
        this.description = description;
    }

    /**
     * Invokes <code>addToDoTask</code> method of <code>taskList</code> to add a todo task.
     * After that, prompts <code>ui</code> to display response messages to user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addToDoTask(description);

        int taskIndex = taskList.getNumberOfTasks() - 1;
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
