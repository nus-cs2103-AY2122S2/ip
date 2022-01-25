package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddToDoTaskCommand extends Command {
    private final String description;
    
    public AddToDoTaskCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addToDoTask(description);
        
        int taskIndex = taskList.getNumberOfTasks() - 1;
        ui.showMessage("Got it. I've added this task: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
        ui.showMessage("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
    }
}
