package van;

/**
 * Abstracts the concept of the command having errors
 */
public class InvalidCommand implements Command {
    private String message;

    /**
     * Constructs a InvalidCommand object containing the problem with
     * the inputted command.
     *
     * @param message string indicating the problem with the command inputted
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Calls the methods needed in order to display the error with the command
     *
     * @param ui       Ui object to handle print tasks
     * @param taskList TaskList object that handles managing of the list of tasks
     * @param storage  Storage object that handles loading and saving of list
     * @return returns true only when command executed is an ExitCommand
     */
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        ui.invalidMessage(message);
        return false;
    }
}
