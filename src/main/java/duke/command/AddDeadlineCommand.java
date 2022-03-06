package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends AddCommand {
    private final String taskDateTime;

    /**
     * Constructor to initialize an instance of AddDeadlineCommand class
     * with task information.
     *
     * @param taskInfo Task information (description and date/time)
     */
    public AddDeadlineCommand(String taskInfo) {
        // Task description
        super(taskInfo.split("/", 2)[0].trim());

        // Task date/time
        String taskByDateTime = taskInfo.split("/", 2)[1].trim();
        taskDateTime = taskByDateTime.split("\\s+", 2)[1].trim();
    }

    /**
     * Executes the command of adding the new Deadline task to the task list
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
        Task newDeadlineTask = taskList.addDeadlineTask(super.getTaskDescription(), taskDateTime);
        assert newDeadlineTask != null : "Task should not be null";

        storage.saveTasksToFile(taskList);

        String response = ui.taskAddedMessage(newDeadlineTask) + System.lineSeparator()
                + ui.numOfTasksInListMessage(taskList);
        assert !response.equals("") : "Add deadline response should not be empty";

        return response;
    }
}
