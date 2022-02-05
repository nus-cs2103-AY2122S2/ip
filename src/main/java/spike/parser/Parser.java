package spike.parser;

import static spike.task.Task.DATE_TIME_PATTERN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import spike.command.AddCommand;
import spike.command.Command;
import spike.command.DeleteCommand;
import spike.command.ExitCommand;
import spike.command.FindCommand;
import spike.command.IncorrectCommand;
import spike.command.ListCommand;
import spike.command.ToggleMarkCommand;
import spike.task.Deadline;
import spike.task.Event;
import spike.task.Task;
import spike.task.TaskList;
import spike.task.ToDo;


/**
 * Encapsulates functionalities to parse user command.
 */
public class Parser {
    public static final String MSG_ERROR_LIST_BY_DATE =
            "Kindly enter the date in the format yyyy-MM-dd 0000 to filter by date";
    public static final String MSG_MISSING_KEYWORD = "Kindly enter the keyword for finding task";
    public static final String MSG_MISSING_TODO_INFO = "Hmmmm what to do? Think again?";
    public static final String MSG_MISSING_DEADLINE_INFO = "Deadline or task description missing.";
    public static final String MSG_WRONG_DATE_TIME_FORMAT = "Please enter a valid date in the format yyyy-MM-dd HHmm";
    public static final String MSG_MISSING_EVENT_INFO = "Event time or event description missing.";
    public static final String UNEXPECTED_ERROR = "Should not reach this line";
    public static final String MSG_ERROR_MARKING = "Invalid arguments for (un)marking. Please check again!";
    public static final String MSG_ERROR_DELETING = "Invalid arguments for deletion. Please check again!";
    public static final String DEADLINE_BY = "/by";
    public static final String EVENT_AT = "/at";

    protected enum CommandName {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find"),
        BYE("bye");

        private String command;

        CommandName (String command) {
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }
    }

    /**
     * Parses user input and returns a command.
     *
     * @param inputLine raw user input
     * @param tasks current task list
     * @return a proper command to be executed
     */
    public Command parseCommand(String inputLine, TaskList tasks) {
        String[] commandWords = inputLine.split(" ");
        CommandName type = validateCommand(commandWords[0]);
        if (type == null) {
            return new IncorrectCommand();
        }
        switch (type) {
        case LIST:
            return parseList(inputLine);
        case MARK:
            // Fallthrough
        case UNMARK:
            return parseToggleMark(type, commandWords, tasks);
        case DELETE:
            return parseDelete(commandWords, tasks);
        case TODO:
            return parseAddToDo(type, inputLine);
        case DEADLINE:
            return parseAddDeadline(type, inputLine, commandWords);
        case EVENT:
            return parseAddEvent(type, inputLine, commandWords);
        case FIND:
            return parseFind(inputLine);
        case BYE:
            return parseExit();
        default:
            return new IncorrectCommand(UNEXPECTED_ERROR);
        }
    }

    /**
     * Parses the list command.
     *
     * @param inputLine user raw input
     * @return a command object ready to be executed
     */
    private Command parseList(String inputLine) {
        if (inputLine.length() >= 5) {
            // User tries to list task by date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
            LocalDateTime ldt = parseDateTime(inputLine.substring(5), dtf);
            if (ldt == null) {
                return new IncorrectCommand(MSG_ERROR_LIST_BY_DATE);
            }
            return new ListCommand(0, ldt);
        }
        return new ListCommand();
    }

    /**
     * Parses the to-do command.
     *
     * @param c command name
     * @param command whole command in string
     * @return a command ready to be executed
     */
    private Command parseAddToDo(CommandName c, String command) {
        if (command.length() <= 5) {
            return new IncorrectCommand(MSG_MISSING_TODO_INFO);
        }
        ToDo newTD = new ToDo(command.substring(command.indexOf(c.getCommand()) + 5));
        return new AddCommand(newTD);
    }

    /**
     * Parses the deadline command.
     *
     * @param c command name
     * @param command whole command in string
     * @param commandWords command broken down into words
     * @return a command ready to be executed
     */
    private Command parseAddDeadline(CommandName c, String command, String[] commandWords) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        boolean isIncompleteCommand = commandWords.length <= 2;
        boolean isMissingEscapeChar = command.indexOf(DEADLINE_BY) == -1;
        if (isIncompleteCommand || isMissingEscapeChar) {
            return new IncorrectCommand(MSG_MISSING_DEADLINE_INFO);
        }

