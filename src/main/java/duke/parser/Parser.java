package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A parser that parses user inputs into the appropriate Command Object.
 */
public class Parser {

    /**
     * Parses a user input instruction into a valid Command Object.
     *
     * @param input String instruction.
     * @return Command Object.
     * @throws ArrayIndexOutOfBoundsException If input has an invalid number of fields for the given command.
     * @throws NumberFormatException If input index cannot be converted into a valid integer.
     * @throws DateTimeParseException If input date is in the incorrect format.
     */
    public static Command parse(String input) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, DateTimeParseException {
        input = input.trim();
        // Ignore empty input
        if (input.equals("")) {
            return new Command("null");
        }

        // Check for keywords
        String[] strArr = input.split(" ", 2);
        String keyword = strArr[0];

        Command command = new Command("default");
        switch (keyword) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            int index = Integer.parseInt(strArr[1]) - 1;
            command = new MarkCommand(index);
            break;
        case "unmark":
            index = Integer.parseInt(strArr[1]) - 1;
            command = new UnmarkCommand(index);
            break;
        case "delete":
            index = Integer.parseInt(strArr[1]) - 1;
            command = new DeleteCommand(index);
            break;
        case "todo":
            command = new AddCommand("todo", strArr[1]);
            break;
        case "event":
            String[] desc = strArr[1].split("/at");
            LocalDate date = LocalDate.parse(desc[1].trim().replace("/", "-"));
            command = new AddCommand("event", desc[0], date);
            break;
        case "deadline":
            desc = strArr[1].split("/by");
            date = LocalDate.parse(desc[1].trim().replace("/", "-"));
            command = new AddCommand("deadline", desc[0], date);
            break;
        default:
            command = new UnknownCommand();
            break;
        }
        return command;
    }
}
