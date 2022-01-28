package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A class that represents a command to add an Event task.
 */
public class AddEventCommand extends AddCommand {
    private final String taskDateTime;

    /**
     * Constructor to initialize an instance of AddEventCommand class
     * with task information.
     *
     * @param taskInfo Task information (description and date/time)
     */
    public AddEventCommand(String taskInfo) {
        // Task description
        super(taskInfo.split("/", 2)[0].trim());

        // Task date/time
        String taskAtDateTime = taskInfo.split("/", 2)[1].trim();
        taskDateTime = taskAtDateTime.split("\\s+", 2)[1].trim();
    }

    /**
     * Executes the command of adding the new Event task to the task list
     * and saving the updated task list to the data file.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task newEventTask = taskList.addEventTask(super.getTaskDescription(), taskDateTime);

        String response = ui.taskAddedMessage(newEventTask)
                + System.lineSeparator() + ui.numOfTasksInListMessage(taskList);
        ui.displayResponse(response);

        storage.saveTasksToFile(taskList);
    }
}
