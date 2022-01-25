package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {
    private String incorrectMessage;

    /**
     * Creates a new incorrect command.
     *
     * @param s the feedback to be given to the user
     */
    public IncorrectCommand(String s) {
        incorrectMessage = s;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return incorrectMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
