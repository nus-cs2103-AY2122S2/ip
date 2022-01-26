package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd =  taskToAdd;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList newTaskList = taskList.add(taskToAdd);
        ui.showAddResult(newTaskList,taskToAdd);
        return newTaskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
