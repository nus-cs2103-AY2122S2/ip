package dazz;

import dazz.command.AliasCommand;
import dazz.command.Command;
import dazz.command.DeadlineCommand;
import dazz.command.DefaultCommand;
import dazz.command.DeleteCommand;
import dazz.command.EventCommand;
import dazz.command.ExitCommand;
import dazz.command.FindCommand;
import dazz.command.ListCommand;
import dazz.command.MarkCommand;
import dazz.command.TodoCommand;
import dazz.command.UnmarkCommand;
import dazz.exception.DazzException;
import dazz.exception.EmptyDateException;
import dazz.exception.IncompleteCommandException;

/**
 * Represents the class to parse the user input.
 * Contains the logic to process the user input.
 */
public class Parser {
    private static final String DEADLINE_TOKEN = "/by";
    private static final String EVENT_TOKEN = "/at";

    /**
     * Returns a valid <code>Command</code> when a valid user input is parsed.
     * If the user input is invalid, <code>DefaultCommand</code> is returned.
     * @param input The user input.
     * @return Command The <code>Command</code> depending on the type of input.
     * @throws DazzException If errors are detected in user input.
     */
    public static Command parse(String input) throws DazzException {
        String[] arr = input.split(" ", 2);
        String command = CommandMapper.getCommand(arr[0]);
        command = !(command == null) ? command : "";
        switch (command) {
        case "alias":
            return parseAlias(input);
        case "bye":
            return new ExitCommand();
        case "todo":
            return new TodoCommand(extractDescription(input));
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "find":
            return new FindCommand(extractDescription(input));
        case "mark":
            return new MarkCommand(Integer.parseInt(extractDescription(input)));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(extractDescription(input)));
        case "delete":
            return new DeleteCommand(Integer.parseInt(extractDescription(input)));
        case "list":
            return new ListCommand();
        default:
            return new DefaultCommand();
        }
    }

    /** this method only handles description from alias, todo, find, delete, mark, unmark commands */
    private static String extractDescription(String input) throws IncompleteCommandException {
        String trimmedInput = input.trim();
        String[] arr = trimmedInput.split(" ", 2);
        if (arr.length < 2) {
            throw new IncompleteCommandException();
        }
        assert !arr[1].equals("");
        return arr[1];
    }

    /** this method handles description and date from deadline and event */
    private static String[] extractDescriptionAndDate(String input, String token) throws DazzException {
        String trimmedInput = input.trim();

        String[] arr = trimmedInput.split(" ", 2);
        if (arr.length < 2 || arr[1].trim().startsWith(token)) {
            throw new IncompleteCommandException();
        }

        String[] wordArr = arr[1].split(token);
        if (wordArr.length < 2 || wordArr[1].trim().equals("")) {
            throw new EmptyDateException();
        }
        return new String[]{wordArr[0].trim(), wordArr[1].trim()};
    }

    // alias todo /as t
    private static String[] extractCommandAndAlias(String input) throws IncompleteCommandException {
        String trimmedInput = input.trim();

        String[] arr = trimmedInput.split(" ", 2);
        if (arr.length < 2 || arr[1].trim().startsWith("/as")) {
            throw new IncompleteCommandException();
        }

        String[] wordArr = arr[1].split("/as");
        if (wordArr.length < 2 || wordArr[1].trim().equals("")) {
            throw new IncompleteCommandException();
        }
        return new String[]{wordArr[0].trim(), wordArr[1].trim()};
    }

    private static AliasCommand parseAlias(String input) throws DazzException {
        String[] wordArr = extractCommandAndAlias(input);
        return new AliasCommand(wordArr[0], wordArr[1]);
    }

    private static DeadlineCommand parseDeadline(String input) throws DazzException {
        String[] wordArr = extractDescriptionAndDate(input, DEADLINE_TOKEN);
        return new DeadlineCommand(wordArr[0], Ui.toLocalDateTime(wordArr[1]));
    }

    private static EventCommand parseEvent(String input) throws DazzException {
        String[] wordArr = extractDescriptionAndDate(input, EVENT_TOKEN);
        return new EventCommand(wordArr[0], Ui.toLocalDateTime(wordArr[1]));
    }
}
