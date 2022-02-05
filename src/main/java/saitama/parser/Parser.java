package saitama.parser;

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
import saitama.exceptions.InvalidFormatException;
import saitama.exceptions.InvalidTaskNumberException;
import saitama.exceptions.MissingQueryException;
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
            InvalidCommandException, InvalidTaskNumberException, MissingQueryException {
        String[] splitCommand = fullCommand.split(" ", 2); //split the command into [command_word, command_arguments]
        splitCommand[0] = splitCommand[0].toUpperCase(); //convert the command word to uppercase
        String command = splitCommand[0];

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
            //Fallthrough
            return prepareAdd(splitCommand);
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
    private static Command prepareAdd(String[] splitCommand) throws
            InvalidFormatException, EmptyDescriptionException, InvalidCommandException {
        if (splitCommand.length < 2) {
            throw new EmptyDescriptionException();
        }

        String taskType = splitCommand[0];
        String taskArguments = splitCommand[1];

        assert taskType.equals("TODO") || taskType.equals("DEADLINE") || taskType.equals("EVENT")
                : "Parser detected invalid task type to add!";

        switch (taskType) {
        case "TODO":
            Task newTask = new ToDo(taskArguments);
            return new AddCommand(newTask);
        case "DEADLINE":
            String[] taskArgumentsList = taskArguments.split(" /by ", 2);
            if (taskArgumentsList.length < 2) {
                throw new InvalidFormatException("You need to specify "
                        + "the date of the deadline! <Deadline> /by <dd/mm/yyyy>");
            }
            String taskDescription = taskArgumentsList[0];
            String deadline = taskArgumentsList[1];
            newTask = new Deadline(taskDescription, deadline);
            return new AddCommand(newTask);
        case "EVENT":
            taskArgumentsList = taskArguments.split(" /at ", 2);
            if (taskArgumentsList.length < 2) {
                throw new InvalidFormatException("You need to specify event location! <Event> /at <location>");
            }
            taskDescription = taskArgumentsList[0];
            String location = taskArgumentsList[1];
            newTask = new Event(taskDescription, location);
            return new AddCommand(newTask);
        default:
            //Should never happen since we asserted that the task type is valid
            throw new InvalidCommandException();
        }
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
}
