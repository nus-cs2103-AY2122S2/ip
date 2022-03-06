package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class that represents a command to add a task.
 */
public abstract class AddCommand implements Command {
    private final String taskDescription;

    /**
     * Constructor to initialize an instance of AddCommand class with
     * task description.
     *
     * @param taskDescription Task description
     */
    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Returns the task description.
     *
     * @return Task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return False as the command is an Add command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and then returns the response message.
     * This abstract method will be implemented by its subclasses.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @return The response message
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
