package karen;

import karen.command.*;
import karen.task.Deadline;
import karen.task.Event;
import karen.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates and parses arguments to create Command objects.
 */
public class Parser {
    public static final Pattern INDEX_FORMAT = Pattern.compile("^(?<keyWord>[^\\s]+)\\s+(?<index>\\d+)$");
    public static final Pattern KEYWORD_FORMAT = Pattern.compile("^(?<keyWord>[^\\s]+).*$");
    public static final Pattern TODO_FORMAT = Pattern.compile("^todo\\s+(?<description>.*)$");
    public static final Pattern DEADLINE_FORMAT =
      Pattern.compile("^deadline (?<description>.*) \\/by (?<time>.*)$");
    public static final Pattern EVENT_FORMAT =
      Pattern.compile("^event (?<description>.*) \\/at (?<time>.*)$");

    public static final String NA_MESSAGE = "I don't understand anything - I want to speak with your manager";

    /**
     * Validates if dateString parameter follows yyyy-mm-dd format.
     * @param dateString input string
     * @return dateString original input string
     * @throws KarenException if format doesn't follow yyyy-mm-dd
     */
    public String validateDateFormat(String dateString) throws KarenException {
        try {
            LocalDate.parse(dateString);
        } catch (DateTimeException err) {
            throw new KarenException("Wrong date formatting. It should be in yyyy-mm-dd");
        }
        return dateString;
    }

    /**
     * Creates InvalidCommand objects with troubleshooting messages
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return InvalidCommand with default or custom message.
     */
    private Command prepareInvalid(String keyWord, String fullInput) {
        Command cmd = new InvalidCommand();

        switch (keyWord) {
        case "deadline":
            if (fullInput.matches("^((?!\\/by).)*$")) {
                cmd = new InvalidCommand("You're missing an /by flag needed to add deadlines");
            }
            break;
        case "event":
            if (fullInput.matches("^((?!\\/at).)*$")) {
                cmd = new InvalidCommand("You're missing an /at flag needed to add events");
            }
        }
        return cmd;
    }

    /**
     * Creates AddCommand object by parsing parameters to instantiate Task objects and
     * other relevant arguments.
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return AddCommand object
     */
    private Command prepareAdd(String keyWord, String fullInput) {
        final Matcher matcher;
        try {
            if (keyWord.equals("todo")) {
                matcher = TODO_FORMAT.matcher(fullInput);
                matcher.find();
                return new AddCommand(new ToDo(matcher.group("description")));
            } else if (keyWord.equals("deadline")) {
                matcher = DEADLINE_FORMAT.matcher(fullInput);
                matcher.find();
                return new AddCommand(new Deadline(matcher.group("description"),
                        validateDateFormat(matcher.group("time"))));
            } else if (keyWord.equals("event")) {
                matcher = EVENT_FORMAT.matcher(fullInput);
                matcher.find();
                return new AddCommand(new Event(matcher.group("description"),
                        validateDateFormat(matcher.group("time"))));
            }
            return new InvalidCommand("How did you invoke this.");
        } catch (IllegalStateException err) {
            // indicates that the format isn't valid - can't parse it
            return prepareInvalid(keyWord, fullInput);
        } catch (KarenException err) {
            return new InvalidCommand(err.message);
        }
    }

    /**
     * Creates ModifyCommand object by parsing parameters to get (1-based index) of Tasks
     * to modify and the relevant ModifyType
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return ModifyCommand object
     */
    private Command prepareModify(String keyWord, String fullInput) {
        final Matcher matcher = INDEX_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand("Incorrect arguments passed into mark/unmark command");
        }

        Command cmd;
        switch (keyWord) {
        case "mark":
            cmd = new ModifyCommand(Integer.valueOf(matcher.group("index"))-1, ModifyType.MARK);
            break;
        case "unmark":
            cmd = new ModifyCommand(Integer.valueOf(matcher.group("index"))-1, ModifyType.UNMARK);
            break;
        default:
            return new InvalidCommand("How did this even get here.");
        }
        return cmd;
    }

    /**
     * Creates DeleteCommand object by parsing parameters to get (1-based index) of Tasks
     * to delete.
     * @param keyWord first word of input
     * @param fullInput full input from user
     * @return DeleteCommand object
     */
    private Command prepareDelete(String keyWord, String fullInput) {
        final Matcher matcher = INDEX_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand("Incorrect arguments passed into delete command");
        }

        try {
            return new DeleteCommand(Integer.valueOf(matcher.group("index"))-1);
        } catch (IllegalStateException err) {
            return new InvalidCommand("Missing arguments for delete command");
        }

    }

    /**
     * Creates Command object from the full user input
     * @param fullInput full input from user
     * @return Command object
     */
    private Command createCommand(String fullInput) {
        // extract first word
        final Matcher matcher = KEYWORD_FORMAT.matcher(fullInput);
        if (!matcher.matches()) {
            return new InvalidCommand(NA_MESSAGE);
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
        case "delete":
            getCommand = prepareDelete(keyWord, fullInput);
            break;
        default:
            getCommand = new InvalidCommand(NA_MESSAGE);
            break;
        };
        return getCommand;
    }

    /**
     * Wrapper function for Parser object to parse input strings from user
     * to create Command objects
     * @param fullInput full input from user
     * @return Command object
     */
    public Command parseInput(String fullInput){
        Command cmd;
        cmd = this.createCommand(fullInput);
        return cmd;
    }

}
