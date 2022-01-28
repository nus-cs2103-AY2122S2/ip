package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Delete a task from the list.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Task t = tasks.get(i);
        tasks.remove(t);
        ui.respond("Noted. I've removed this task: \n  " +
                t + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof DeleteCommand && ((DeleteCommand) o).i == this.i;
    }
}
