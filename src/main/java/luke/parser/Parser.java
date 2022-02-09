package luke.parser;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import luke.commands.ActionType;
import luke.commands.AddCommand;
import luke.commands.Command;
import luke.commands.CommandAction;
import luke.commands.DeadlineCommand;
import luke.commands.DeleteCommand;
import luke.commands.EventCommand;
import luke.commands.ExitCommand;
import luke.commands.FindCommand;
import luke.commands.InvalidCommand;
import luke.commands.ListCommand;
import luke.commands.MarkCommand;
import luke.commands.ReadCommand;
import luke.commands.TodoCommand;
import luke.commands.UnmarkCommand;
import luke.commands.UpdateCommand;
import luke.data.tasks.Deadline;
import luke.data.tasks.Event;
import luke.data.tasks.Todo;

/**
 * Implements the parser class which parses user input and returns commands to execute.
 */
public class Parser {
    private static final String INVALID_DATE_FORMAT_MESSAGE = "The force does not comprehend the date.";
    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "The force cannot convert the value to a number.";
    private static final String EMPTY_DESCRIPTION_MESSAGE = "The description of %s cannot be empty.";
    private static final String MISSING_ARGUMENT_MESSAGE = "%s require the %s argument.";
    private static final String MISSING_KEYWORD_MESSAGE = "find command requires a keyword argument.";
    private static final String MISSING_INDEX_MESSAGE = "The index of %s cannot be empty.";
    private static final String COMMAND_NOT_FOUND = "Command not found.";
    /* Map of accepted user commands tied to the respective command action type. */
    private static Map<String, CommandAction> commandActionMap = new HashMap<>() {{
            put("bye", CommandAction.EXIT);
            put("list", CommandAction.LIST);
            put("mark", CommandAction.MARK);
            put("unmark", CommandAction.UNMARK);
            put("todo", CommandAction.TODO);
            put("event", CommandAction.EVENT);
            put("deadline", CommandAction.DEADLINE);
            put("delete", CommandAction.DELETE);
            put("find", CommandAction.FIND);
        }};

