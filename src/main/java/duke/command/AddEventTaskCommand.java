package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed adds an event task to the task list.
 */
public class AddEventTaskCommand extends Command {
    private final String description;
    private final String at;

    /**
     * Class constructor.
     *
     * @param description description of the event task.
     * @param at the time of the event expressed in a <code>String</code>.
     */
    public AddEventTaskCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Invokes <code>addEventTask</code> method of <code>taskList</code> to add an event task.
     * After that, prompts <code>ui</code> to display response messages to user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addEventTask(description, at);

        int taskIndex = taskList.getNumberOfTasks() - 1;
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
