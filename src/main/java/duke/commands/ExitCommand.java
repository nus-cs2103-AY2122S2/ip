package duke.commands;

import duke.common.DukeException;
import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     * 
     * @return Output message for GUI.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "ExitCommand[execute] taskList cannot be null.";
        assert storage != null : "ExitCommand[execute] storage cannot be null.";

        return Constants.BYE;
    }

    /**
     * Notifies the main function to end the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
