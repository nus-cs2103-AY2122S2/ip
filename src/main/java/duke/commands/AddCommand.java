package duke.commands;

import duke.data.Task;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddCommand extends Command {
    private final Task t;

    public AddCommand(Task task) {
        super();
        this.t = task;
    }

    /**
     * Add a task to the list.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(t);
        storage.record(t + "\n");
        return "Got it. I've added this task:\n  " +
                    t + "\n\nNow you have " + tasks.size() + " tasks in the list.";

    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AddCommand && ((AddCommand) o).t.equals(this.t);
    }

}
