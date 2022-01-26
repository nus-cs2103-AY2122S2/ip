package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd =  taskToAdd;
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newTaskList = taskList.add(taskToAdd);
        ui.showAddResult(newTaskList,taskToAdd);
        String lineToAdd = storage.createSummaryFromTask(this.taskToAdd);
        storage.addLine(storage.getFilePath(), lineToAdd);
        return newTaskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
