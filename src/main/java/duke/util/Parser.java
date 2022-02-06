package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandFormatException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class parses the user input into commands and also parses the saved task list on disk into tasks.
 */
public class Parser {

    // Global constant commands.
    private static final SimpleDateFormat taskFormat = new SimpleDateFormat("MMM dd yyyy");
    private static final SimpleDateFormat savedFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final Command EXIT_COMMAND = new ExitCommand();
    private static final Command LIST = new ListCommand();
    private static final Command INVALID_COMMAND = new InvalidCommand("Invalid Command. Valid commands are:"
            + "\n" + "todo\n" + "deadline\n" + "event\n" + "mark\n" + "unmark\n" + "list\n" + "delete\n" + "find\n"
            + "bye");

    /**
     * Constructor for a parser.
     */
    public Parser() {}

    /**
     * Parses a saved task on disk into a task.
     * @param savedTask the saved task.
     * @return the parsed task.
     */
    public static Task parseFile(String savedTask) {

        String[] splitStr = savedTask.split("\\|");

        boolean status = Boolean.parseBoolean(splitStr[1]);

        String activity = splitStr[2];
        String timeOrPlace = null;
        Task parsedTask = null;

        switch (splitStr[0]) {
        case "T":
            parsedTask = new Todo(activity);
            break;
        case "E":
            timeOrPlace = splitStr[3];
            parsedTask = new Event(activity, timeOrPlace);
            break;
        case "D":
            String tmp = splitStr[3];
            try {
                timeOrPlace = savedFormat.format(taskFormat.parse(tmp));
            } catch (ParseException e) { // Corrupted Save File.
                e.printStackTrace();
            }
            parsedTask = new Deadline(activity, timeOrPlace);
            break;
        default:
        }

        parsedTask.setMarked(status);
        return parsedTask;
    }

    /**
     * Parses user input into a command.
     * @param command the user input.
     * @return a command that can be executed.
     * @throws DukeException if the user input is invalid.
     */
    public Command parseCommand(String command) throws DukeException {
        String[] userInput = command.split(" ", 2);
        Command parsed;
        switch (userInput[0]) {
        case "bye":
            parsed = EXIT_COMMAND;
            break;
        case "list":
            parsed = LIST;
            break;
        case "mark":
            parsed = parseMark(userInput);
            break;
        case "unmark":
            parsed = parseUnmark(userInput);
            break;
        case "todo":
            parsed = parseTodo(userInput);
            break;
        case "deadline":
            parsed = parseDeadline(userInput);
            break;
        case "event":
            parsed = parseEvent(userInput);
            break;
        case "delete":
            parsed = parseDelete(userInput);
            break;
        case "find":
            parsed = parseFind(userInput);
            break;
        default:
            parsed = INVALID_COMMAND;
            break;
        }
        return parsed;
    }

    /**
     * Parses the given user input into a Mark Command.
     * @param userInput the user input to be parsed.
     * @return a Mark Command.
     * @throws InvalidCommandFormatException if Mark Command format by userInput is invalid.
     */
    private Command parseMark(String[] userInput) throws InvalidCommandFormatException {
        int index;
        try {
            index = Integer.parseInt(userInput[1]);
            index--;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command mark has invalid syntax -e.g., mark [index], "
                    + "where integer is an index");
        }
        return new MarkCommand(index);
    }

    /**
     * Parses the given user input into a Unmark Command.
     * @param userInput the user input to be parsed.
     * @return a Unmark Command.
     * @throws InvalidCommandFormatException if the Unmark Command format by the userInput is invalid.
     */
    private Command parseUnmark(String[] userInput) throws InvalidCommandFormatException {
        int index;
        try {
            index = Integer.parseInt(userInput[1]);
            index--;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command unmark has invalid syntax -e.g., unmark [index], "
                    + "where integer is an index");
        }
        return new UnmarkCommand(index);
    }

    /**
     * Parses the given user input into an Add Command for a To-Do task.
     * @param userInput the user input to be parsed.
     * @return a Add Command.
     * @throws InvalidCommandFormatException if the user did not input an activity To-Do.
     */
    private Command parseTodo(String[] userInput) throws InvalidCommandFormatException {
        try {
            if (userInput[1].trim().equals("")) {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command todo cannot be blank. -e.g, todo [task], "
                    + "where task is not empty");
        }
        Task parsedTask = new Todo(userInput[1]);
        return new AddCommand(parsedTask);
    }

    /**
     * Parses the given user input into an Add Command for a Deadline task.
     * @param userInput the user input to be parsed.
     * @return an Add Command.
     * @throws InvalidCommandFormatException if the Deadline Command format by the userInput is invalid.
     * @throws InvalidDateException if the date given is of the wrong format.
     */
    private Command parseDeadline(String[] userInput) throws InvalidCommandFormatException, InvalidDateException {
        Task parsedTask;
        try {
            String[] splitBy = userInput[1].split(" /by ");
            String activity = splitBy[0];
            String by = splitBy[1];
            parsedTask = new Deadline(activity, by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command deadline has invalid syntax -e.g., "
                    + "deadline [activity] /by [yyyy-mm-dd]");
        }
        return new AddCommand(parsedTask);
    }

    /**
     * Parses the given user input into an Add Command for an Event task.
     * @param userInput the user input to be parsed.
     * @return an Add Command.
     * @throws InvalidCommandFormatException if the Event Command format by the userInput is invalid.
     */
    private Command parseEvent(String[] userInput) throws InvalidCommandFormatException {
        Task parsedTask;
        try {
            String[] splitAt = userInput[1].split(" /at ");
            String activity = splitAt[0];
            String at = splitAt[1];
            parsedTask = new Event(activity, at);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command event has invalid syntax -e.g., "
                    + "event [activity] /at [location]");
        }
        return new AddCommand(parsedTask);
    }

    /**
     * Parses the given user input into a Delete Command.
     * @param userInput the user input to be parsed.
     * @return a Delete Command.
     * @throws InvalidCommandFormatException if the Delete Command format by the userInput is invalid.
     */
    private Command parseDelete(String[] userInput) throws InvalidCommandFormatException {
        int index;
        try {
            index = Integer.parseInt(userInput[1]);
            index--;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command delete has invalid syntax -e.g., "
                    + "delete [index], where index is an integer");
        }
        return new DeleteCommand(index);
    }

    /**
     * Parses the given user input into a Find Command.
     * @param userInput the user input to be parsed.
     * @return a Find Command.
     * @throws InvalidCommandFormatException if the Find Command format by the userInput is invalid.
     */
    private Command parseFind(String[] userInput) throws InvalidCommandFormatException {
        String findTask;
        try {
            findTask = userInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("Command find has invalid syntax -e.g., find [task]");
        }
        return new FindCommand(findTask);
    }

}
