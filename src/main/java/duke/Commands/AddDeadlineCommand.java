package duke.Commands;

import duke.ui.Ui;
import duke.tasklist.TaskList;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addDeadlineTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
