package saitama.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import saitama.commands.AddCommand;
import saitama.commands.Command;
import saitama.commands.DeleteCommand;
import saitama.commands.ExitCommand;
import saitama.commands.FindCommand;
import saitama.commands.ListCommand;
import saitama.commands.MarkCommand;
import saitama.commands.UnmarkCommand;
import saitama.exceptions.EmptyDescriptionException;
import saitama.exceptions.FileCorruptException;
import saitama.exceptions.InvalidCommandException;
import saitama.exceptions.InvalidDateTimeException;
import saitama.exceptions.InvalidFormatException;
import saitama.exceptions.InvalidTaskNumberException;
import saitama.exceptions.MissingQueryException;
import saitama.tags.RecurFrequency;
import saitama.tags.Tag;
import saitama.tasks.Deadline;
import saitama.tasks.Event;
import saitama.tasks.Task;
import saitama.tasks.ToDo;

/**
 * Interprets the user input.
 */
public class Parser {

    public static final String EMPTY_EVENT_DESCRIPTION_MESSAGE = "The description of the event task cannot be empty!";
    public static final String EMPTY_DEADLINE_DESCRIPTION_MESSAGE = "The description of the deadline "
            + "task cannot be empty!";
    public static final String INVALID_EVENT_FORMAT_MESSAGE = "You need to specify event name and event "
            + "location!\nFormat: event <task name> /at <location>";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "You need to specify the task name and deadline!\n"
            + "Format: deadline <task name> /by <dd/mm/yyyy hh:mm>";
    public static final String MISSING_LOCATION_MESSAGE = "Your event location cannot be empty!";
    public static final String INVALID_DATE_FORMAT_MESSAGE = "Please enter a valid deadline format! "
            + "<dd/mm/yyyy hh:mm>";

