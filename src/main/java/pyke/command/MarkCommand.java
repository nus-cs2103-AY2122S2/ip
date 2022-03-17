package pyke.command;

import java.io.IOException;

import pyke.exception.InvalidNumberException;
import pyke.exception.PykeException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

public class MarkCommand extends Command {
    private boolean isMark;
    private int taskId;

    /**
     * Constructs a MarkCommand.
     * @param markType the type of this command.
     * @param taskId the Id of the task.
     */
    public MarkCommand(boolean markType, int taskId) {
        this.isMark = markType;
        this.taskId = taskId;
    }

    /**
     * Executes a command that marks or unmarks a task in the list.
     *
     * @param taskList the class store the tasks info.
     * @param ui the interface for output information.
     * @param storage in charge of file IO.
     * @throws PykeException if the number is not in the range of list index.
     * @throws IOException if there is an error when writing to the local file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskId <= 0 || taskId > taskList.getSize()) {
            throw new InvalidNumberException();
        } else {
            taskList.setTaskStatus(taskId - 1, isMark);
            storage.saveFile(taskList);
            if (!isMark) {
                ui.outputText("OK, I've marked this task as not done yet: \n  "
                        + taskList.getTaskOutputStyle(taskId - 1));
            } else {
                ui.outputText("Nice! I've marked this task as done: \n  "
                        + taskList.getTaskOutputStyle(taskId - 1));
            }
        }
    }

    @Override
    public String executeUi(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskId <= 0 || taskId > taskList.getSize()) {
            throw new InvalidNumberException();
        } else {
            taskList.setTaskStatus(taskId - 1, isMark);
            storage.saveFile(taskList);
            if (!isMark) {
                return ui.outputUiText("OK, I've marked this task as not done yet: \n  "
                        + taskList.getTaskOutputStyle(taskId - 1));
            } else {
                return ui.outputUiText("Nice! I've marked this task as done: \n  "
                        + taskList.getTaskOutputStyle(taskId - 1));
            }
        }
    }
    /**
     * Knows if this command will exit the program.
     *
     * @return true if this method will exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
