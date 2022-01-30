package athena.commands;

import athena.tasks.TaskList;
import athena.ui.Ui;

/**
 *  Represents a shutdown command given to Athena by the user.
 */
public class ShutdownCommand extends Command {
    /**
     * Terminates the program.
     *
     * @param ui Ui instance to display outputs through.
     * @param taskList Current taskList instance.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.sayText("Farewell!");
        // main loop needs to check that this is an exit command
    }
}
