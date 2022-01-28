package pyke.command;

import pyke.exception.InvalidNumberException;
import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;

public class DelCommand extends Command{
    private int taskId;

    public DelCommand(int taskId) {
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskId <= 0 || taskId > taskList.getSize()) {
            throw new InvalidNumberException();
        } else {
            String outputText = taskList.getTaskOutputStyle(taskId - 1);
            taskList.delTask(taskId - 1);
            storage.saveFile(taskList);
            ui.outputText(" Noted. I've removed this task:\n    " + outputText
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        }
    }
    public boolean isExit() {
        return false;
    }
}
