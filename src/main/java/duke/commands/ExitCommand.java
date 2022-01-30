package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks   the TaskList containing the current tasks
     * @param ui      the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the exit command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Goodbye! Till the next time we meet!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
