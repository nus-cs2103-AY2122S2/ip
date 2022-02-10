package athena.commands;

import athena.tasks.TaskList;

/**
 *  Represents a shutdown command given to Athena by the user.
 */
public class ShutdownCommand extends Command {
    /**
     * Terminates the program.
     *
     * @param taskList Current taskList instance.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Farewell!";
        // main loop needs to check that this is an exit command
    }
}
