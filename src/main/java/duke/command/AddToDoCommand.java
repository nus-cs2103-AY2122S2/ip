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
     * and saving the updated task list to the data file, and then returns
     * the response message.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @return The response message
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newToDoTask = taskList.addToDoTask(super.getTaskDescription());
        assert newToDoTask != null : "Task should not be null";

        storage.saveTasksToFile(taskList);

        String response = ui.taskAddedMessage(newToDoTask) + System.lineSeparator()
                + ui.numOfTasksInListMessage(taskList);
        assert !response.equals("") : "Add todo response should not be empty";

        return response;
    }
}
