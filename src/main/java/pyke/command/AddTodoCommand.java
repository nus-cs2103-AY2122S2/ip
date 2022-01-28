package pyke.command;

import pyke.exception.EmptyDescriptionException;
import pyke.exception.PykeException;
import pyke.task.ToDo;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddTodoCommand extends Command{
    private String taskName;

    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Execute a command that adds a to-do task.
     * Then it will write the list to the local file and output certain info about this operation.
     *
     * @param taskList the class store the tasks info
     * @param ui the interface for output information
     * @param storage in charge of file IO
     * @throws PykeException
     * @throws IOException if there is an error when writing to the local file
     */
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

    /**
     * To know if this command will exit the program
     *
     * @return true if this method will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
