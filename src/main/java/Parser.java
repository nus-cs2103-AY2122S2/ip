import java.time.LocalDate;

public class Parser {

    public static Command parse(String fullCommand) throws PacException {
        String[] commandArray = fullCommand.split(" ", 2);
        String keyWord = commandArray[0].toLowerCase();
        Commands command;

        switch (keyWord) {
        case "bye":
             command = Commands.BYE;
            break;
        case "list":
            command = Commands.LIST;
            break;
        case "mark":
            command = Commands.MARK;
            break;
        case "unmark":
            command = Commands.UNMARK;
            break;
        case "todo":
            command = Commands.TODO;
            break;
        case "deadline":
            command = Commands.DEADLINE;
            break;
        case "event":
            command = Commands.EVENT;
            break;
        case "delete":
            command = Commands.DELETE;
            break;
        default:
            command = Commands.ERROR;
            break;
        }

        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(commandArray[1]) - 1);
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(commandArray[1]) - 1);
        case TODO:
            return new AddCommand(new ToDo(commandArray[1]));
        case DEADLINE:
            String[] deadlineArray = commandArray[1].split(" /by ", 2);
            return new AddCommand(new Deadline(deadlineArray[0], deadlineArray[1]));
        case EVENT:
            String[] eventArray = commandArray[1].split(" /at ", 2);
            return new AddCommand(new Event(eventArray[0], eventArray[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(commandArray[1]) - 1);
        default:
            throw new PacException("Invalid Command.");
        }
    }
}
