package alfred.command;

import alfred.exceptions.InvalidDateTimeException;
import alfred.exceptions.InvalidInputException;
import alfred.storage.AlfredStorage;
import alfred.task.Event;
import alfred.task.Task;
import alfred.ui.AlfredUserInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class EventCommand extends Command {
    private String[] arguments;

    public EventCommand(String input) {
        this.arguments = input.substring(5).split(" /at ");
        this.arguments = Arrays.stream(this.arguments).filter(in -> !in.isEmpty())
            .toArray(String[]::new); // filter away empty strings
    }

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
