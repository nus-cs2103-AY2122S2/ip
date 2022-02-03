package pyke.command;

import pyke.exception.InvalidNumberException;
import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;

public class MarkCommand extends Command{
    private boolean markType;
    private int taskId;

    public MarkCommand(boolean markType, int taskId) {
        this.markType = markType;
        this.taskId = taskId;
    }

    /**
     * Execute a command that marks or unmarks a task in the list
     *
     * @param taskList the class store the tasks info
     * @param ui the interface for output information
     * @param storage in charge of file IO
     * @throws PykeException if the number is not in the range of lilst index
     * @throws IOException if there is an error when writing to the local file
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskId <= 0 || taskId > taskList.getSize()) {
            throw new InvalidNumberException();
        } else {
            taskList.setTaskStatus(taskId - 1, markType);
            storage.saveFile(taskList);
            if (!markType) {
                ui.outputText("OK, I've marked this task as not done yet: \n  " + taskList.getTaskOutputStyle(taskId - 1));
            } else {
                ui.outputText("Nice! I've marked this task as done: \n  " + taskList.getTaskOutputStyle(taskId - 1));
            }
        }
    }

    @Override
    public String executeUi(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskId <= 0 || taskId > taskList.getSize()) {
            throw new InvalidNumberException();
        } else {
            taskList.setTaskStatus(taskId - 1, markType);
            storage.saveFile(taskList);
            if (!markType) {
                return ui.outputUiText("OK, I've marked this task as not done yet: \n  " + taskList.getTaskOutputStyle(taskId - 1));
            } else {
                return ui.outputUiText("Nice! I've marked this task as done: \n  " + taskList.getTaskOutputStyle(taskId - 1));
            }
        }
    }
    /**
     * To know if this command will exit the program
     *
     * @return true if this method will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
