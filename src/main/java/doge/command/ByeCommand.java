package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;

/**
 * Represents the "bye" command. Doge bot will send a farewell message and terminate itself.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for class ByeCommand.
     */
    public ByeCommand() {

    }

    /**
     * Executes the "Bye" command. It saves the current TaskList into storage and then Doge bot terminates itself.
     *
     * {@inheritDoc}
     * @throws DogeException if storage fails to save task list
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        storage.save(tasks.getTaskList());
        this.setExit(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Doge:   Please don't ever bother me again, bye.";
    }
}
