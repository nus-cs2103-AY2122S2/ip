package funbox.command;

import java.io.IOException;
import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

/**
 * This is the abstract class for Command.
 */
public abstract class Command {
    boolean isExit;

    /**
     * This is the constructor for command class.
     *
     * @param isExit Use for determining whether the command is a `Bye` command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Return a boolean value.
     *
     * @return Return true if it is a `Bye` command, else return false.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns a response to be displayed to the user.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions Returns exceptions related to FunBox.
     * @throws IOException Returns exceptions related to input/output.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException;
}
