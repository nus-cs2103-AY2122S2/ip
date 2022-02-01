package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * The ExitCommand class is a type of Command that is used to stop running the bot application.
 */
public class ExitCommand extends Command {
    public ExitCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    /**
     * Executes the exit command, displays the goodbye and stops running the bot application.
     * @param tasks TaskList that stores the current list of tasks.
     * @param ui Ui of the bot application.
     * @param storage Storage to store the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isRun = false;
        ui.showGoodbye();
    }
}
