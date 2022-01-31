package duke.parser;

import duke.Commands.AddDeadlineCommand;
import duke.Commands.AddEventCommand;
import duke.Commands.AddToDoCommand;
import duke.Commands.RemoveCommand;
import duke.Commands.MarkCommand;
import duke.Commands.UnmarkCommand;
import duke.Commands.FindCommand;
import duke.Commands.ListCommand;
import duke.tasklist.TaskList;
import duke.exceptions.DukeException;

import java.util.Locale;

public class Parser {

    public static void processUserInput(CommandType commandType, String userInput,
                                        TaskList taskList) throws DukeException {
        switch (commandType) {
            case LIST:
                new ListCommand(taskList);
                break;
            case MARK:
                new MarkCommand(taskList, userInput);
                break;
            case UNMARK:
                new UnmarkCommand(taskList, userInput);
                break;
            case DEADLINE:
                new AddDeadlineCommand(taskList, userInput);
                break;
            case EVENT:
                new AddEventCommand(taskList, userInput);
                break;
            case TODO:
                new AddToDoCommand(taskList, userInput);
                break;
            case REMOVE:
                new RemoveCommand(taskList, userInput);
                break;
            case FIND:
                new FindCommand(taskList, userInput);
                break;
            case BYE:
                break;
            default:
                throw new DukeException("Invalid input");
        }
    }

    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, FIND, BYE, NULL
    }

    public static CommandType getCommandType(String userInput) {
        String command = userInput.split(" ")[0].toLowerCase(Locale.ROOT);

        switch (command) {
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "remove":
                return CommandType.REMOVE;
            case "find":
                return CommandType.FIND;
            case "bye":
                return CommandType.BYE;
            default:
                return CommandType.NULL;
        }
    }
}
