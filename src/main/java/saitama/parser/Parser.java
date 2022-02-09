package saitama.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
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
    /**
     * Returns a Command object corresponding to the input given by the user.
     *
     * @param fullCommand The command input given by the user.
     * @return Command object corresponding to the user input.
     * @throws InvalidFormatException if command exists but is in invalid format.
     * @throws EmptyDescriptionException if command exists but no details are given.
     * @throws InvalidCommandException if command does not exist.
     * @throws InvalidTaskNumberException if given command takes in a task number but the number does not exist.
     */
    public static Command parse(String fullCommand) throws InvalidFormatException, EmptyDescriptionException,
            InvalidCommandException, InvalidTaskNumberException, MissingQueryException, InvalidDateTimeException {

        String[] splitCommand = fullCommand.split(" ", 2); //split the command into [command_word, command_arguments]
        splitCommand[0] = splitCommand[0].toUpperCase(); //convert the command word to uppercase
        String command = splitCommand[0];

        ArrayList<Tag> tags = new ArrayList<>();
        if (splitCommand.length >= 2) { //if command has arguments
            String commandArguments = splitCommand[1];
            tags = getTags(commandArguments); //check for tags
            splitCommand[1] = removeTags(commandArguments);
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
     * @param splitCommand The split command array.
     * @return A MarkCommand object.
     * @throws InvalidTaskNumberException if the provided task number is not an integer or does not exist.
     */
    private static Command prepareMark(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2 || !isNumeric(splitCommand[1])) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = Integer.parseInt(splitCommand[1]);
        return new MarkCommand(taskNumber);
    }

    /**
     * Creates an UnmarkCommand object that unmarks the corresponding task number.
     *
     * @param splitCommand The split command array.
     * @return An UnmarkCommand object.
     * @throws InvalidTaskNumberException if the provided task number is not an integer or does not exist.
     */
    private static Command prepareUnmark(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2 || !isNumeric(splitCommand[1])) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = Integer.parseInt(splitCommand[1]);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Creates a DeleteCommand object that deletes the corresponding task number.
     *
     * @param splitCommand The split command array.
     * @return A DeleteCommand object.
     * @throws InvalidTaskNumberException if the provided task number is not an integer or does not exist.
     */
    private static Command prepareDelete(String[] splitCommand) throws InvalidTaskNumberException {
        if (splitCommand.length < 2 || !isNumeric(splitCommand[1])) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = Integer.parseInt(splitCommand[1]);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Creates a FindCommand object that searches for the corresponding query in the task list.
     *
     * @param splitCommand The split command array.
     * @return A FindCommand object.
     */
    private static Command prepareFind(String[] splitCommand) throws MissingQueryException {
        if (splitCommand.length < 2) {
            throw new MissingQueryException();
        }
        String query = splitCommand[1];
        return new FindCommand(query);
    }

    /**
     * Creates an AddCommand object that adds the corresponding task to the task list.
     *
     * @param splitCommand The split command array.
     * @return An AddCommand object.
     * @throws InvalidFormatException if command exists but is in the wrong format.
     * @throws EmptyDescriptionException if no details of the task are given.
     * @throws InvalidCommandException if command does not exist.
     */
    private static Command prepareAdd(String[] splitCommand, ArrayList<Tag> tags) throws
            InvalidFormatException, EmptyDescriptionException, InvalidCommandException, InvalidDateTimeException {
        if (splitCommand.length < 2 || splitCommand[1].equals("")) {
            throw new EmptyDescriptionException();
        }
        String taskType = splitCommand[0];
        String taskArguments = splitCommand[1];

        RecurFrequency recurFrequency = null;
        for (Tag tag : tags) {
            if (tag instanceof RecurFrequency) {
                @SuppressWarnings("unchecked")
                RecurFrequency rf = (RecurFrequency) tag;
                recurFrequency = rf;
                break;
            }
        }

        assert taskType.equals("TODO") || taskType.equals("DEADLINE") || taskType.equals("EVENT")
                : "Parser detected invalid task type to add!";

        switch (taskType) {
        case "TODO":
            Task newTask = new ToDo(taskArguments, recurFrequency);
            return new AddCommand(newTask);
        case "DEADLINE":
            String[] taskArgumentsList = taskArguments.split(" /by ", 2);
            if (taskArgumentsList.length < 2) {
                throw new InvalidFormatException("You need to specify "
                        + "the deadline!\n deadline <task name> /by <dd/mm/yyyy hh:mm>");
            }
            String taskDescription = taskArgumentsList[0];
            LocalDateTime deadline = processDateTime(taskArgumentsList[1]);
            newTask = new Deadline(taskDescription, deadline, recurFrequency);
            return new AddCommand(newTask);
        case "EVENT":
            taskArgumentsList = taskArguments.split(" /at ", 2);
            if (taskArgumentsList.length < 2) {
                throw new InvalidFormatException("You need to specify event location!\n"
                        + "event <task name> /at <location>");
            }
            taskDescription = taskArgumentsList[0];
            String location = taskArgumentsList[1];
            newTask = new Event(taskDescription, location, recurFrequency);
            return new AddCommand(newTask);
        default:
            //Should never happen since we asserted that the task type is valid
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns a list of RecursiveTags given the command arguments.
     *
     * @param commandArguments The command argument containing all the tags.
     * @return The list of RecursiveTags found in the command argument.
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
     * @param commandArguments The command argument that needs its tags removed.
     * @return The command arguments without tags.
     */
    private static String removeTags(String commandArguments) {
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
     * A helper function to test if a String can be converted to integer.
     *
     * @param string The string to be converted to integer.
     * @return Whether the string can be converted to integer.
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
     * @param by The deadline in dd/mm/yyyy format.
     * @return LocalDate object corresponding to the provided date.
     * @throws InvalidFormatException if the format of by is not dd/mm/yyyy.
     */
    public static LocalDateTime processDateTime(String by) throws InvalidFormatException, InvalidDateTimeException {
        String[] dateTime = by.split(" ", 2);
        if (dateTime.length < 2) {
            throw new InvalidFormatException("Please enter a valid deadline format! <dd/mm/yyyy hh:mm>");
        }

        String date = dateTime[0];
        String time = dateTime[1];
        String[] splitDate = date.split("/");
        String[] splitTime = time.split(":");

        if (splitDate.length < 3 || splitTime.length < 2) {
            throw new InvalidFormatException("Please enter a valid deadline format! <dd/mm/yyyy hh:mm>");
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
            throw new InvalidFormatException("Please enter a valid deadline format! <dd/mm/yyyy hh:mm>");
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
        }
    }
}
