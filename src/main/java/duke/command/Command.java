package duke.command;

import java.io.IOException;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Ui;

/**
 * Super class for all the Command classes
 */
public abstract class Command {

    /**
     * Executes the behavior of the class
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @throws IOException If an I/O error occur
     */
    public abstract void execute(TaskList taskList, Ui ui,
            BotStorage botStorage, DateTable dateTable) throws IOException;

    /**
     * Checks if the command is the exit command
     *
     * @return True if this is an exit command and False otherwise
     */
    public abstract boolean isExit();
}
