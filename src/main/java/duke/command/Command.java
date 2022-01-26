package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

import java.io.IOException;

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
            BotStoring botStorage, DateTable dateTable) throws IOException;

    /**
     * Checks if the command is the exit command
     *
     * @return True if this is an exit command and False otherwise
     */
    public abstract boolean isExit();
}
