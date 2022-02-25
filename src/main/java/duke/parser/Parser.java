package duke.parser;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Encapsulates parsing methods to read user input
 */
public class Parser {
    /**
     * A string that prefixes the program bye command.
     */
    public static final String COMMAND_BYE = "bye";
    /**
     * A string that prefixes the program list command.
     */
    public static final String COMMAND_LIST = "list";
    /**
     * A string that prefixes the program delete command.
     */
    public static final String COMMAND_DELETE = "delete";
    /**
     * A string that prefixes the program mark command.
     */
    public static final String COMMAND_MARK = "mark";
    /**
     * A string that prefixes the program unmark command.
     */
    public static final String COMMAND_UNMARK = "unmark";
    /**
     * A string that prefixes the program deadline command.
     */
    public static final String COMMAND_DEADLINE = "deadline";
    /**
     * A string that prefixes the program event command.
     */
    public static final String COMMAND_EVENT = "event";
    /**
     * A string that prefixes the program todo command.
     */
    public static final String COMMAND_TODO = "todo";

    /**
     * A string that flags the deadline date substring.
     */
    public static final String DEADLINE_DATE = " /by ";
    /**
     * A string that flags the event date substring.
     */
    public static final String EVENT_DATE = " /at ";

    /**
     * Parses user input string
     *
     * @param input user input to be parsed by the method
     * @return the command object parsed out from user input
     * @throws DukeException when user input cannot be parsed into a command
     */
    public static Command parse(String input) throws DukeException {
        StringTokenizer st = new StringTokenizer(input);
        String cmd = st.nextToken();

        switch (cmd) {
            case COMMAND_BYE:
                return new ExitCommand();
            case COMMAND_LIST:
                return new ListCommand();
            case COMMAND_DELETE:
                return new DeleteCommand(getIndex(st));
            case COMMAND_MARK:
                return new MarkCommand(getIndex(st));
            case COMMAND_UNMARK:
                return new UnmarkCommand(getIndex(st));
            case COMMAND_TODO:
                return new TodoCommand(validDescription(input.replaceFirst(cmd, "")));
            case COMMAND_DEADLINE:
                return parseDeadline(input.replaceFirst(cmd, ""));
            case COMMAND_EVENT:
                return parseEvent(input.replaceFirst(cmd, ""));
            default:
                throw new InvalidCommandException("Maybe try not trying to break my chatbot? thx xoxo");
        }
    }

    /**
     * Gets index of task used to instantiate
     * command object from a StringTokenizer
     *
     * @param st StringTokenizer to be parsed
     * @return index parsed out of StringTokenizer
     * @throws DukeException when index was not specified by user
     */
    private static int getIndex(StringTokenizer st) throws DukeException {
        try {
            return Integer.parseInt(st.nextToken());
        } catch (NoSuchElementException e) {
            throw new DukeException("Maybe try specifyin' what task you want next time? jfc");
        }
    }

    /**
     * Parse deadline date from the user input
     *
     * @param description command argument for the deadline command
     * @return deadline command object parsed out from the command input
     * @throws DukeException when deadline of deadline task is not
     * specified by user in the command input
     */
    private static DeadlineCommand parseDeadline(String description) throws DukeException {
        try {
            String[] deadlineTask = description.split(DEADLINE_DATE);
            return new DeadlineCommand(validDescription(deadlineTask[0]), deadlineTask[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please include a task deadline.");
        }
    }

    /**
     * Parse event date from the user input
     *
     * @param description command argument for the event command
     * @return event command object parsed out from the command input
     * @throws DukeException when the date of the event object is not
     * specified by user in the command input
     */
    private static EventCommand parseEvent(String description) throws DukeException {
        try {
            String[] eventTask = description.split(EVENT_DATE);
            return new EventCommand(validDescription(eventTask[0]), eventTask[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please include an event date.");
        }
    }

    /**
     * Parses out valid argument from user input
     *
     * @param description user input to be parsed
     * @return valid argument parsed out from user input
     * @throws DukeException when user input is blank
     */
    private static String validDescription(String description) throws DukeException{
        if (!description.isBlank()) {
            return description;
        } else {
            throw new DukeException("try adding sth? idk just a thot xoxo");
        }
    }
}
