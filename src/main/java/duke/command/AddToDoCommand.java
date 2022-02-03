package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that represents a command to add a ToDo task.
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Constructor to initialize an instance of AddToDoCommand class
     * with task description.
     *
     * @param taskDescription Task description
     */
    public AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Executes the command of adding the new ToDo task to the task list
     * and saving the updated task list to the data file.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newToDoTask = taskList.addToDoTask(super.getTaskDescription());

        String response = ui.taskAddedMessage(newToDoTask)
                + System.lineSeparator() + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }
}
