package commandset;

import helper.TaskList;
import helper.Ui;

public class ListCommand extends Command {

    // the size of task list if no tasks are present.
    private static final int EMPTY = 0;

    /**
     * prints the list of user commands.
     * @param taskList the list of user tasks.
     */
    public static void list(TaskList taskList) {

        if (taskList.numOfTasks() != EMPTY) {
            Ui.printMessage(taskList.toString());
        } else {
            Ui.printMessage("No tasks are there! Seems like you are free! \n Do you wanna add some tasks?");
        }

    }
}
