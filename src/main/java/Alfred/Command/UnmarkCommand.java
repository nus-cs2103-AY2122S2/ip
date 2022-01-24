package Alfred.Command;

import Alfred.Exceptions.InvalidIndexException;
import Alfred.Exceptions.InvalidInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.UI.AlfredUserInterface;

/**
 * Encapsulates the unmark command.
 */
public class UnmarkCommand extends Command {

    private final String[] arguments;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param input input from the console. Assumes that input is
     *              *         correctly formatted, such that first word is "unmark".
     */
    public UnmarkCommand(String input) {
        this.arguments = input.split(" ");
    }

    /**
     * Executes the unmark command, by modifying the internal data state
     * of Alfred and then printing the change to console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @throws InvalidInputException If there is more than 1 argument following "mark"
     *                               in the input.
     * @throws InvalidIndexException If object to be unmarked is at an invalid index.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) throws InvalidInputException,
            InvalidIndexException {
        // check only two arguments
        if (arguments.length != 2) {
            throw new InvalidInputException();
        }
        // check that second argument is numerical
        int taskId;
        try {
            taskId = Integer.valueOf(arguments[1]) - 1;
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException();
        }
        // modify in storage
        storage.unmarkTask(taskId);

        // print out
        String out = "I see, no worries sir. I've marked this as to-be-done.\n";
        out += storage.taskToString(taskId);
        ui.sandwichAndPrint(out);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
