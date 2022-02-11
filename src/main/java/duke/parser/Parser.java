package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import duke.command.AddDeadlineTaskCommand;
import duke.command.AddEventTaskCommand;
import duke.command.AddToDoTaskCommand;
import duke.command.AliasCommand;
import duke.command.ByeCommand;
import duke.command.ClearTaskCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.FindTaskCommand;
import duke.command.ListTaskCommand;
import duke.command.MarkTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.exception.DukeException;

/**
 * This class acts as a parser that parses user inputs into specific commands.
 */
public class Parser {
    private static final int ARRAY_INDEX_OFFSET = 1;
    private static final String[] COMMANDS = new String[]{
        "alias", "bye", "clear", "list", "find", "mark",
        "unmark", "delete", "todo", "deadline", "event"};
    private static final String[] COMMAND_SHORTCUTS = new String[]{
        "a", "b", "c", "ls", "f", "m", "um", "del", "t", "d", "e"};
    private final HashSet<String> reservedCommands;
    private final HashMap<String, String> aliases;

    /**
     * Class constructor.
     */
    public Parser() {
        reservedCommands = new HashSet<>();
        reservedCommands.addAll(Arrays.asList(COMMANDS));

        aliases = new HashMap<>();
        for (int i = 0; i < COMMAND_SHORTCUTS.length; i++) {
            aliases.put(COMMAND_SHORTCUTS[i], COMMANDS[i]);
        }
    }

    /**
     * Dispatches the input string entered by user to appropriate helper parser
     * matching the command type.
     *
     * @param command the command entered by user.
     * @return a command to be executed by the application.
     * @throws DukeException when <code>command</code> is invalid or its format is incorrect.
     */
    public Command parse(String command) throws DukeException {
        String[] commandTokens = command.split(" ", 2);
        String primaryCommand = Optional.ofNullable(aliases.get(commandTokens[0])).orElse(commandTokens[0]);
        String arguments = commandTokens.length > 1 ? commandTokens[1] : "";

        switch (primaryCommand) {
        case "alias":
            return parseAliasCommand(arguments);
        case "bye":
            return parseByeCommand(arguments);
        case "clear":
            return parseClearCommand(arguments);
        case "list":
            return parseListCommand(arguments);
        case "find":
            return parseFindCommand(arguments);
        case "mark":
            return parseMarkCommand(arguments);
        case "unmark":
            return parseUnmarkCommand(arguments);
        case "delete":
            return parseDeleteCommand(arguments);
        case "todo":
            return parseToDoCommand(arguments);
        case "deadline":
            return parseDeadlineCommand(arguments);
        case "event":
            return parseEventCommand(arguments);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means");
        }
    }

    /**
     * Parses an alias command.
     *
     * @param arguments arguments to this command.
     * @return an <code>AliasCommand</code> instance.
     * @throws DukeException when one or more arguments is missing or illegal.
     */
    private Command parseAliasCommand(String arguments) throws DukeException {
        String[] aliasMapping = arguments.split(" ");
        if (aliasMapping.length != 2) {
            throw new DukeException("The alias command takes in exactly 2 arguments");
        }

        String from = aliasMapping[0];
        String to = aliasMapping[1];
        if (reservedCommands.contains(from)) {
            throw new DukeException("Duke's original command cannot be changed");
        } else if (!reservedCommands.contains(to)) {
            throw new DukeException("Alias can only refer to Duke's original command");
        }

        aliases.put(from, to);
        return new AliasCommand(from, to);
    }

    /**
     * Parses a bye command.
     *
     * @param arguments not used.
     * @return a <code>ByeCommand</code> instance.
     */
    private Command parseByeCommand(String arguments) {
        return new ByeCommand();
    }

    /**
     * Parses a clear command.
     *
     * @param arguments not used.
     * @return a <code>ClearTaskCommand</code> instance.
     */
    private Command parseClearCommand(String arguments) {
        return new ClearTaskCommand();
    }

    /**
     * Parses a list command.
     *
     * @param arguments not used.
     * @return a <code>ListTaskCommand</code> instance.
     */
    private Command parseListCommand(String arguments) {
        return new ListTaskCommand();
    }

    /**
     * Parses a find command.
     *
     * @param arguments arguments to this command.
     * @return a <code>FindTaskCommand</code> instance.
     */
    private Command parseFindCommand(String arguments) {
        return new FindTaskCommand(arguments);
    }

    /**
     * Parses a mark command.
     *
     * @param arguments arguments to this command.
     * @return a <code>MarkTaskCommand</code> instance.
     * @throws DukeException when the argument is missing or is not a number.
     */
    private Command parseMarkCommand(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("Seems like the command is incomplete");
        }

        try {
            int indexOfTaskToMark = Integer.parseInt(arguments) - ARRAY_INDEX_OFFSET;
            return new MarkTaskCommand(indexOfTaskToMark);
        } catch (NumberFormatException e) {
            throw new DukeException("The index of task to mark is not a valid integer");
        }
    }

    /**
     * Parses an unmark command.
     *
     * @param arguments arguments to this command.
     * @return a <code>UnmarkTaskCommand</code> instance.
     * @throws DukeException when the argument is missing or is not a number.
     */
    private Command parseUnmarkCommand(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("Seems like the command is incomplete");
        }

        try {
            int indexOfTaskToMark = Integer.parseInt(arguments) - ARRAY_INDEX_OFFSET;
            return new UnmarkTaskCommand(indexOfTaskToMark);
        } catch (NumberFormatException e) {
            throw new DukeException("The index of task to unmark is not a valid integer");
        }
    }

    /**
     * Parses a delete command.
     *
     * @param arguments arguments to this command.
     * @return a <code>DeleteTaskCommand</code> instance.
     * @throws DukeException when the argument is missing or is not a number.
     */
    private Command parseDeleteCommand(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("Seems like the command is incomplete");
        }

        try {
            int indexOfTaskToDelete = Integer.parseInt(arguments) - ARRAY_INDEX_OFFSET;
            return new DeleteTaskCommand(indexOfTaskToDelete);
        } catch (NumberFormatException e) {
            throw new DukeException("The index of task to unmark is not a valid integer");
        }
    }

    /**
     * Parses a todo command.
     *
     * @param arguments arguments to this command.
     * @return a <code>AddToDoTaskCommand</code> instance.
     */
    private Command parseToDoCommand(String arguments) {
        return new AddToDoTaskCommand(arguments);
    }

    /**
     * Parses a deadline command.
     *
     * @param arguments arguments to this command.
     * @return a <code>AddDeadlineTaskCommand</code> instance.
     * @throws DukeException when the arguments are missing or in incorrect format.
     */
    private Command parseDeadlineCommand(String arguments) throws DukeException {
        try {
            String[] deadlineTokens = arguments.split(" /by ");
            String description = deadlineTokens[0];
            LocalDate date = LocalDate.parse(deadlineTokens[1]);
            return new AddDeadlineTaskCommand(description, date);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date provided is invalid(or in wrong format)");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Seems like the command is incomplete");
        }
    }

    /**
     * Parses an event command.
     *
     * @param arguments arguments to this command.
     * @return a <code>AddEventTaskCommand</code> instance.
     * @throws DukeException when the arguments are missing or in incorrect format.
     */
    private Command parseEventCommand(String arguments) throws DukeException {
        try {
            String[] eventTokens = arguments.split(" /at ");
            String description = eventTokens[0];
            String at = eventTokens[1];
            return new AddEventTaskCommand(description, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Seems like the command is incomplete");
        }
    }
}
