package gene.component;

import gene.command.*;
import gene.exception.UnrecognizedCommandException;

/**
 * The parser class. Contains the method which helps to parse and distinguish
 * between commands, handles all user inputs and turns them to commands.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class Parser {
    private final static String EXITKEY = "bye";
    private final static String LISTKEY = "list";
    private final static String DELETEKEY = "delete\\s\\d";
    private final static String MARKKEY = "mark\\s\\d";
    private final static String UNMARKKEY = "unmark\\s\\d";
    private final static String TODOKEY = "^todo.*$";
    private final static String EVENTKEY = "^event.*$";
    private final static  String DEADLINEKEY = "^deadline.*$";

    public Parser() {
    }

    /**
     * The parseCommand method takes in the user's input and returns a
     * corresponding executable command
     *
     * @param toParse
     * @return Command to be executed
     * @throws Exception thrown if user input matches no command
     */
    public static Command parseCommand(String toParse) throws Exception {
        if (isListCommand(toParse)) {
            return new ListCommand();
        }

        if (isDeleteCommand(toParse)) {
            return new DeleteCommand(toParse);
        }

        if (isMarkCommand(toParse)) {
            return new EditCommand(toParse, "1");
        }

        if (isUnmarkCommad(toParse)) {
            return new EditCommand(toParse, "0");
        }

        if (isTodoCommand(toParse)) {
            return new AddCommand(toParse, "todo");
        }

        if (isDeadlineCommand(toParse)) {
            return new AddCommand(toParse, "deadline");
        }

        if (isEventCommand(toParse)) {
            return new AddCommand(toParse, "event");
        }

        if (isExitCommand(toParse)) {
            return new ExitCommand();
        }

        else {
            throw new UnrecognizedCommandException("");
        }

    }

    /**
     * Method that figures out if input matches the description of an exit
     * command.
     *
     * @param nextKey the user input
     * @return true if user input corresponds to exit command
     */
    public static boolean isExitCommand(String nextKey) {
        return nextKey.equals(EXITKEY);
    }

    /**
     * Method that figures out if input matches the description of a delete
     * command. uses regex as described above
     *
     * @param nextKey the user input
     * @return true if user input corresponds to delete command
     */
    public static boolean isDeleteCommand(String nextKey) {
        return nextKey.matches(DELETEKEY);
    }

    /**
     * Method that figures out if input matches the description of a list
     * command.
     *
     * @param key the user input
     * @return true if user input corresponds to list command
     */
    public static boolean isListCommand(String key) {
        return key.equals(LISTKEY);
    }

    /**
     * Method that figures out if input matches the description of a mark
     * command. uses regex as described above
     *
     * @param nextKey the user input
     * @return true if user input corresponds to mark command
     */
    public static boolean isMarkCommand(String nextKey) {
        return nextKey.matches(MARKKEY);
    }

    /**
     * Method that figures out if input matches the description of an unmark
     * command. uses regex as described above
     *
     * @param nextKey the user input
     * @return true if user input corresponds to unmark command
     */
    public static boolean isUnmarkCommad(String nextKey) {
        return nextKey.matches(UNMARKKEY);
    }

    /**
     * Method that figures out if input matches the description of a add to do
     * command. uses regex as described above
     *
     * @param nextKey the user input
     * @return true if user input corresponds to to do command
     */
    public static boolean isTodoCommand(String nextKey) {
        return nextKey.matches(TODOKEY);
    }

    /**
     * Method that figures out if input matches the description of a add event
     * command. uses regex as described above
     *
     * @param nextKey the user input
     * @return true if user input corresponds to event command
     */
    public static boolean isEventCommand(String nextKey) {
        return nextKey.matches(EVENTKEY);
    }

    /**
     * Method that figures out if input matches the description of add deadline
     * command. uses regex as described above
     *
     * @param nextKey the user input
     * @return true if user input corresponds to deadline command
     */
    public static boolean isDeadlineCommand(String nextKey) {
        return nextKey.matches(DEADLINEKEY);
    }
}
