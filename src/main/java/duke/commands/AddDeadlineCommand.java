package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;


public class AddDeadlineCommand extends Command {

    /**
     * Add a deadline task to task list
     *
     * @param taskList the list of tasks
     * @param userInput the input from user
     */
    public AddDeadlineCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addDeadlineTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
