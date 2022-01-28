package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class MarkCommand extends Command {
    private final int i;

    public MarkCommand(int i) {
        this.i = i;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

            Task t = tasks.get(i);
            t.mark();
            ui.respond("Nice! I've marked this task as done:\n  " + t);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MarkCommand && ((MarkCommand) o).i == this.i;
    }
}
