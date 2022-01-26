public class Parser {

    public static Command parse(String fullCommand) {
        String[] fullCommandArr = fullCommand.split(" ");
        Command command = null;
        switch(fullCommandArr[0]) {
        case "bye": command = new ByeCommand();
            break;
        case "list": command = new ListCommand();
            break;
        case "mark": command = new MarkCommand(fullCommand, fullCommandArr);
            break;
        case "unmark": command = new UnmarkCommand(fullCommand, fullCommandArr);
            break;
        case "todo": command = new ToDoCommand(fullCommand, fullCommandArr);
            break;
        case "deadline": command = new DeadlineCommand(fullCommand, fullCommandArr);
            break;
        case "event": command = new EventCommand(fullCommand, fullCommandArr);
            break;
        case "delete": command = new DeleteCommand(fullCommand, fullCommandArr);
            break;
        default: command = new InvalidCommand();
        }
        return command;
    }
}
