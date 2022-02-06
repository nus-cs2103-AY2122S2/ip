package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.InvalidArgumentException;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * Parser that reads and analyzes strings.
 */
public class Parser {

    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Reads user input and outputs the desired Command.
     *
     * @param input Input by the user.
     * @return Command that is desired according to input.
     */
    public static Command parse(String input) {
        try {
            String[] inputWords = input.split("\\s");
            //action is first word of the input
            Action action = Action.valueOf(inputWords[0].toUpperCase());
            switch (action) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case TODO:
                return new AddCommand(Action.TODO, input);
            case DEADLINE:
                return new AddCommand(Action.DEADLINE, input);
            case EVENT:
                return new AddCommand(Action.EVENT, input);
            case MARK:
                return new MarkCommand(inputWords);
            case UNMARK:
                return new UnmarkCommand(inputWords);
            case DELETE:
                return new DeleteCommand(inputWords);
            case FIND:
                return new FindCommand(input);
            default:
                return new InvalidCommand();
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }
    }

    /**
     * Returns the Description of task.
     *
     * @param input Input task to parse.
     * @return Description of task.
     * @throws InvalidArgumentException Exception when there are no keywords provided.
     */
    public static String parseDescription(String input) throws InvalidArgumentException {
        String[] arr = input.split("\\s", 2);
        //no keyword given by user
        if (arr.length <= 1) {
            // action is either find or todo.
            if (arr[0].equals("todo")) {
                throw new InvalidArgumentException(Messages.UNKNOWN_TODO);
            } else {
                throw new InvalidArgumentException(Messages.UNKNOWN_FIND);
            }
        }
        return arr[1].trim();
    }

    // returns duke.task.Deadline description and dateTime in a String[] like a pair.

    /**
     * Returns the description and dateTime of Deadline task in a String array.
     *
     * @param input Input Deadline to parse.
     * @return String array of description and dateTime.
     * @throws InvalidArgumentException when invalid format is given in user input.
     */
    public static String[] parseDeadline(String input) throws InvalidArgumentException {
        String[] deadlineArr = input.split("/by", 2);
        String[] deadlineSplit = deadlineArr[0].split("\\s", 2);
        // no description
        if (deadlineSplit.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_DEADLINE);
        }
        // don't have /by keyword
        if (deadlineArr.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_DATETIME);
        }
        return new String[]{deadlineSplit[1].trim(), deadlineArr[1].trim()};
    }


    /**
     * Returns the description and 'at' of Event task in a String array.
     *
     * @param input Input Event to parse.
     * @return String array of description and 'at'.
     * @throws InvalidArgumentException when invalid is given in user input.
     */
    public static String[] parseEvent(String input) throws InvalidArgumentException {
        String[] eventArr = input.split("/at", 2);
        String[] eventSplit = eventArr[0].split("\\s", 2);
        if (eventSplit.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_EVENT);
        }
        if (eventArr.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_LOCATION);
        }
        // description, and at respectively
        return new String[]{eventSplit[1].trim(), eventArr[1].trim()};
    }

}
