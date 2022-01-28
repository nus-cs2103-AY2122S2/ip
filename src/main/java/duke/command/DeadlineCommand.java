package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeDateTime;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {

    private final String description;
    private final DukeDateTime by;

    /**
     * Constructs a {@code DeadlineCommand} object.
     * @param description the description of the deadline task
     * @param by a {@code DukeDateTime} object specifying the deadline of the task
     */
    public DeadlineCommand(String description, DukeDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task into the list of tasks.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Deadline(description, by);
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n" + t
                + "\nNow you have " + tasks.size() + " tasks in your list.");
    }

}
