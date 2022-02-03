package src.main.java.duke.command;

import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.TaskList;

/**
 * ListCommand is a Command that triggers the program to print out the task list
 * with proper indexing according to chronological order of when the task was
 * added into the task list.
 */
public class ListCommand extends Command {

    /**
     * Execute method for ListCommand lists out the tasks stored in the tasks list.
     * 
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    /**
     * isExit method checks if this is an exit command, and only returns yes for an
     * exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
