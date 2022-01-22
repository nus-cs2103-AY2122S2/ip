import java.util.HashMap;
import java.util.Map;

public class Command {
    private CommandAction commandAction;
    private Map<String, String> arguments;
    private static Map<String, CommandAction> commandActionMap = new HashMap<>() {{
        put("bye", CommandAction.EXIT);
        put("list", CommandAction.LIST);
        put("mark", CommandAction.MARK);
        put("unmark", CommandAction.UNMARK);
        put("todo", CommandAction.TODO);
        put("event", CommandAction.EVENT);
        put("deadline", CommandAction.DEADLINE);
        put("delete", CommandAction.DELETE);
        put("save", CommandAction.SAVE);
    }};

    Command(CommandAction commandAction, Map<String, String> arguments) {
        this.commandAction = commandAction;
        this.arguments = arguments;
    }

    Command(CommandAction commandAction, String[] arguments) {
        this(commandAction, parseArguments(commandAction, arguments));
    }

    Command() {
        this(CommandAction.UNKNOWN, new HashMap<>());
    }

    public CommandAction getCommandAction() {
        return this.commandAction;
    }

    public Map<String, String> getArguments() {
        return this.arguments;
    }

    public String getArgumentByKey(String key) {
        return this.arguments.get(key);
    }

    public boolean isExitCmd() {
        return this.commandAction == CommandAction.EXIT;
    }

    public static Map<String, String> parseArguments(CommandAction cmdAction, String[] args) {
        Map<String, String> argsMap = new HashMap<>();
        if (!cmdAction.getArgumentKeys().isBlank()) {

            switch (cmdAction.getCommandActionType()) {
                case ADD:
                    if (args.length < 2) {
                        throw new IllegalArgumentException(String.format("The description of %s cannot be empty.", args[0]));
                    }
                    String[] inputs = args[1].split("/", 2);
                    if(inputs[0].isBlank()) {
                        throw new IllegalArgumentException(String.format("The description of %s cannot be empty.", args[0]));
                    }
                    argsMap.put("description", inputs[0]);
                    if (cmdAction != CommandAction.TODO) {
                        String extraArg = cmdAction.getArgumentKeys().split(",",2)[1];
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
                    break;
                case UPDATE:
                    if (args.length < 2) {
                        throw new IllegalArgumentException(String.format("The index of %s cannot be empty.", args[0]));
                    }
                    Integer.parseInt(args[1]);
                    argsMap.put("index", args[1]);
                    break;
                default:
                    break;
            }
        }
        return argsMap;
    }

    public static Command parseCommand(String input) throws UnknownCommandException, IllegalArgumentException {
        String[] inputs = input.split(" ", 2);
        if (commandActionMap.containsKey(inputs[0].toLowerCase())) {
            return new Command(commandActionMap.get(inputs[0].toLowerCase()), inputs);
        } else {
            throw new UnknownCommandException();
        }
    }
}
