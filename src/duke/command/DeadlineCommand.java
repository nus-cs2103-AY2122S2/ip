package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeDateTime;
import duke.task.Task;
import duke.task.Deadline;

public class DeadlineCommand extends Command {

    private final String description;
    private final DukeDateTime by;

    public DeadlineCommand(String description, DukeDateTime by) {
        super(Keyword.DEADLINE);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Deadline(description, by);
        tasks.add(t);
        ui.showMessage("Got it. I've added this task:\n" + t
                + "\nNow you have " + tasks.size() + " tasks in your list.");
    }

}
