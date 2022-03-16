package duke;

import java.io.IOException;

/**
 * Abstract class to be inherited
 */
public abstract class Command {
    /**
     *Abstract method which executes commands
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ExceptionHandler, IOException;

    /**
     * Abstract boolean method to check for exit command
     */
    public static boolean isExit() {
        return false;
    }
}


