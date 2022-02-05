package alfred.command;

import alfred.Alfred;
import alfred.exceptions.InvalidIndexException;
import alfred.exceptions.InvalidInputException;
import alfred.storage.AlfredStorage;
import alfred.ui.AlfredUserInterface;

/**
 * Encapsulates the mark command.
 */
public class MarkCommand extends Command {

    private final String[] arguments;

    /**
     * Constructs a MarkCommand object.
     *
     * @param input String input from the console. Assumes that input is
     *              correctly formatted, such that first word is "mark".
     */
    public MarkCommand(String input) {
        this.arguments = input.split(" ");
    }

    /**
     * Executes the mark command, by modifying the internal data state
     * of Alfred and then printing the change to console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @throws InvalidInputException If there is more than 1 argument following "mark"
     *                               in the input.
     * @throws InvalidIndexException If object to be marked is at an invalid index.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage) throws InvalidInputException,
            InvalidIndexException {
        String out = this.response(ui, storage);
        ui.sandwichAndPrint(out);
    }

    /**
     * Executes the mark command, by modifying the internal data state
     * of Alfred and then printing the change to console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @return String output for user.
     * @throws InvalidInputException If there is more than 1 argument following "mark"
     *                               in the input.
     * @throws InvalidIndexException If object to be marked is at an invalid index.
     */
    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage)
            throws InvalidInputException,
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
        storage.markTask(taskId);

        // print out
        String out = "Good job sir. I've marked this as complete.\n";
        out += storage.taskToString(taskId);
        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
