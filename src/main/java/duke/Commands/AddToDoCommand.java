package duke.Commands;

import duke.ui.Ui;
import duke.tasklist.TaskList;

public class AddToDoCommand extends Command {

    public AddToDoCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addToDoTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
