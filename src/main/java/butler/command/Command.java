package butler.command;

import butler.ButlerInputException;
import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public abstract class Command {

    /**
     * Executes command behavior.
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     * @throws ButlerInputException Throws ButlerInputException when inputs passed by the user are inappropriate.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws ButlerInputException;
}
