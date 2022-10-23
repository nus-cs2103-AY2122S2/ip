package luke.parser;

import java.time.LocalDateTime;
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
import luke.commands.RecurCommand;
import luke.commands.TodoCommand;
import luke.commands.UnmarkCommand;
import luke.commands.UpdateCommand;
import luke.data.tasks.Deadline;
import luke.data.tasks.Event;
import luke.data.tasks.RecurDeadline;
import luke.data.tasks.RecurEvent;
import luke.data.tasks.RecurTodo;
import luke.data.tasks.RecurringTask;
import luke.data.tasks.Todo;

/**
 * Implements the parser class which parses user input and returns commands to execute.
 */
public class Parser {
    private static final String INVALID_DATE_FORMAT_MESSAGE = "The force does not comprehend the date:\n%s";
    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "The force cannot convert the value to a number.";
    private static final String EMPTY_DESCRIPTION_MESSAGE = "The description of %s cannot be empty.";
    private static final String MISSING_ARGUMENT_MESSAGE = "%s command requires the %s argument.";
    private static final String INVALID_NUMBER_OF_ARGUMENT_MESSAGE = "%s has invalid number of arguments.";
    private static final String MISSING_KEYWORD_MESSAGE = "find command requires a keyword argument.";
    private static final String MISSING_INDEX_MESSAGE = "The index of %s cannot be empty.";
    private static final String COMMAND_NOT_FOUND = "Command not found.";
    private static final String RECUR_ERROR_MESSAGE = "recur command encountered an error:\n";
    private static final String COMMAND_NOT_SUPPORTED = "Command not supported.";
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
            put("recur", CommandAction.RECUR);
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
            CommandAction cmdAction = commandActionMap.getOrDefault(inputs[0], CommandAction.INVALID);
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
            return new InvalidCommand(String.format(INVALID_DATE_FORMAT_MESSAGE, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Prepares and returns the appropriate read command.
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
     * Prepares and returns the appropriate update command.
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
     * Prepares and returns the appropriate add command.
     *
     * @param cmdAction The command action tied to the user input.
     * @param args      The arguments that the user have supplied.
     * @return The appropriate add command based on user input.
     * @throws IllegalArgumentException If the command is not an add command.
     */
    private static AddCommand prepareAddCommand(CommandAction cmdAction, String[] args) {
        assert(cmdAction.getCommandActionType() == ActionType.ADD);
        verifyAddCommand(cmdAction, args);

        if (cmdAction == CommandAction.RECUR) {
            return prepareRecurCommand(args);
        }

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

    private static AddCommand prepareRecurCommand(String[] args) {
        Map<String, String> argsMap = parseRecurArguments(args);
        String taskCommand = argsMap.get("task");
        String[] taskArgs = taskCommand.split(" ", 2);
        CommandAction cmdAction = commandActionMap.getOrDefault(taskArgs[0], CommandAction.INVALID);
        try {
            verifyAddCommand(cmdAction, taskArgs);
            argsMap.putAll(parseAddArguments(cmdAction, taskArgs));
            RecurringTask recurringTask;
            switch (cmdAction) {
            case DEADLINE:
                recurringTask = new RecurDeadline(argsMap);
                break;
            case EVENT:
                recurringTask = new RecurEvent(argsMap);
                break;
            case TODO:
                recurringTask = new RecurTodo(argsMap);
                break;
            default:
                throw new IllegalArgumentException(COMMAND_NOT_SUPPORTED);
            }
            return new RecurCommand(recurringTask);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(RECUR_ERROR_MESSAGE + e.getMessage());
        }
    }

    private static void verifyAddCommand(CommandAction cmdAction, String[] args) {
        if (cmdAction.getCommandActionType() != ActionType.ADD) {
            throw new IllegalArgumentException(COMMAND_NOT_SUPPORTED);
        }
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENT_MESSAGE, args[0]));
        }
    }


    /**
     * Parses and returns the arguments tied to the add commands.
     *
     * @param cmdAction The command action tied to the user input.
     * @param args      The arguments that the user have supplied.
     * @return The arguments mapped to the keywords of the command.
     * @throws IllegalArgumentException If the argument does not fit the syntax of the command.
     */
    private static Map<String, String> parseAddArguments(CommandAction cmdAction, String[] args)
            throws IllegalArgumentException {
        assert(args.length == 2);
        assert(cmdAction.getCommandActionType() == ActionType.ADD);

        //add description argument identifier
        args[1] = "description " + args[1];
        Map<String, String> argsMap = new HashMap<>();
        String[] actualArgs = args[1].split("/(?=[A-Za-z])");
        String[] expectedArgs = cmdAction.getArgumentKeys().split(",");

        verifyArguments(args[0], actualArgs, expectedArgs);

        for (int i = 0; i < expectedArgs.length; i++) {
            String[] argValuePair = actualArgs[i].split(" ", 2);
            argsMap.put(argValuePair[0], argValuePair[1]);
        }

        return argsMap;
    }

    private static Map<String, String> parseRecurArguments(String[] args) {
        assert(args.length == 2);
        Map<String, String> argsMap = new HashMap<>();

        // Add next argument
        if (!args[1].contains("/next")) {
            args[1] += " /next " + DateTimeParser.toCommandString(LocalDateTime.now());
        }

        String[] actualArgs = args[1].split("/(?=every|next)");

        actualArgs[0] = "task " + actualArgs[0];
        String[] expectedArgs = CommandAction.RECUR.getArgumentKeys().split(",");
        verifyArguments("recur", actualArgs, expectedArgs);

        for (int i = 0; i < actualArgs.length; i++) {
            String[] argValuePair = actualArgs[i].split(" ", 2);
            argsMap.put(argValuePair[0], argValuePair[1]);
        }

        return argsMap;
    }

    private static void verifyArguments(String command, String[] actualArgs, String[] expectedArgs) {
        for (int i = 0; i < expectedArgs.length; i++) {
            if (i == actualArgs.length) {
                throw new IllegalArgumentException(String.format(MISSING_ARGUMENT_MESSAGE, command, expectedArgs[i]));
            }
            String[] argValuePair = actualArgs[i].split(" ", 2);
            if (argValuePair.length < 2) {
                throw new IllegalArgumentException(String.format(MISSING_ARGUMENT_MESSAGE, command, expectedArgs[i]));
            }
            if (argValuePair[1].isBlank()) {
                throw new IllegalArgumentException(String.format(MISSING_ARGUMENT_MESSAGE, command, expectedArgs[i]));
            }
            if (!argValuePair[0].equalsIgnoreCase(expectedArgs[i])) {
                throw new IllegalArgumentException(String.format(MISSING_ARGUMENT_MESSAGE, command, expectedArgs[i]));
            }
        }
    }

    /**
     * Parses and returns the arguments tied to the find command
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
     * Parses and returns the arguments tied to the update commands.
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
