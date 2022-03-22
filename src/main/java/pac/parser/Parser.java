package pac.parser;

import pac.command.AddCommand;
import pac.command.Command;
import pac.command.FindCommand;
import pac.command.RescheduleCommand;
import pac.task.Deadline;
import pac.command.DeleteCommand;
import pac.task.Event;
import pac.command.ExitCommand;
import pac.command.ListCommand;
import pac.command.MarkCommand;
import pac.PacException;
import pac.task.ToDo;
import pac.command.UnmarkCommand;

/**
 * Parser parses the user input and returns a Command
 */
public class Parser {

    /**
     * parses the user input and returns the corresponding command
     * @param fullCommand
     * @return
     * @throws PacException
     */
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
        case "find":
            command = Commands.FIND;
            break;
        case "reschedule":
            command = Commands.RESCHEDULE;
            break;
        default:
            throw new PacException("Invalid Command.");
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
            if(commandArray[1].trim().length() == 0) {
                throw new PacException("Invalid Command Format");
            }
            return new AddCommand(new ToDo(commandArray[1]));
        case DEADLINE:
            String[] deadlineArray = commandArray[1].split(" /by ", 2);
            return new AddCommand(new Deadline(deadlineArray[0], deadlineArray[1]));
        case EVENT:
            String[] eventArray = commandArray[1].split(" /at ", 2);
            return new AddCommand(new Event(eventArray[0], eventArray[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(commandArray[1]) - 1);
        case FIND:
            return new FindCommand(commandArray[1]);
        case RESCHEDULE:
            String[] rescheduleArray = commandArray[1].split(" /r ", 2);
            return new RescheduleCommand(Integer.parseInt(rescheduleArray[0]) - 1, rescheduleArray[1]);
        default:
            throw new PacException("Invalid Command.");
        }
    }
}
