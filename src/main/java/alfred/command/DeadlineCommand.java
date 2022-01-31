package alfred.command;

import alfred.exceptions.InvalidDateTimeException;
import alfred.exceptions.InvalidInputException;
import alfred.storage.AlfredStorage;
import alfred.task.Deadline;
import alfred.task.Task;
import alfred.ui.AlfredUserInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * This is a class encapsulating the Deadline command.
 */
public class DeadlineCommand extends Command {
    private String[] arguments;

    /**
     * Constructs a DeadlineCommand object. Removes the first 8 characters,
     * assuming it is "deadline". Then splits it by " /by " to get the arguments,
     * and removes empty strings.
     *
     * @param input String input read from the console, with the starting keyword
     *              as "deadline".
     */
    public DeadlineCommand(String input) {
        this.arguments = input.substring(8).split(" /by ");
        this.arguments = Arrays.stream(this.arguments).filter(in -> !in.isEmpty())
                .toArray(String[]::new); // filter away empty strings
    }

    /**
     * Executes the Deadline command by checking for valid input format,
     * adding the task to an internal storage object, before printing out
     * the new task added.
     *
     * @param ui      AlfredUserInterface object used as the UI interface for Alfred.
     * @param storage AlfredStorage object used to handle internal storage for Alfred.
     * @throws InvalidDateTimeException If date-time being given is not in ISO format.
     * @throws InvalidInputException    If any missing arguments.
     */
    @Override
    public void execute(AlfredUserInterface ui, AlfredStorage storage)
            throws InvalidDateTimeException, InvalidInputException {
        String response = this.response(ui, storage);
        ui.sandwichAndPrint(response);

    }

    @Override
    public String response(AlfredUserInterface ui, AlfredStorage storage)
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
        Task event = new Deadline(this.arguments[0], this.arguments[1]);
        storage.addTask(event);

        // response
        String out = "Yes sir, I've added this task.\n";
        out += event.toString() + "\n";
        out += storage.summarizeList();
        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
