package duke.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.TaskList;
import duke.exception.IncompleteInputException;
import duke.exception.WrongInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Helps to make sense of user input.
 */
public class Parser {
    private static final String WRONG_INPUT_MESSAGE = "D: D: D: I don't understand the input D: D: D:";
    private static final String INCOMPLETE_INPUT_MESSAGE = "D: D: D: The description of a %1$s is "
            + "incorrect or empty D: D: D:";

    private static final String LIST_EMPTY_MESSAGE = "D: D: D: There are no items in the list D: D: D:";
    private static final String WRONG_INDEX_MESSAGE = "D: D: D: The task number isn't in the list D: D: D:";

    private static final String NUMERICAL_DESCRIPTION_INCORRECT_MESSAGE = "D: D: D: '%1$s'"
            + " should be followed by a single positive integer D: D: D:";

    private static final String REMINDER_INCORRECT_FORMAT_MESSAGE = "Unrecognized format D: D: D:"
            + " Please reformat in 'remind [task number] [reminder date in YYYY-MM-DD]"
            + " [reminder time in HH:MM]'!";
    private static final String INVALID_DATE_TIME_MESSAGE = "D: The specified date/time has already passed!";

    private final String[] acceptableInputs = new String[]{"mark", "unmark", "todo", "deadline",
        "event", "delete", "find", "remind"};

    /**
     * Creates a new Parser instance.
     */
    public Parser() {}

    /**
     * Returns the command part of the user input.
     *
     * @param input The user input.
     * @return The command part of the user input.
     * @throws WrongInputException If the input does not contain a valid command.
     */
    public String parseCommand(String input) throws WrongInputException {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];
        boolean isValidCommand = false;
        for (String acceptableInput : acceptableInputs) {
            if (command.equals(acceptableInput)) {
                isValidCommand = true;
                break;
            }
        }
        if (!isValidCommand) {
            throw new WrongInputException(WRONG_INPUT_MESSAGE);
        }
        return command;
    }

    /**
     * Returns the numerical part of the user input.
     * Assumes the user input contains a command that requires a numerical input.
     *
     * @param input The user input.
     * @param command The command contained in the user input.
     * @param listSize The length of the task list.
     * @return The numerical part of the user input.
     * @throws WrongInputException If the input is not in the expected form.
     */
    public int parseNumericalDescription(String input, String command, int listSize) throws WrongInputException {
        if (listSize == 0) {
            throw new WrongInputException(LIST_EMPTY_MESSAGE);
        }
        assert input.contains(command);
        String[] inputs = input.split(command + " ", 2);

        try {
            int taskNumber = Integer.parseInt(inputs[1].trim());
            boolean isTaskNumberValid = taskNumber <= listSize && taskNumber > 0;
            if (!isTaskNumberValid) {
                throw new WrongInputException(WRONG_INDEX_MESSAGE);
            }
            return taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new WrongInputException(String.format(NUMERICAL_DESCRIPTION_INCORRECT_MESSAGE, command));
        }
    }

    /**
     * Returns an array containing the task description and its associated time.
     *
     * @param input The user input.
     * @param command The command contained in the user input.
     * @param format The regular expression that indicates the timing part of the task.
     * @return An array containing the task description and its associated time.
     * @throws WrongInputException If the regular expression cannot be found in the user input.
     * @throws IncompleteInputException If the input is incomplete.
     */
    public String[] parseFormatDescription(String input, String command, String format)
            throws IncompleteInputException {
        String[] inputs = input.split(" " + format + " ", 2);
        if (inputs.length < 2) {
            throw new IncompleteInputException(String.format(INCOMPLETE_INPUT_MESSAGE, command));
        }

        String[] description = inputs[0].trim().split(command + " ", 2);
        boolean isDescriptionEmpty = description.length < 2 || description[1].trim().equals("");
        if (isDescriptionEmpty) {
            throw new IncompleteInputException(String.format(INCOMPLETE_INPUT_MESSAGE, command));
        }
        String[] finalDescription = new String[2];
        finalDescription[0] = description[1];
        finalDescription[1] = inputs[1].trim();
        return finalDescription;
    }

    /**
     * Returns the task description contained in the user input.
     *
     * @param input The user input.
     * @param command The command contained in the user input.
     * @return The task description.
     * @throws IncompleteInputException If the input is incomplete.
     */
    public String parseStringDescription(String input, String command) throws IncompleteInputException {
        String[] inputs = input.split(command + " ", 2);
        if (inputs.length == 1) {
            throw new IncompleteInputException(String.format(INCOMPLETE_INPUT_MESSAGE, command));
        }
        return inputs[1];
    }

    /**
     * Adds a Reminder to the Task with task number specified in the user input
     * and returns the Task.
     *
     * @param input The user input.
     * @param taskList The task list to retrieve the Task from.
     * @return The Task specified by the task number in the user input.
     * @throws WrongInputException If the input format does not match the required format.
     */
    public Task parseReminderDescription(String input, TaskList taskList) throws WrongInputException {
        String[] inputs = input.split(" ");
        if (inputs.length != 4) {
            throw new WrongInputException(REMINDER_INCORRECT_FORMAT_MESSAGE);
        }
        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new WrongInputException(WRONG_INDEX_MESSAGE);
            }
            Task taskToRemind = taskList.getTask(index);
            LocalDateTime reminderDateTime = LocalDateTime.of(LocalDate.parse(inputs[2]),
                    LocalTime.parse(inputs[3]));
            if (reminderDateTime.isBefore(LocalDateTime.now())) {
                throw new WrongInputException(INVALID_DATE_TIME_MESSAGE);
            }
            taskToRemind.setReminder(reminderDateTime);
            return taskToRemind;
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new WrongInputException(REMINDER_INCORRECT_FORMAT_MESSAGE);
        }
    }

    /**
     * Returns a Task object from parsing a saved line in a storage file.
     *
     * @param entry A line from a storage file containing the description of a Task.
     * @return A Task object formed from parsing a line in a storage file.
     * @throws DateTimeParseException If any time in the file is not in the correct format.
     */
    public static Task parseFileFormat(String entry) throws DateTimeParseException {
        String[] entryParts = entry.split(" \\| ");
        if (entryParts[0].equals("T")) {
            if (entryParts[1].equals("1")) {
                return new Todo(entryParts[2], true);
            } else {
                return new Todo(entryParts[2], false);
            }
        } else if (entryParts[0].equals("D")) {
            if (entryParts[1].equals("1")) {
                return new Deadline(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")), true);
            } else {
                return new Deadline(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")), false);
            }
        } else {
            assert(entryParts[0].equals("E"));
            if (entryParts[1].equals("1")) {
                return new Event(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")), true);
            } else {
                return new Event(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")), false);
            }
        }
    }
}
