package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed adds a deadline task to the task list.
 */
public class AddDeadlineTaskCommand extends Command {
    private final String description;
    private final LocalDate date;

    /**
     * Class constructor.
     *
     * @param description description of the deadline task.
     * @param date deadline of the task expressed in date.
     */
    public AddDeadlineTaskCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Invokes <code>addDeadlineTask</code> method of <code>taskList</code> to add a deadline task.
     * After that, prompts <code>ui</code> to display response messages to user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addDeadlineTask(description, date);

        int taskIndex = taskList.getNumberOfTasks() - 1;
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
