package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {

    /**
     * Constructs a {@code ByeCommand} object.
     */
    public ByeCommand() {}

    /**
     * Pushes a goodbye message to the UI.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
