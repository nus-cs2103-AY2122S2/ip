package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class AddCommand extends Command {
    private final Task t;

    public AddCommand(Task task) {
        super();
        this.t = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(t);
        storage.record(t + "\n");
        ui.respond("Got it. I've added this task:\n  " +
                    t + "\nNow you have " + tasks.size() + " tasks in the list.");

    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AddCommand && ((AddCommand) o).t.equals(this.t);
    }

}
