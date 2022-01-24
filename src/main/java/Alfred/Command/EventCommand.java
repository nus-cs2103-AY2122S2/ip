package Alfred.Command;

import Alfred.Exceptions.InvalidDateTimeException;
import Alfred.Exceptions.InvalidInputException;
import Alfred.Storage.AlfredStorage;
import Alfred.Task.Event;
import Alfred.Task.Task;
import Alfred.UI.AlfredUserInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Encapsulates the Event Command.
 */
public class EventCommand extends Command {
    private String[] arguments;

    /**
     * Constructs an EventCommand object. Assumes that first word is
     * "event", and splits input from console accordingly to get
     * other arguments.
     *
     * @param input String input from the console. Assumes that input
     *              begins with "event".
     */
    public EventCommand(String input) {
        this.arguments = input.substring(5).split(" /at ");
        this.arguments = Arrays.stream(this.arguments).filter(in -> !in.isEmpty())
                .toArray(String[]::new); // filter away empty strings
    }

    /**
     * Executes the event command. Checks for valid input, modifies
     * the internal data state of Alfred, then prints new task to console.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @throws InvalidDateTimeException If date-time being given is not in ISO format.
     * @throws InvalidInputException    If any missing arguments.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage)
            throws InvalidDateTimeException, InvalidInputException {
        // check validity of data
        if (this.arguments.length != 2) {
            throw new InvalidInputException();
        }
        try {
            LocalDateTime.parse(this.arguments[1]);
        } catch (DateTimeParseException dte) {
            throw new InvalidDateTimeException();
        }
        // modify data state
        Task event = new Event(this.arguments[0], this.arguments[1]);
        storage.addTask(event, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
