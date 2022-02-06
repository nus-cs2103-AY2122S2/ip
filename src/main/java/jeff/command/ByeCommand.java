package jeff.command;

import jeff.storage.Storage;

import jeff.task.TaskList;

import jeff.ui.Ui;

/**
 * ByeCommand class is a Command that contains instructions
 * to run when user wants to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Saves the current task list and say "bye" to the user.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return true to exit the while loop.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
