package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
/**
 * Represents a abstract command recognized by the parser.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the command object.
     *
     * @param taskList a container of existing tasks in the program.
     * @param io a manager that deals with interactions with the user.
     * @param storage a manager that deals with storing and loading of files.
     */
    public abstract void execute(TaskList taskList, Ui io, Storage storage) throws DukeException;

    /**
     * Returns a boolean that specifies whether the program should end.
     *
     * @return isExit which is the switch that determines if the program ends.
     */
    public boolean getExit() {
        return isExit;
    }
}
