package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {
    private final String incorrectMessage;

    /**
     * Creates a new incorrect command.
     *
     * @param s the feedback to be given to the user
     */
    public IncorrectCommand(String s) {
        incorrectMessage = s;
    }

    /**
     * Executes the "incorrect" command.
     *
     * @param tasks the TaskList containing the current tasks
     * @param ui the Ui of the chatbot
     * @param storage the storage of the chatbot
     * @return the result of executing the "incorrect" command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return incorrectMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
