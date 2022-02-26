package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that represents a command to list all the available commands.
 */
public class HelpCommand implements Command {
    /**
     * Constructor to initialize an instance of HelpCommand class.
     */
    public HelpCommand() {
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a Help command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String response = Ui.HELP_MESSAGE;
        assert !response.equals("") : "Help response should not be empty";

        return response;
    }
}
