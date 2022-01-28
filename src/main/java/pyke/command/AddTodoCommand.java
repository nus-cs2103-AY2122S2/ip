package pyke.command;

import pyke.exception.EmptyDescriptionException;
import pyke.exception.PykeException;
import pyke.task.ToDo;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;

public class AddTodoCommand extends Command{
    private String taskName;

    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        } else {
            taskList.addTask(new ToDo(taskName));
            storage.saveFile(taskList);
            ui.outputText("Got it. I've added this task:\n    "
                    + taskList.getTaskOutputStyle(taskList.getSize() - 1)
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        }
    }
    public boolean isExit() {
        return false;
    }
}
