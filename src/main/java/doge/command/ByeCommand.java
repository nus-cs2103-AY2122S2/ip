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
     *
     */
    public ByeCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        storage.save(tasks.getTaskList());
        this.setExit(true);
    }

    @Override
    public String toString() {
        return "Doge:   Please don't ever bother me again, bye.";
    }
}