        boolean isMissingTaskDescription = commandWords[1].equals(DEADLINE_BY);
        boolean isMissingDateTime = command.indexOf(DEADLINE_BY) + 3 == command.length();
        if (isMissingTaskDescription || isMissingDateTime) {
            return new IncorrectCommand(MSG_MISSING_DEADLINE_INFO);
        }
        String deadline = command.substring(command.indexOf(DEADLINE_BY) + 4);
        LocalDateTime deadlineT = parseDateTime(deadline, dtf);
        if (deadlineT == null) {
            return new IncorrectCommand(MSG_WRONG_DATE_TIME_FORMAT);
        }
        String taskDescription = command.substring(command.indexOf(c.getCommand()) + 9,
                command.indexOf(DEADLINE_BY) - 1);
        Deadline newD = new Deadline(taskDescription, deadlineT);
        return new AddCommand(newD);
    }


    /**
     * Parses the event command.
     *
     * @param c command name
     * @param command whole command in string
     * @param commandWords command broken down into words
     * @return a command ready to be executed
     */
    private Command parseAddEvent(CommandName c, String command, String[] commandWords) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        boolean isIncompleteCommand = commandWords.length <= 2;
        boolean isMissingEscapeChar = command.indexOf(EVENT_AT) == -1;
        if (isIncompleteCommand || isMissingEscapeChar) {
            return new IncorrectCommand(MSG_MISSING_EVENT_INFO);
        }

        boolean isMissingTaskDescription = commandWords[1].equals(EVENT_AT);
        boolean isMissingDateTime = command.indexOf(EVENT_AT) + 3 == command.length();
        if (isMissingTaskDescription || isMissingDateTime) {
            return new IncorrectCommand(MSG_MISSING_EVENT_INFO);
        }
        String eventAt = command.substring(command.indexOf(EVENT_AT) + 4);
        LocalDateTime eventT = parseDateTime(eventAt, dtf);
        if (eventT == null) {
            return new IncorrectCommand(MSG_WRONG_DATE_TIME_FORMAT);
        }
        String eventDescription = command.substring(command.indexOf(c.getCommand()) + 6,
                command.indexOf(EVENT_AT) - 1);
        Event newE = new Event(eventDescription, eventT);
        return new AddCommand(newE);
    }

    /**
     * Parses mark or unmark command
     *
     * @param c command name
     * @param commandWords command broken down into words
     * @param tasks current task list
     * @return a command object ready to be executed
     */
    private Command parseToggleMark(CommandName c, String[] commandWords, TaskList tasks) {
        if (!validateIndexCommand(commandWords, tasks)) {
            return new IncorrectCommand(MSG_ERROR_MARKING);
        }
        if (c.getCommand().equals("mark")) {
            Task toMark = tasks.getTasks().get(Integer.parseInt(commandWords[1]) - 1);
            return new ToggleMarkCommand(1, toMark);
        } else {
            Task toUnmark = tasks.getTasks().get(Integer.parseInt(commandWords[1]) - 1);
            return new ToggleMarkCommand(0, toUnmark);
        }
    }

    /**
     * Parses delete command.
     *
     * @param commandWords command broken down into words
     * @param tasks current task list
     * @return a command object ready to be executed
     */
    private Command parseDelete(String[] commandWords, TaskList tasks) {
        if (!validateIndexCommand(commandWords, tasks)) {
            return new IncorrectCommand(MSG_ERROR_DELETING);
        }
        Task toDelete = tasks.getTasks().get(Integer.parseInt(commandWords[1]) - 1);
        return new DeleteCommand(toDelete);
    }

    /**
     * Validates command consist of only command and index.
     * Such as delete and mark/mark
     *
     * @return true if command is valid, else false
     */
    private boolean validateIndexCommand(String[] commandWords, TaskList tasks) {
        boolean isIncorrectParameter = commandWords.length != 2;
        if (isIncorrectParameter) {
            return false;
        }
        boolean isInvalidIndex = isValidIndex(commandWords[1], tasks.getListSize()) == -1;
        if (isInvalidIndex) {
            return false;
        }
        return true;
    }

    /**
     * Parses the find command.
     *
     * @param inputLine user raw input
     * @return a command object ready to be executed
     */
    private Command parseFind(String inputLine) {
        if (inputLine.length() <= 5) {
            return new IncorrectCommand(MSG_MISSING_KEYWORD);
        }
        return new FindCommand(inputLine.substring(5));
    }

    /**
     * Parses exit command
     *
     * @return a command ready to be executed
     */
    private Command parseExit() {
        return new ExitCommand();
    }


    /**
     * Parses date and time input by user and returns valid LocalDateTime object
     *
     * @return object containing date and time information
     */
    private static LocalDateTime parseDateTime(String s, DateTimeFormatter dtf) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(s, dtf);
            return dateTime;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks whether the input string is valid index.
     * If yes, return it, else return -1.
     *
     * @return indicator of whether the index given is valid
     */
    private static int isValidIndex(String str, int listSize) {
        try {
            int x = Integer.parseInt(str);
            if (x - 1 >= listSize || x <= 0) {
                return -1;
            }
            // Expect user to give 1-based index, while the list uses 0-based index
            return x - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Checks whether it is a valid command.
     * If valid, return that command enum number, else return null.
     *
     * @return command name if the input is valid
     */
    private CommandName validateCommand(String input) {
        for (CommandName c : CommandName.values()) {
            if (c.getCommand().equals(input)) {
                return c;
            }
        }
        return null;
    }
}
