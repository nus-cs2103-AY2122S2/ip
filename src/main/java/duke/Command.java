package duke;

import java.io.IOException;

/**
 * Abstract class to be inherited
 */
public abstract class Command {
    /**
     *Abstract method which executes commands
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler, IOException;

    /**
     *Abstract boolean method to check for exit command
     */
    public abstract boolean isExit();
}


