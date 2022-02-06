package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * An instance of InvalidCommand.
 */
public class InvalidCommand extends Command {
    /**
     * Prints out that the command given is invalid.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string indicating that the command is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Invalid command. Try `help` to view the list of possible commands.";
    }
}
