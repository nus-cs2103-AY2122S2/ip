package butler.command;

import butler.ButlerInputException;
import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class InvalidCommand extends Command {

    /**
     * Executes command behavior.
     * In this case it throws a ButlerInputException to be handled down the line.
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     * @throws ButlerInputException Throws the ButlerInputException as when user inputs cannot be understood
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws ButlerInputException {
        throw new ButlerInputException("I'm sorry Master, but I do not know what that means. "
                + "Please provide a valid command.\n");

    }
}
