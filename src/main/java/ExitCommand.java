/**
 * A class that represents a command to exit the program.
 */
public class ExitCommand implements Command {
    /**
     * Constructor to initialize an instance of ExitCommand class.
     */
    public ExitCommand() {
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True as the command is an Exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command of exiting the program.
     *
     * @param taskList Task list
     * @param ui An object to handle I/O operations
     * @param storage An object to handle file operations
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayExit();
    }
}
