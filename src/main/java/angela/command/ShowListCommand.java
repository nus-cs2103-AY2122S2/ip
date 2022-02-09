package angela.command;

import java.util.ArrayList;

import angela.datetime.DateTable;
import angela.task.Task;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Ui;

/**
 * Shows all the tasks in the current list
 */
public class ShowListCommand extends angela.command.Command {

    /**
     * Extracts all the tasks from the list and prints them on terminal
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     */
    @Override
    public String execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable) {
        ArrayList<Task> storingList = taskList.getStoringList();
        return ui.showTaskList(storingList);
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
