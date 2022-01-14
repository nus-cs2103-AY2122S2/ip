public class Command {
    private CommandType commandType;
    private String input;

    Command(CommandType commandType, String input) {
        this.commandType = commandType;
        this.input = input;
    }

    Command() {
        new Command(CommandType.ADD, "");
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getInput() {
        return input;
    }

    public boolean isExitCmd() {
        return this.commandType == CommandType.EXIT;
    }

    public static Command parseCommand(String input) {
        if (input.equals("bye")) {
            return new Command(CommandType.EXIT, input);
        } else if (input.equals("list")) {
            return new Command(CommandType.LIST, input);
        } else {
            return new Command(CommandType.ADD, input);
        }
    }
}
