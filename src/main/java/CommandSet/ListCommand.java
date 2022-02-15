package commandset;

import helper.TaskList;
import helper.Ui;

public class ListCommand extends Command {

    /**
     * prints the list of user commands.
     * @param taskList the list of user tasks.
     */
    public static void list(TaskList taskList) {
        Ui.printMessage(taskList.toString());
    }
}
