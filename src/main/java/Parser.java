import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    static public Command parse(String fullCommand) throws DukeException {
        if (fullCommand.startsWith("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            String remainingCommand = fullCommand.split(" ", 2)[1];
            return new MarkCommand(Integer.parseInt(remainingCommand));
        } else if (fullCommand.startsWith("unmark")) {
            String remainingCommand = fullCommand.split(" ", 2)[1];
            return new UnmarkCommand(Integer.parseInt(remainingCommand));
        } else if (fullCommand.startsWith("delete")) {
            String remainingCommand = fullCommand.split(" ", 2)[1];
            return new DeleteCommand(Integer.parseInt(remainingCommand));
        } else if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") ||fullCommand.startsWith("event")) {
            if (fullCommand.split(" ").length == 1) {
                throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", fullCommand));
            } else {
                String command = fullCommand.split(" ", 2)[0];
                String remainingCommand = fullCommand.split(" ", 2)[1];
                if (command.equals("todo")) {
                    return new AddTodoCommand(remainingCommand);
                }
                else if (command.equals("deadline")) {
                    String[] splitDescription = remainingCommand.split(" /by ");
                    if (!isDate(splitDescription[1])) {
                       return new AddDeadlineCommand(splitDescription[0], splitDescription[1]);
                    }
                    else {
                        return new AddDeadlineCommand(splitDescription[0], LocalDate.parse(splitDescription[1]));
                    }
                }
                else {
                    String[] splitDescription = remainingCommand.split(" /at ");
                    if (!isDate(splitDescription[1])) {
                        return new AddEventCommand(splitDescription[0], splitDescription[1]);
                    }
                    else {
                        return new AddEventCommand(splitDescription[0], LocalDate.parse(splitDescription[1]));
                    }
                }
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static boolean isDate(String s) {
        try {
            LocalDate.parse(s);
        }
        catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}



