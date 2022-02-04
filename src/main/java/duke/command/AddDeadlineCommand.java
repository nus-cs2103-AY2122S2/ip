package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents add new deadline command
 * inherit from Command
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
     * @param tasks the entire TaskList.
     * @param ui the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new Deadline(newDeadline.getTask(), newDeadline.getDate()));
        storage.saveTaskList(tasks);
        ui.showMessage("Got it. I've added this duke.task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have " + tasks.getSize() + " tasks in the list.");

    }
}