    /**
     * Parses the specified input and returns the appropriate command.
     *
     * @param input The specified input to parse.
     * @return The appropriate command based on the specified input.
     */
    public static Command parse(String input) {
        String[] inputs = input.split(" ", 2);
        try {
            if (!commandActionMap.containsKey(inputs[0])) {
                return new InvalidCommand();
            }
            CommandAction cmdAction = commandActionMap.get(inputs[0]);
            assert(cmdAction != null);
            switch (cmdAction.getCommandActionType()) {
            case NO_ACTION:
                return new ExitCommand();
            case READ:
                return prepareReadCommand(cmdAction, inputs);
            case ADD:
                return prepareAddCommand(cmdAction, inputs);
            case UPDATE:
                return prepareUpdateCommand(cmdAction, inputs);
            default:
                return new InvalidCommand();
            }
        } catch (NumberFormatException e) {
            return new InvalidCommand(INVALID_NUMBER_FORMAT_MESSAGE);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(INVALID_DATE_FORMAT_MESSAGE);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Prepares and return the appropriate read command.
     *
     * @param cmdAction The command action tied to the user input.
     * @param args      The arguments that the user have supplied.
     * @return The appropriate read command based on user input.
     * @throws IllegalArgumentException If the command is not a read command.
     */
    private static ReadCommand prepareReadCommand(CommandAction cmdAction, String[] args) {
        assert(cmdAction.getCommandActionType() == ActionType.READ);
        switch (cmdAction) {
        case FIND:
            String keyword = parseFindArgument(args);
            return new FindCommand(keyword);
        case LIST:
            return new ListCommand();
        default:
            throw new IllegalArgumentException(COMMAND_NOT_FOUND);
        }
    }


    /**
     * Prepares and return the appropriate update command.
     *
     * @param cmdAction The command action tied to the user input.
     * @param args      The arguments that the user have supplied.
     * @return The appropriate update command based on user input.
     * @throws IllegalArgumentException If the command is not a update command.
     */
    private static UpdateCommand prepareUpdateCommand(CommandAction cmdAction, String[] args) {
        assert(cmdAction.getCommandActionType() == ActionType.UPDATE);
        int index = parseUpdateArguments(args);
        switch (cmdAction) {
        case MARK:
            return new MarkCommand(index);
        case UNMARK:
            return new UnmarkCommand(index);
        case DELETE:
            return new DeleteCommand(index);
        default:
            throw new IllegalArgumentException(COMMAND_NOT_FOUND);
        }
    }

    /**
     * Prepares and return the appropriate add command.
     *
     * @param cmdAction The command action tied to the user input.
     * @param args      The arguments that the user have supplied.
     * @return The appropriate add command based on user input.
     * @throws IllegalArgumentException If the command is not an add command.
     */
    private static AddCommand prepareAddCommand(CommandAction cmdAction, String[] args) {
        assert(cmdAction.getCommandActionType() == ActionType.ADD);
        Map<String, String> argsMap = parseAddArguments(cmdAction, args);
        switch (cmdAction) {
        case DEADLINE:
            return new DeadlineCommand(new Deadline(argsMap));
        case EVENT:
            return new EventCommand(new Event(argsMap));
        case TODO:
            return new TodoCommand(new Todo(argsMap));
        default:
            throw new IllegalArgumentException(COMMAND_NOT_FOUND);
        }
    }


    /**
     * Parses and return the arguments tied to the add commands.
     *
     * @param cmdAction The command action tied to the user input.
     * @param args      The arguments that the user have supplied.
     * @return The arguments mapped to the keywords of the command.
     * @throws IllegalArgumentException If the argument does not fit the syntax of the command.
     */
    private static Map<String, String> parseAddArguments(CommandAction cmdAction, String[] args)
            throws IllegalArgumentException {
        assert(cmdAction.getCommandActionType() == ActionType.ADD);
        Map<String, String> argsMap = new HashMap<>();
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format(EMPTY_DESCRIPTION_MESSAGE, args[0]));
        }
        String[] inputs = args[1].split("/", 2);
        if (inputs[0].isBlank()) {
            throw new IllegalArgumentException(String.format(EMPTY_DESCRIPTION_MESSAGE, args[0]));
        }
        argsMap.put("description", inputs[0]);
        if (cmdAction != CommandAction.TODO) {
            String extraArg = cmdAction.getArgumentKeys().split(",", 2)[1];
            if (inputs.length < 2) {
                throw new IllegalArgumentException(String.format(MISSING_ARGUMENT_MESSAGE, args[0], extraArg));
            }
            inputs = inputs[1].split(" ", 2);
            if (inputs[0].equalsIgnoreCase(extraArg)) {
                argsMap.put(extraArg, inputs[1]);
            } else {
                throw new IllegalArgumentException(String.format(MISSING_ARGUMENT_MESSAGE, args[0], extraArg));
            }
        }
        return argsMap;
    }

    /**
     * Parses and return the arguments tied to the find command
     *
     * @param args The arguments that the user have supplied.
     * @return The arguments mapped to the keywords of the command.
     * @throws IllegalArgumentException If the argument does not fit the syntax of the command.
     */
    public static String parseFindArgument(String[] args) throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format(MISSING_KEYWORD_MESSAGE, args[0]));
        }

        if (args[1].isBlank()) {
            throw new IllegalArgumentException(String.format(MISSING_KEYWORD_MESSAGE, args[0]));
        }

        return args[1];
    }

    /**
     * Parses and return the arguments tied to the update commands.
     *
     * @param args The arguments that the user have supplied.
     * @return The arguments mapped to the keywords of the command.
     * @throws IllegalArgumentException If the argument does not fit the syntax of the command.
     */
    private static int parseUpdateArguments(String[] args) throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format(MISSING_INDEX_MESSAGE, args[0]));
        }
        return Integer.parseInt(args[1]);
    }

}
