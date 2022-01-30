package luke.parser;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

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

public class Parser {
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

    public static Command parse(String input) {
        String[] inputs = input.split(" ", 2);
        try {
            if (commandActionMap.containsKey(inputs[0])) {
                CommandAction cmdAction = commandActionMap.get(inputs[0]);
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
                    break;
                }
            }
            return new InvalidCommand();
        } catch (NumberFormatException e) {
            return new InvalidCommand("The force cannot convert the value to a number.");
        } catch (DateTimeParseException e) {
            return new InvalidCommand("The force does not comprehend the date.");
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    public static ReadCommand prepareReadCommand(CommandAction cmdAction, String[] args) {
        switch (cmdAction) {
        case FIND:
            String keyword = parseFilterArgument(args);
            return new FindCommand(keyword);
        default:
            return new ListCommand();
        }
    }

    public static UpdateCommand prepareUpdateCommand(CommandAction cmdAction, String[] args) {
        int index = parseUpdateArguments(args);
        switch (cmdAction) {
        case MARK:
            return new MarkCommand(index);
        case UNMARK:
            return new UnmarkCommand(index);
        default:
            return new DeleteCommand(index);
        }
    }

    public static AddCommand prepareAddCommand(CommandAction cmdAction, String[] args) {
        Map<String, String> argsMap = parseAddArguments(cmdAction, args);
        switch (cmdAction) {
        case DEADLINE:
            return new DeadlineCommand(new Deadline(argsMap));
        case EVENT:
            return new EventCommand(new Event(argsMap));
        default:
            return new TodoCommand(new Todo(argsMap));
        }
    }

    public static Map<String, String> parseAddArguments(CommandAction cmdAction, String[] args)
            throws IllegalArgumentException {
        Map<String, String> argsMap = new HashMap<>();
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format("The description of %s cannot be empty.", args[0]));
        }
        String[] inputs = args[1].split("/", 2);
        if (inputs[0].isBlank()) {
            throw new IllegalArgumentException(String.format("The description of %s cannot be empty.", args[0]));
        }
        argsMap.put("description", inputs[0]);
        if (cmdAction != CommandAction.TODO) {
            String extraArg = cmdAction.getArgumentKeys().split(",", 2)[1];
            if (inputs.length < 2) {
                throw new IllegalArgumentException(String.format("%s require the %s argument.", args[0], extraArg));
            }
            inputs = inputs[1].split(" ", 2);
            if (inputs[0].equalsIgnoreCase(extraArg)) {
                argsMap.put(extraArg, inputs[1]);
            } else {
                throw new IllegalArgumentException(String.format("%s require the %s argument.", args[0], extraArg));
            }
        }
        return argsMap;
    }

    public static String parseFilterArgument(String[] args) throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format("find command requires a keyword argument.", args[0]));
        }

        if (args[1].isBlank()) {
            throw new IllegalArgumentException(String.format("find command requires a keyword argument.", args[0]));
        }

        return args[1];
    }

    public static int parseUpdateArguments(String[] args) throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException(String.format("The index of %s cannot be empty.", args[0]));
        }
        return Integer.parseInt(args[1]);
    }

}
