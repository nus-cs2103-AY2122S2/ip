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

    /**
     * Execute a command that delete a task.
     * Then it will write the list to the local file and output certain info about this operation.
     *
     * @param taskList the class store the tasks info
     * @param ui the interface for output information
     * @param storage in charge of file IO
     * @throws PykeException if the number is not in the desired range of index
     * @throws IOException if there is an error when writing to the local file
     */
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

    /**
     * To know if this command will exit the program
     *
     * @return true if this method will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
