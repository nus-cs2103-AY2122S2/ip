package funbox.command;

import java.io.IOException;
import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public abstract class Command {
    boolean isExit;

    /**
     * The constructor for the command class.
     *
     * @param isExit 
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException;
}
