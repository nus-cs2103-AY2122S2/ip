package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListTaskCommand extends Command {

    public ListTaskCommand() {
        
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        int numberOfTasks = taskList.getNumberOfTasks();

        if (numberOfTasks == 0) {
            ui.showMessage("Congratulations! There are no tasks in your list :)");
            return;
        }

        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            ui.showMessage(String.format("%d. %s", i + 1, taskList.getDescriptionOfTaskAtIndex(i)));
        }
    }
}
