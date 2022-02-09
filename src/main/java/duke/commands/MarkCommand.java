package duke.commands;

import duke.data.Task;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int i;

    public MarkCommand(int i) {
        this.i = i;
    }

    /**
     * Mark a task as completed.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

            Task t = tasks.get(i);
            t.mark();
            return ui.respond("Nice! I've marked this task as done:\n  " + t);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MarkCommand && ((MarkCommand) o).i == this.i;
    }
}
