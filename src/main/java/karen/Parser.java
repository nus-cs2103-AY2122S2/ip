package karen;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import karen.command.AddCommand;
import karen.command.ByeCommand;
import karen.command.Command;
import karen.command.DeleteCommand;
import karen.command.EditCommand;
import karen.command.FindCommand;
import karen.command.InvalidCommand;
import karen.command.InvalidMessage;
import karen.command.ListCommand;
import karen.command.ModifyCommand;
import karen.command.ModifyType;
import karen.task.Deadline;
import karen.task.Event;
import karen.task.ToDo;

/**
 * Validates and parses arguments to create Command objects.
 *
 * Code organisation partially adapted from:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */
public class Parser {
    public static final Pattern INDEX_FORMAT = Pattern.compile("^(?<keyWord>[^\\s]+)\\s+(?<index>\\d+)$");
    public static final Pattern KEYWORD_FORMAT = Pattern.compile("^(?<keyWord>[^\\s]+).*$");
    public static final Pattern TODO_FORMAT = Pattern.compile("^todo\\s+(?<description>.*)$");
    public static final Pattern DEADLINE_FORMAT = Pattern.compile("^deadline (?<description>.*) \\/by (?<time>.*)$");
    public static final Pattern EVENT_FORMAT = Pattern.compile("^event (?<description>.*) \\/at (?<time>.*)$");
    public static final Pattern FIND_FORMAT = Pattern.compile("^find (?<keyTerm>.*)$");
    public static final Pattern EDIT_FORMAT = Pattern.compile(
            "^edit\\s+(?<index>\\d+)\\s+\\/description\\s+(?<editValue>.*)$");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");

    /**
     * Validates and returns LocalDateTime object if dateString parameter follows yyyy-mm-dd format.
     *
     * @param dateString input string
     * @return LocalDateTime object representation of dateString
     * @throws KarenException if format doesn't follow yyyy-mm-dd
     */
    public LocalDateTime validateDateFormat(String dateString) throws KarenException {
        try {
            return parseDate(dateString);
        } catch (DateTimeException err) {
            throw new KarenException(InvalidMessage.INVALID_DATE);
        }
    }

    /**
     * Static method to parse string in format `yyyy-mm-dd HHmm` into
     * LocalDateTime object
     *
     * @param dateString input date string
     * @return parsed LocalDateTime
     */
    public static LocalDateTime parseDate(String dateString) {
        return LocalDateTime.parse(dateString, DATETIME_FORMATTER);
    }

    /**
     * Creates InvalidCommand objects with troubleshooting messages
     *
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return InvalidCommand with default or custom message.
     */
    private Command prepareInvalid(String keyWord, String fullInput) {
        Command cmd = null;

        switch (keyWord) {
        case "deadline":
            if (fullInput.matches("^((?!\\/by).)*$")) {
                cmd = new InvalidCommand(InvalidMessage.MISSING_BY);
            }
            break;
        case "event":
            if (fullInput.matches("^((?!\\/at).)*$")) {
                cmd = new InvalidCommand(InvalidMessage.MISSING_AT);
            }
            break;
        default:
            cmd = new InvalidCommand();
        }

        // default case should be a catch-all for initialising InvalidCommand with a default message
        assert cmd != null;

        return cmd;
    }

    /**
     * Creates AddCommand object by parsing parameters to instantiate Task objects and
     * other relevant arguments.
     *
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return AddCommand object
     */
    private Command prepareAdd(String keyWord, String fullInput) {
        final Matcher matcher;
        String trimInput = fullInput.trim();

        try {
            Command cmd;

            switch (keyWord) {
            case "todo":
                matcher = TODO_FORMAT.matcher(trimInput);
                matcher.find();
                cmd = new AddCommand(new ToDo(matcher.group("description")));
                break;
            case "deadline":
                matcher = DEADLINE_FORMAT.matcher(trimInput);
                matcher.find();
                cmd = new AddCommand(new Deadline(matcher.group("description"),
                        validateDateFormat(matcher.group("time"))));
                break;
            case "event":
                matcher = EVENT_FORMAT.matcher(trimInput);
                matcher.find();
                cmd = new AddCommand(new Event(matcher.group("description"),
                        validateDateFormat(matcher.group("time"))));
                break;
            default:
                cmd = new InvalidCommand();
            }
            return cmd;
        } catch (IllegalStateException err) {
            // indicates that the format isn't valid - can't parse it
            return prepareInvalid(keyWord, trimInput);
        } catch (KarenException err) {
            return new InvalidCommand(err.getInvalidEnum());
        }
    }

