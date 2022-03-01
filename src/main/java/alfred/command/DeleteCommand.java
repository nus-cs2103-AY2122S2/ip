package alfred.command;

import alfred.exceptions.InvalidIndexException;
import alfred.exceptions.InvalidInputException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

/**
 * This class encapsulates the Delete Command.
 */
public class DeleteCommand extends Command {

    private final String[] arguments;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param input String input read from the console, assuming the starting keyword
     *              as "delete".
     */
    public DeleteCommand(String input) {
        this.arguments = input.split(" ");
    }

    /**
     * Executes the Delete Command, by removing the designated task from
     * the internal storage object used by Alfred.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @throws InvalidInputException If too many or too few arguments, or list index is not numeric.
     * @throws InvalidIndexException If item index is out of bounds.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) throws InvalidInputException,
            InvalidIndexException {
        String response = this.response(ui, storage);
        ui.sandwichAndPrint(response);
    }

    /**
     * Executes the Delete Command, by removing the designated task from
     * the internal storage object used by Alfred.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @return String output meant for user.
     * @throws InvalidInputException If too many or too few arguments, or list index is not numeric.
     * @throws InvalidIndexException If item index is out of bounds.
     */
    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage)
            throws InvalidInputException,
            InvalidIndexException {

        assert this.arguments != null;

        // check only two arguments
        if (arguments.length != 2) {
            throw new InvalidInputException();
        }

        // check that second argument is numerical, for index to delete
        int taskId;
        try {
            taskId = Integer.valueOf(arguments[1]) - 1;
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException();
        }

        // get response
        String out = "Noted sir. I've removed the following task:\n";
        out += storage.taskToString(taskId);

        // modify in storage
        storage.deleteTask(taskId);

        return out;
    }

    /**
     * Returns True if command is an Exit Command.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
