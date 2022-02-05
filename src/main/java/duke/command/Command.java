package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Executable command.
 */
public abstract class Command {

    /**
     * Constructs Command.
     */
    public Command() {

    }

    /**
     * Checks if this command is the exit command.
     *
     * @return False by default. This is overriden in the ExitCommand subclass.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes this command.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
