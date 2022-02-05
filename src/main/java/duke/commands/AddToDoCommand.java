package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddToDoCommand extends Command {

    /**
     * Add a todo task to task list
     *
     * @param taskList the list of tasks
     * @param userInput the input from user
     */
    public AddToDoCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addToDoTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
