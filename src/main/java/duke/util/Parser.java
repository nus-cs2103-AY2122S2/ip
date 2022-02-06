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

        assert activity != null;

        switch (splitStr[0]) {
        case "T":
            parsedTask = new Todo(activity);
            break;
        case "E":
            timeOrPlace = splitStr[3];

            assert timeOrPlace != null;

            parsedTask = new Event(activity, timeOrPlace);
            break;
        case "D":
            String tmp = splitStr[3];
            try {
                timeOrPlace = savedFormat.format(taskFormat.parse(tmp));
            } catch (ParseException e) {
                System.exit(1);
            }
            parsedTask = new Deadline(activity, timeOrPlace);
            break;
        default:
        }
        assert parsedTask != null;
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
        String[] splitStr = command.split(" ", 2);
        Command parsed;
        Task task;
        int index;
        switch (splitStr[0]) {
        case "bye":
            parsed = EXIT_COMMAND;
            break;
        case "list":
            parsed = LIST;
            break;
        case "mark":
            try {
                index = Integer.parseInt(splitStr[1]);
                index--;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command mark has invalid syntax -e.g., mark [index], "
                        + "where integer is an index");
            }
            parsed = new MarkCommand(index);
            break;
        case "unmark":
            try {
                index = Integer.parseInt(splitStr[1]);
                index--;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command unmark has invalid syntax -e.g., unmark [index], "
                        + "where integer is an index");
            }
            parsed = new UnmarkCommand(index);
            break;
        case "todo":
            try {
                if (splitStr[1].trim().equals("")) {
                    throw new InvalidCommandFormatException("Command todo cannot be blank. -e.g, todo [task], "
                            + "where task is not empty");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command todo cannot be blank. -e.g, todo [task], "
                        + "where task is not empty");
            }
            task = new Todo(splitStr[1]);
            parsed = new AddCommand(task);
            break;
        case "deadline":
            String[] tempStr = splitStr[1].split(" /by ");
            try {
                task = new Deadline(tempStr[0], tempStr[1]);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command deadline has invalid syntax -e.g., "
                        + "deadline [activity] /by [yyyy-mm-dd]");
            }
            parsed = new AddCommand(task);
            break;
        case "event":
            try {
                String[] tempStr2 = splitStr[1].split(" /at ");
                task = new Event(tempStr2[0], tempStr2[1]);
                parsed = new AddCommand(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command deadline has invalid syntax -e.g., "
                        + "event [activity] /at [location]");
            }
            break;
        case "delete":
            try {
                index = Integer.parseInt(splitStr[1]);
                index--;
                parsed = new DeleteCommand(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command delete has invalid syntax -e.g., "
                        + "delete [index], where index is an integer");
            }
            break;
        case "find":
            try {
                String findTask = splitStr[1];
                parsed = new FindCommand(findTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandFormatException("Command find has invalid syntax -e.g., find [task]");
            }
            break;
        default:
            parsed = INVALID_COMMAND;
            break;
        }
        return parsed;
    }

}
