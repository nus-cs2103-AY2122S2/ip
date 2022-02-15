package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents add new deadline command
 * Inherits from Command
 */
public class AddDeadlineCommand extends Command {
    private final duke.task.Deadline newDeadline;

    /**
     * Returns a deadline command with new deadline.
     *
     * @param newDeadline new Deadline.
     */
    public AddDeadlineCommand(Deadline newDeadline) {
        super();
        this.newDeadline = newDeadline;
    }

    /**
     * Returns a deadline command with new deadline.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new Deadline(newDeadline.getTask(), newDeadline.getDate()));
        storage.saveTaskList(tasks);
        int taskIndex = tasks.getSize() - 1;
        ui.showMessage("Got it. I've added this task: \n        "
                + tasks.getByIndex(taskIndex) + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");

    }
}