    /**
     * Returns a Command object corresponding to the input given by the user.
     *
     * @param fullCommand The command input given by the user
     * @return Command object corresponding to the user input
     * @throws InvalidFormatException if command exists but is in invalid format
     * @throws EmptyDescriptionException if command exists but no details are given
     * @throws InvalidCommandException if command does not exist
     * @throws InvalidTaskNumberException if given command takes in a task number but the number does not exist
     */
    public static Command parse(String fullCommand) throws InvalidFormatException, EmptyDescriptionException,
            InvalidCommandException, InvalidTaskNumberException, MissingQueryException, InvalidDateTimeException {

        //split the command into [command_word, command_arguments]
        String[] splitCommand = fullCommand.trim().split(" ", 2);
        splitCommand[0] = splitCommand[0].toUpperCase(); //convert the command word to uppercase
        String command = splitCommand[0];

        ArrayList<Tag> tags = new ArrayList<>();
        if (splitCommand.length >= 2) { //if command has arguments
            String commandArguments = splitCommand[1];
            tags = getTags(commandArguments); //check for tags
            splitCommand[1] = removeTagsInString(commandArguments); //modify the string to remove tags
        }

        switch (command) {
        case "BYE":
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return prepareMark(splitCommand);
        case "UNMARK":
            return prepareUnmark(splitCommand);
        case "DELETE":
            return prepareDelete(splitCommand);
        case "FIND":
            return prepareFind(splitCommand);
        case "TODO":
            //Fallthrough
        case "DEADLINE":
            //Fallthrough
        case "EVENT":
            return prepareAdd(splitCommand, tags);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Creates a MarkCommand object that marks the corresponding task number.
     *
     * @param splitCommand The split command array
     * @return A MarkCommand object
     * @throws InvalidTaskNumberException if the provided task number is not an integer or does not exist
     */
    private static MarkCommand prepareMark(String[] splitCommand) throws InvalidTaskNumberException {
        int taskNumber = getTaskNumber(splitCommand);
        return new MarkCommand(taskNumber);
    }

    /**
     * Creates an UnmarkCommand object that unmarks the corresponding task number.
     *
     * @param splitCommand The split command array
     * @return An UnmarkCommand object
     * @throws InvalidTaskNumberException if the provided task number is not an integer or does not exist
     */
    private static UnmarkCommand prepareUnmark(String[] splitCommand) throws InvalidTaskNumberException {
        int taskNumber = getTaskNumber(splitCommand);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Creates a DeleteCommand object that deletes the corresponding task number.
     *
     * @param splitCommand The split command array
     * @return A DeleteCommand object
     * @throws InvalidTaskNumberException if the provided task number is not an integer or does not exist
     */
    private static DeleteCommand prepareDelete(String[] splitCommand) throws InvalidTaskNumberException {
        int taskNumber = getTaskNumber(splitCommand);
        return new DeleteCommand(taskNumber);
    }

    private static int getTaskNumber(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2) {
            throw new InvalidTaskNumberException();
        }
        String taskNum = splitCommand[1].trim();
        if (!isNumeric(taskNum)) {
            throw new InvalidTaskNumberException();
        }
        return Integer.parseInt(taskNum);
    }

    /**
     * Creates a FindCommand object that searches for the corresponding query in the task list.
     *
     * @param splitCommand The split command array
     * @return A FindCommand object
     */
    private static FindCommand prepareFind(String[] splitCommand) throws MissingQueryException {
        if (splitCommand.length < 2 || splitCommand[1].trim().equals("")) {
            throw new MissingQueryException();
        }
        String query = splitCommand[1].toUpperCase();
        return new FindCommand(query);
    }

    /**
     * Creates an AddCommand object that adds the corresponding task to the task list.
     *
     * @param splitCommand The split command array
     * @return An AddCommand object
     * @throws InvalidFormatException if command exists but is in the wrong format
     * @throws EmptyDescriptionException if no details of the task are given
     * @throws InvalidCommandException if command does not exist
     */
    private static AddCommand prepareAdd(String[] splitCommand, ArrayList<Tag> tags) throws InvalidFormatException,
            EmptyDescriptionException, InvalidCommandException, InvalidDateTimeException {
        String taskType = splitCommand[0];
        if (splitCommand.length < 2 || splitCommand[1].trim().equals("")) {
            throw new EmptyDescriptionException(String.format("The description of %s cannot be empty!", taskType));
        }

        String taskArguments = splitCommand[1];
        RecurFrequency recurFrequency = getRecurFrequency(tags);

        assert taskType.equals("TODO") || taskType.equals("DEADLINE") || taskType.equals("EVENT")
                : "Parser detected invalid task type to add!";

        switch (taskType) {
        case "TODO":
            Task newTask = new ToDo(taskArguments, recurFrequency);
            return new AddCommand(newTask);
        case "DEADLINE":
            newTask = createDeadlineTask(taskArguments, recurFrequency);
            return new AddCommand(newTask);
        case "EVENT":
            newTask = createEventTask(taskArguments, recurFrequency);
            return new AddCommand(newTask);
        default:
            //Should never happen since we asserted that the task type is valid
            throw new InvalidCommandException();
        }
    }

    /**
     * Creates an event task given the task arguments and recur frequency of the task.
     *
     * @param taskArguments The event task's arguments
     * @param recurFrequency The RecurFrequency of the task
     * @return A new event task based on the task's arguments and recur frequency
     * @throws InvalidFormatException if the task argument format is not in the form task name /at location
     * @throws EmptyDescriptionException if the description of the task is empty
     */
    private static Task createEventTask(String taskArguments, RecurFrequency recurFrequency)
            throws InvalidFormatException, EmptyDescriptionException {
        String[] taskArgumentsList = taskArguments.split(" /at ", 2);
        if (taskArgumentsList.length < 2) {
            throw new InvalidFormatException(INVALID_EVENT_FORMAT_MESSAGE);
        }

        String taskDescription = taskArgumentsList[0];
        if (taskDescription.trim().equals("")) {
            throw new EmptyDescriptionException(EMPTY_EVENT_DESCRIPTION_MESSAGE);
        }

        String location = taskArgumentsList[1];
        if (location.trim().equals("")) {
            throw new InvalidFormatException(MISSING_LOCATION_MESSAGE);
        }

        Task newTask = new Event(taskDescription, location, recurFrequency);
        return newTask;
    }

    /**
     * Creates a deadline task given the task arguments and recur frequency of the task.
     *
     * @param taskArguments The deadline task's arguments
     * @param recurFrequency The RecurFrequency of the task
     * @return A new deadline task based on the task's arguments and recur frequency
     * @throws InvalidFormatException if the task argument format is not in the form
     * task name /by dd/mm/yyyy hh:mm
     * @throws EmptyDescriptionException if the description of the task is empty
     * @throws InvalidDateTimeException if the specified date or time does not exist
     */
    private static Task createDeadlineTask(String taskArguments, RecurFrequency recurFrequency)
            throws InvalidFormatException, EmptyDescriptionException, InvalidDateTimeException {
        String[] taskArgumentsList = taskArguments.split(" /by ", 2);
        if (taskArgumentsList.length < 2) {
            throw new InvalidFormatException(INVALID_DEADLINE_FORMAT_MESSAGE);
        }

        String taskDescription = taskArgumentsList[0];
        if (taskDescription.trim().equals("")) {
            throw new EmptyDescriptionException(EMPTY_DEADLINE_DESCRIPTION_MESSAGE);
        }

        LocalDateTime deadline = processDateTime(taskArgumentsList[1]);
        Task newTask = new Deadline(taskDescription, deadline, recurFrequency);
        return newTask;
    }

    /**
     * Returns the first RecurFrequency tag given a list of tags.
     *
     * @param tags The list of tags
     * @return The first RecurFrequency tag in the list
     */
    private static RecurFrequency getRecurFrequency(ArrayList<Tag> tags) {
        RecurFrequency recurFrequency = null;
        for (Tag tag : tags) {
            if (tag instanceof RecurFrequency) {
                @SuppressWarnings("unchecked")
                RecurFrequency rf = (RecurFrequency) tag;
                recurFrequency = rf;
                break;
            }
        }
        return recurFrequency;
    }

    /**
     * Returns a list of RecursiveTags given the command arguments.
     *
     * @param commandArguments The command argument containing all the tags
     * @return The list of RecursiveTags found in the command argument
     */
    private static ArrayList<Tag> getTags(String commandArguments) {
        ArrayList<Tag> tags = new ArrayList<>();
        String[] arguments = commandArguments.split(" ");
        for (String argument : arguments) {
            if (argument.startsWith("--")) {
                RecurFrequency r = RecurFrequency.get(argument);
                if (r != null) {
                    tags.add(r);
                }
            }
        }
        return tags;
    }

    /**
     * Returns the String command arguments with all tags removed.
     *
     * @param commandArguments The command argument that needs its tags removed
     * @return The command arguments without tags
     */
    private static String removeTagsInString(String commandArguments) {
        String[] arguments = commandArguments.split(" ");
        String taglessCommandArguments = commandArguments + " ";
        for (String argument : arguments) {
            if (argument.startsWith("--")) {
                taglessCommandArguments = taglessCommandArguments.replace(argument + " ", "");
            }
        }
        return taglessCommandArguments.substring(0, taglessCommandArguments.length() - 1);
    }

    /**
     * Returns whether the string can be converted to integer.
     *
     * @param string The string to be converted to integer
     * @return Whether the string can be converted to integer
     */
    private static boolean isNumeric(String string) {
        if (string == null || string.equals("")) {
            return false;
        }

        try {
            int intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns a LocalDate object based on the by parameter.
     *
     * @param by The deadline in dd/mm/yyyy format
     * @return LocalDate object corresponding to the provided date
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy
     */
    public static LocalDateTime processDateTime(String by) throws InvalidFormatException, InvalidDateTimeException {
        String[] dateTime = by.trim().split(" ", 2);
        if (dateTime.length < 2) {
            throw new InvalidFormatException(INVALID_DATE_FORMAT_MESSAGE);
        }

        String date = dateTime[0];
        String time = dateTime[1];
        String[] splitDate = date.trim().split("/");
        String[] splitTime = time.trim().split(":");

        if (splitDate.length < 3 || splitTime.length < 2) {
            throw new InvalidFormatException(INVALID_DATE_FORMAT_MESSAGE);
        }

        try {
            int year = Integer.parseInt(splitDate[2]);
            int month = Integer.parseInt(splitDate[1]);
            int day = Integer.parseInt(splitDate[0]);
            int hour = Integer.parseInt(splitTime[0]);
            int minute = Integer.parseInt(splitTime[1]);
            LocalDateTime deadline = LocalDateTime.of(year, month, day, hour, minute);
            return deadline;
        } catch (NumberFormatException e) {
            throw new InvalidFormatException(INVALID_DATE_FORMAT_MESSAGE);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Returns the LocalDate given a string in DD-MM-YYYY format.
     *
     * @param date The date to be processed in DD-MM-YYYY format
     * @return The LocalDate of the date
     * @throws FileCorruptException if the date is invalid
     */
    public static LocalDate processDate(String date) throws FileCorruptException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new FileCorruptException();
        }
    }
}
