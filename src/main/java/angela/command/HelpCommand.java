package angela.command;

import java.io.IOException;
import java.util.ArrayList;

import angela.datetime.DateTable;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Ui;

/**
 * Shows all available command to user
 */
public class HelpCommand extends Command {

    /**
     * Extracts all the commands and prints them on GUI
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @return The array string represent the display text
     * @throws IOException If an I/O error occurs
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, BotStorage botStorage,
            DateTable dateTable) throws IOException {
        return ui.displayAllCommand();
    }

    /**
     * Checks if the command is the exit command
     *
     * @return True as this is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
