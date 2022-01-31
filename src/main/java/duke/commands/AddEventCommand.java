package duke.commands;

import duke.ui.Ui;
import duke.tasklist.TaskList;

public class AddEventCommand extends Command {

    /**
     * Add a event task to task list
     *
     * @param taskList the list of task
     * @param userInput the input from user
     */
    public AddEventCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addEventTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
