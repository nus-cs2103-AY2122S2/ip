package duke.Commands;

import duke.Tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * InvalidCommand is a subclass of DukeCommand that is created when the user inputs an invalid command
 */
public class InvalidCommand extends DukeCommand {

    public InvalidCommand(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns the error message
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     * @return String error message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.commandBody;
    }
}
