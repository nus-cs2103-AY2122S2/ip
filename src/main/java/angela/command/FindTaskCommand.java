package angela.command;

import java.io.IOException;

import angela.datetime.DateTable;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Ui;

/**
 * Finds task that match with specific keyword
 */
public class FindTaskCommand extends Command {
    private String keyWord;

    public FindTaskCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Finds and prints out the task that match with keyword
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     * @throws IOException If an I/O error occur
     */
    @Override
    public String execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException {
        return taskList.findTasksByKeyWord(keyWord);
    }

    /**
     * Checks if the command is the exit command
     *
     * @return False as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
