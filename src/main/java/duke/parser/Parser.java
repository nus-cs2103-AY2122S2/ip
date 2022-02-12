package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
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
     *
     * Reads user input and outputs the desired Command.
     *
     * @param input Input by the user.
     * @return Command that is desired according to input.
     */
    public String parse(String input, TaskList tasks, Storage storage) {
        Command command = new InvalidCommand();
        try {
            String[] inputWords = splitWhiteSpace(input);
            //action is first word of the input
            String actionWord = inputWords[0];
            Action action = Parser.getAction(actionWord);
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
            case HELP:
                command = new HelpCommand();
                break;
            default:
                command = new InvalidCommand();
            }
        } catch (IllegalArgumentException e) {
            command = new InvalidCommand();
        } catch (NullPointerException e) {
            command = new InvalidCommand();
        } finally {
            return command.execute(tasks, ui, storage);
        }
    }
    
    /**
     * Gets Action from input string.
     *
     * @param command input string.
     * @return the Action.
     * @throws IllegalArgumentException exception when not one of enum types.
     * @throws NullPointerException exception when input string is null.
     */
    public static Action getAction(String command) throws IllegalArgumentException, NullPointerException {
        return Action.valueOf(command.toUpperCase());
    }

    /**
     * Checks if user has input 'bye' command.
     *
     * @param input user input.
     * @return whether input equals bye, ignoring case.
     */
    public static boolean isExit(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Returns the Description of task.
     *
     * @param input Input task to parse.
     * @return Description of task.
     * @throws InvalidArgumentException Exception when there are no keywords provided.
     */
    public static String parseDescription(String input) throws InvalidArgumentException {
        String[] arr = splitWhiteSpace(input);

        //no keyword given by user
        if (arr.length <= 1) {
            Action action = Parser.getAction(arr[0]);
            if (action.equals(Action.TODO)) {
                throw new InvalidArgumentException(Messages.UNKNOWN_TODO);
            } else if (action.equals(Action.FIND)) {
                throw new InvalidArgumentException(Messages.UNKNOWN_FIND);
            } else {
                assert false : "no commands other than todo and find should use parseDescription";
            }
        }

        String description = arr[1].trim();
        return description;
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
        /* Example: deadline abc /by xyz is split into:
           deadline abc AND xyz.
         */
        String[] deadlineArr = splitWithRegex(input, "/by");

        // Example: deadline deadlineDescription is split to deadline and deadlineDescription.
        String[] deadlineSplit = splitWhiteSpace(deadlineArr[0]);

        // deadline with no description stated.
        if (deadlineSplit.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_DEADLINE);
        }
        // deadline with no /by keyword.
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
        /* Example: event abc /at xyz is split into:
           event abc AND xyz
         */
        String[] eventArr = splitWithRegex(input, "/at");

        // Example: deadline deadlineDescription is split to deadline and deadlineDescription.
        String[] eventSplit = splitWhiteSpace(eventArr[0]);

        //event with no description stated.
        if (eventSplit.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_EVENT);
        }
        //event with no /at stated.
        if (eventArr.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_LOCATION);
        }
        // description, and /at respectively
        return new String[]{eventSplit[1].trim(), eventArr[1].trim()};
    }

    /**
     * Splits a String into an array of strings, with white space as divider.
     *
     * @param input String to be split.
     * @return String array result after dividing.
     */
    public static String[] splitWhiteSpace(String input) {
        return input.split("\\s", 2);
    }

    /**
     * Splits the string with given regex.
     *
     * @param input String to be split.
     * @param regex the dividing regex that splits the String.
     * @return array of split Strings.
     */
    public static String[] splitWithRegex(String input, String regex) {
        return input.split(regex, 2);
    }


}
