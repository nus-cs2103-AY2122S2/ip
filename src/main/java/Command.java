public class Command {
    private CommandType commandType;
    private String arguments;

    Command(CommandType commandType, String arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    Command(CommandType commandType) {
        this(commandType, "");
    }

    Command() {
        this(CommandType.ADD, "");
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public String getArguments() {
        return this.arguments;
    }

    public boolean isExitCmd() {
        return this.commandType == CommandType.EXIT;
    }

    public static Command parseCommand(String input) {
        String[] inputs = input.split(" ", 2);
        if (inputs[0].equals("bye")) {
            return new Command(CommandType.EXIT);
        } else if (inputs[0].equals("list")) {
            return new Command(CommandType.LIST);
        } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")) {
            return new Command(CommandType.MARK, inputs[1]);
        } else {
            return new Command(CommandType.ADD, input);
        }
    }
}
