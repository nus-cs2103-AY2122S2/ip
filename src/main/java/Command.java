import java.util.HashMap;
import java.util.Map;

public class Command {
    private CommandAction commandAction;
    private Map<String,String> arguments;

    Command(CommandAction commandAction, Map<String,String> arguments) {
        this.commandAction = commandAction;
        this.arguments = arguments;
    }

    Command(CommandAction commandAction, String arguments) {
        this(commandAction, parseArguments(commandAction, arguments));
    }

    Command(CommandAction commandAction) {
        this(commandAction, parseArguments(commandAction, ""));
    }

    public CommandAction getCommandAction() {
        return this.commandAction;
    }

    public Map<String, String> getArguments() {
        return this.arguments;
    }

    public String getArgumentByKey(String key) { return this.arguments.get(key); }

    public boolean isExitCmd() {
        return this.commandAction == CommandAction.EXIT;
    }

    public static Map<String,String> parseArguments(CommandAction cmdType, String args) {
        Map<String, String> argsMap = new HashMap<>();
        if(args.length() != 0) {
            switch(cmdType.getCommandActionType()) {
                case ADD:
                    String[] inputs = args.split("/", 2);
                    argsMap.put("description", inputs[0]);
                    switch(cmdType) {
                        case DEADLINE:
                            inputs = inputs[1].split(" ",2);
                            argsMap.put("by", inputs[1]);
                            break;
                        case EVENT:
                            inputs = inputs[1].split(" ",2);
                            argsMap.put("at", inputs[1]);
                            break;
                    }
                    break;
                case UPDATE:
                    argsMap.put("index", args);
                    break;
                default:
                    break;
            }
        }
        return argsMap;
    }

    public static Command parseCommand(String input) {
        String[] inputs = input.split(" ", 2);
        if (inputs[0].equalsIgnoreCase("bye")) {
            return new Command(CommandAction.EXIT);
        } else if (inputs[0].equalsIgnoreCase("list")) {
            return new Command(CommandAction.LIST);
        } else if (inputs[0].equalsIgnoreCase("mark")) {
            return new Command(CommandAction.MARK, inputs[1]);
        } else if (inputs[0].equalsIgnoreCase("unmark")) {
            return new Command(CommandAction.UNMARK, inputs[1]);
        } else if (inputs[0].equalsIgnoreCase("todo")) {
            return new Command(CommandAction.TODO, inputs[1]);
        } else if (inputs[0].equalsIgnoreCase("deadline")) {
            return new Command(CommandAction.DEADLINE, inputs[1]);
        } else if (inputs[0].equalsIgnoreCase("event")) {
            return new Command(CommandAction.EVENT, inputs[1]);
        } else {
            return new Command(CommandAction.UNKNOWN, input);
        }
    }
}
