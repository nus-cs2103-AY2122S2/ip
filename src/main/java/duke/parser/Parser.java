package duke.parser;

import java.util.Locale;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ReminderCommand;
import duke.commands.HelpCommand;
import duke.commands.RemoveCommand;
import duke.commands.UnmarkCommand;
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
            return new MarkCommand(userInput);
        case UNMARK:
            return new UnmarkCommand(userInput);
        case DEADLINE:
            return new AddDeadlineCommand(userInput);
        case EVENT:
            return new AddEventCommand(userInput);
        case TODO:
            return new AddToDoCommand(userInput);
        case REMOVE:
            return new RemoveCommand(userInput);
        case FIND:
            return new FindCommand(userInput);
        case REMINDER:
            return new ReminderCommand(userInput);
        case HELP:
            return new HelpCommand();
        case BYE:
            return new ByeCommand();
        default:
            throw new DukeException("I don't know what does that mean! </3\n"
                    + "\nSend me the help keyword for the list of commands!");
        }
    }

    /**
     * Types of valid commands
     */
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, FIND, REMINDER, HELP, BYE, NULL
    }

    /**
     * Converts user input into a valid command type
     *
     * @param userInput the input from user
     */
    public static CommandType getCommandType(String userInput) {
        String command = userInput.split(" ")[0].toLowerCase(Locale.ROOT);
        assert !command.equals("") : "Empty Command";
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
        case "reminder":
            return CommandType.REMINDER;
        case "help":
            return CommandType.HELP;
        case "bye":
            return CommandType.BYE;
        default:
            return CommandType.NULL;
        }
    }
}
