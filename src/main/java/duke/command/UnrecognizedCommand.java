package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class UnrecognizedCommand extends Command {

    /**
     * Constructs a {@code UnrecognizedCommand} object.
     */
    public UnrecognizedCommand() {}

    /**
     * Pushes a message to the UI to indicate that the command is not recognized.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Sorry, but I don't know what that means :(";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
