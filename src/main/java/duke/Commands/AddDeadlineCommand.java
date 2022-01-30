package duke.Commands;

import duke.ui.Ui;
import duke.tasklist.TaskList;

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
