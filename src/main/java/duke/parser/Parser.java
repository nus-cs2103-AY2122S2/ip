package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    private final HashMap<String, String> aliases;
    private final HashSet<String> reservedCommands;

    /**
     * Class constructor.
     */
    public Parser() {
        aliases = new HashMap<>();
        reservedCommands = new HashSet<>();

        aliases.put("a", "alias");
        aliases.put("b", "bye");
        aliases.put("c", "clear");
        aliases.put("ls", "list");
        aliases.put("f", "find");
        aliases.put("m", "mark");
        aliases.put("um", "unmark");
        aliases.put("del", "delete");
        aliases.put("t", "todo");
        aliases.put("d", "deadline");
        aliases.put("e", "event");
        reservedCommands.addAll(aliases.values());
    }

    /**
     * Parses the input string entered by user into a command known to the application.
     *
     * @param command the command entered by user.
     * @return a command to be executed by the application.
     * @throws DukeException when <code>command</code> is invalid or its format is incorrect.
     */
    public Command parse(String command) throws DukeException {
        String[] commandTokens = command.split(" ", 2);
        String primaryCommand = Optional.ofNullable(aliases.get(commandTokens[0])).orElse(commandTokens[0]);

        try {
            switch (primaryCommand) {
            case "alias":
                String[] aliasMapping = commandTokens[1].split(" ");
                String from = aliasMapping[0];
                String to = aliasMapping[1];
                if (aliasMapping.length != 2) {
                    throw new DukeException("The alias command takes in exactly 2 arguments");
                } else if (reservedCommands.contains(from)) {
                    throw new DukeException("Duke's original command cannot be changed");
                } else if (!reservedCommands.contains(to)) {
                    throw new DukeException("Alias can only refer to Duke's original command");
                }
                aliases.put(from, to);
                return new AliasCommand(from, to);
            case "bye":
                return new ByeCommand();
            case "clear":
                return new ClearTaskCommand();
            case "list":
                return new ListTaskCommand();
            case "find":
                return new FindTaskCommand(commandTokens[1]);
            case "mark":
                int indexOfTaskToMark = Integer.parseInt(commandTokens[1]) - ARRAY_INDEX_OFFSET;
                return new MarkTaskCommand(indexOfTaskToMark);
            case "unmark":
                int indexOfTaskToUnmark = Integer.parseInt(commandTokens[1]) - ARRAY_INDEX_OFFSET;
                return new UnmarkTaskCommand(indexOfTaskToUnmark);
            case "delete":
                int indexOfTaskToDelete = Integer.parseInt(commandTokens[1]) - ARRAY_INDEX_OFFSET;
                return new DeleteTaskCommand(indexOfTaskToDelete);
            case "todo":
                return new AddToDoTaskCommand(commandTokens[1]);
            case "deadline":
                String[] deadlineTokens = commandTokens[1].split(" /by ");
                LocalDate date = LocalDate.parse(deadlineTokens[1]);
                return new AddDeadlineTaskCommand(deadlineTokens[0], date);
            case "event":
                String[] eventTokens = commandTokens[1].split(" /at ");
                return new AddEventTaskCommand(eventTokens[0], eventTokens[1]);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("The date provided is invalid(or in wrong format)");
        } catch (NumberFormatException e) {
            throw new DukeException("The index of task to " + commandTokens[0] + " is not a valid integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Seems like the command is incomplete");
        }
    }
}
