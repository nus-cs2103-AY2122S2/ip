package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;

/**
 * Represents a command given by user.
 */
public abstract class Command {

    /**
     * Executes the command, depending on the type of command given by user.
     *
     * @param taskList TaskList containing existing list of tasks.
     * @param storage Storage to update data file in computer.
     */
    public abstract void execute(TaskList taskList, Storage storage);
}
