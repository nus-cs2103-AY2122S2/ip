package duke.command;

import duke.manager.Ui;
import duke.manager.TaskList;
import duke.manager.Storage;

/**
 * Represents a command that will exit the program upon execution.
 */
public class ExitCommand extends Command{

    /**
     * Executes the command by printing a farewell message.
     *
     * @param taskList A TaskList that stores the tasks.
     * @param ui An Ui object to handle user interaction.
     * @param storage A Storage object to handle saving of data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("Hope to see you again!");
    }

    /**
     * Returns True if it is an exit command and false otherwise.
     * @return A boolean.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
