package src.main.java.duke.command;

import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.TaskList;

/**
 * ExitCommand is a Command that terminates the program.
 */
public class ExitCommand extends Command {

    /**
     * isExit method checks if this is an exit command, and only returns yes for an
     * exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Execute method for ExitCommand prints a farewell message to the user and
     * exits the program.
     * 
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }
}
