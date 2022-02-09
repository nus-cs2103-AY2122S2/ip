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
import duke.storage.Storage;
import duke.task.TaskList;
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
    public String parse(String input, TaskList tasks, Storage storage) {
        Command command = new InvalidCommand();
        try {
            String[] inputWords = input.split("\\s");
            //action is first word of the input
            Action action = Action.valueOf(inputWords[0].toUpperCase());
            switch (action) {
            case BYE:
                command = new ExitCommand();
                break;
            case LIST:
                command = new ListCommand();
                break;
            case TODO:
                command = new AddCommand(Action.TODO, input);
                break;
            case DEADLINE:
                command = new AddCommand(Action.DEADLINE, input);
                break;
            case EVENT:
                command = new AddCommand(Action.EVENT, input);
                break;
            case MARK:
                command = new MarkCommand(inputWords);
                break;
            case UNMARK:
                command = new UnmarkCommand(inputWords);
                break;
            case DELETE:
                command = new DeleteCommand(inputWords);
                break;
            case FIND:
                command = new FindCommand(input);
                break;
            default:
                command = new InvalidCommand();
            }
        } catch (IllegalArgumentException e) {
            command = new InvalidCommand();
        } finally {
            return command.execute(tasks, ui, storage);
        }
    }

    public boolean isExit(String input) {
        return input.equals("bye");
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
