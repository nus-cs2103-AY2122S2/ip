package duke.command;

import duke.datetime.DateTable;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Shows all the tasks in the current list
 */
public class ShowListCommand extends duke.command.Command {

    /**
     * Extracts all the tasks from the list and prints them on terminal
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, BotStoring botStorage, DateTable dateTable) {
        ArrayList<Task> storingList = taskList.getStoringList();
        ui.showTaskList(storingList);
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
