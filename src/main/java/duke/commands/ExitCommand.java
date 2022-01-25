package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits the chatbot.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Goodbye! Till the next time we meet!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
