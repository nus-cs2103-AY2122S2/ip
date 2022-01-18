package baron.commands;

public class CommandParser {
    public static Command parseCommand(String fullCommand) {
        fullCommand = fullCommand.strip();
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else {
            return new EchoCommand(fullCommand);
        }
    }
}
