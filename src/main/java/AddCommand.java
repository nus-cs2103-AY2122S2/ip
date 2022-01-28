import java.io.IOException;

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
     * An abstract method to execute the command.
     * The method will be implemented in the extended classes.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     * @throws IOException If the tasks cannot be saved to the data file
     */
    @Override
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
