public class Command {
    private CommandType commandType;

    Command(CommandType commandType) {
        this.commandType = commandType;
    }

    Command() {
        new Command(CommandType.DEFAULT);
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void printOutput(String output) {
        String msg = "============================================================\n"
                + String.format("%s: %s\n", Duke.BOT_NAME, output)
                + "============================================================";
        System.out.println(msg);
    }

    public boolean isExitCmd() {
        return this.commandType == CommandType.EXIT;
    }

    public static Command parseCommand(String input) {
        if (input.equals("bye")) {
            return new Command(CommandType.EXIT);
        } else {
            Command cmd = new Command(CommandType.DEFAULT);
            cmd.printOutput(input);
            return cmd;
        }
    }
}