    /**
     * Creates ModifyCommand object by parsing parameters to get (1-based index) of Tasks
     * to modify and the relevant ModifyType
     *
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return ModifyCommand object
     */
    private Command prepareModify(String keyWord, String fullInput) {
        final Matcher matcher = INDEX_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand(InvalidMessage.INCORRECT_MODIFY);
        }

        Command cmd;
        switch (keyWord) {
        case "mark":
            cmd = new ModifyCommand(Integer.valueOf(matcher.group("index")) - 1, ModifyType.MARK);
            break;
        case "unmark":
            cmd = new ModifyCommand(Integer.valueOf(matcher.group("index")) - 1, ModifyType.UNMARK);
            break;
        default:
            cmd = new InvalidCommand();
        }

        // default case should be a catch-all for initialising InvalidCommand with a default message
        assert cmd != null;

        return cmd;
    }

    /**
     *
     * @param keyWord
     * @param fullInput
     * @return
     */
    private Command prepareEdit(String keyWord, String fullInput) {
        final Matcher matcher = EDIT_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand(InvalidMessage.MISSING_EDIT);
        }

        try {
            return new EditCommand(Integer.valueOf(matcher.group("index")) - 1,
                    matcher.group("editValue"));
        } catch (IllegalStateException err) {
            return new InvalidCommand(InvalidMessage.INCORRECT_EDIT);
        }

    }

    /**
     * Creates DeleteCommand object by parsing parameters to get (1-based index) of Tasks
     * to delete.
     *
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return DeleteCommand object
     */
    private Command prepareDelete(String keyWord, String fullInput) {
        final Matcher matcher = INDEX_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand(InvalidMessage.MISSING_DELETE);
        }

        try {
            return new DeleteCommand(Integer.valueOf(matcher.group("index")) - 1);
        } catch (IllegalStateException err) {
            return new InvalidCommand(InvalidMessage.INCORRECT_DELETE);
        }
    }

    /**
     * Creates FindCommand object by parsing parameters to get keyTerm to search with
     *
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return FindCommand object
     */
    private Command prepareFind(String keyWord, String fullInput) {
        final Matcher matcher = FIND_FORMAT.matcher(fullInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(InvalidMessage.INCORRECT_FIND);
        }

        return new FindCommand(matcher.group("keyTerm"));
    }

    /**
     * Creates Command object from the full user input
     *
     * @param fullInput full input from user
     * @return Command object
     */
    private Command createCommand(String fullInput) {
        // extract first word
        final Matcher matcher = KEYWORD_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand();
        }

        String keyWord = matcher.group("keyWord");
        Command getCommand;

        switch (keyWord) {
        case "list":
            getCommand = new ListCommand();
            break;
        case "bye":
            getCommand = new ByeCommand();
            break;
        case "todo":
        case "deadline":
        case "event":
            getCommand = prepareAdd(keyWord, fullInput);
            break;
        case "mark":
        case "unmark":
            getCommand = prepareModify(keyWord, fullInput);
            break;
        case "edit":
            getCommand = prepareEdit(keyWord, fullInput);
            break;
        case "delete":
            getCommand = prepareDelete(keyWord, fullInput);
            break;
        case "find":
            getCommand = prepareFind(keyWord, fullInput);
            break;
        default:
            getCommand = new InvalidCommand();
            break;
        };
        return getCommand;
    }

    /**
     * Wrapper function for Parser object to parse input strings from user
     * to create Command objects
     *
     * @param fullInput full input from user
     * @return Command object
     */
    public Command parseInput(String fullInput) {
        Command cmd;
        cmd = createCommand(fullInput);
        return cmd;
    }
}
