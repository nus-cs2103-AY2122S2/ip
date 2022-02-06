package duke.parser;

import java.util.Locale;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasklist.TaskList;


public class Parser {

    /**
     * Process and validate user input to instantiate intended class
     *
     * @param commandType the type of task
     * @param userInput the input from the user
     * @param taskList the list of tasks
     * @throws DukeException if input from user is invalid
     */
    public static Command processUserInput(CommandType commandType, String userInput,
                                        TaskList taskList) throws DukeException {
        switch (commandType) {
        case LIST:
            return new ListCommand(taskList);
        case MARK:
            return new MarkCommand(taskList, userInput);
        case UNMARK:
            return new UnmarkCommand(taskList, userInput);
        case DEADLINE:
            return new AddDeadlineCommand(taskList, userInput);
        case EVENT:
            return new AddEventCommand(taskList, userInput);
        case TODO:
            return new AddToDoCommand(taskList, userInput);
        case REMOVE:
            return new RemoveCommand(taskList, userInput);
        case FIND:
            return new FindCommand(taskList, userInput);
        case BYE:
            return new ByeCommand();
        default:
            throw new DukeException("Invalid input");
        }
    }

    /**
     * Types of valid commands
     */
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, FIND, BYE, NULL
    }

    /**
     * Converts user input into a valid command type
     *
     * @param userInput the input from user
     */
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
