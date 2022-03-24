public class Parser {
    public static Command parse(String fullCommand) {
        String[] command = fullCommand.split(" ", 2);
        switch (command[0].toUpperCase()) {
        case "TODO":
            return new TodoCommand(command[1]);
        case "DEADLINE":
            return new DeadlineCommand(command[1]);
        case "EVENT":
            return new EventCommand(command[1]);
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(command[1]);
        case "UNMARK":
            return new UnmarkCommand(command[1]);
        case "DELETE":
            return new DeleteCommand(command[1]);
        case "BYE":
            return new ByeCommand();
        default:
            return new ErrorCommand();
        }
    }
}
