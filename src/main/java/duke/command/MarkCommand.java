package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A class that represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int taskNum;

    /**
     * Constructor to initialize an instance of MarkCommand class with
     * task number.
     *
     * @param taskNum Task number
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is a Done command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command of marking the task in the task list as done
     * and saving the updated list to the data file.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws DukeException If the task is not found
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskNum > 0 && taskNum <= taskList.getNumOfTasks()) {
            Task taskDone = taskList.markDone(taskNum);

            String response = ui.taskDoneMessage(taskDone);
            ui.displayResponse(response);

            storage.saveTasksToFile(taskList);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
