package duke.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

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
    private final String[] acceptableInputs = new String[]{"mark", "unmark", "todo", "deadline", "event",
        "delete", "find", "remind"};

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
        String[] inputs = input.split(" ");
        String command = inputs[0];
        boolean isValidCommand = false;
        for (String acceptableInput : acceptableInputs) {
            if (command.equals(acceptableInput)) {
                isValidCommand = true;
                break;
            }
        }
        if (!isValidCommand) {
            throw new WrongInputException("D: D: D: I don't understand the input D: D: D:");
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
        assert input.contains(command);
        String[] inputs = input.split(command + " ");
        if (listSize == 0) {
            throw new WrongInputException("D: D: D: There are no items in the list D: D: D:");
        }
        if (inputs.length > 2) {
            throw new WrongInputException("D: D: D: The " + command
                    + " should be followed by a single number D: D: D:");
        }

        try {
            int taskNumber = Integer.parseInt(inputs[1]);
            boolean isTaskNumberValid = taskNumber <= listSize && taskNumber > 0;
            if (!isTaskNumberValid) {
                throw new WrongInputException("D: D: D: The task number isn't in the list D: D: D:");
            }
            return taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new WrongInputException("D: D: D: The input doesn't seem to contain a number D: D: D:");
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
            throws WrongInputException, IncompleteInputException {
        try {
            String[] inputs = input.split(" " + format + " ");
            if (inputs.length < 2) {
                throw new IncompleteInputException("D: D: D: The description of a " + command
                        + " is incorrect or empty D: D: D:");
            }
            String[] finalDescription = new String[2];
            if (inputs.length == 2) {
                String[] description = inputs[0].split(command + " ");
                if (description.length == 2) {
                    finalDescription[0] = description[1];
                } else {
                    StringBuilder firstPart = new StringBuilder();
                    for (int i = 1; i < description.length; i++) {
                        firstPart.append(description[i]);
                        if (i < description.length - 1) {
                            firstPart.append(command).append(" ");
                        }
                    }
                    finalDescription[0] = firstPart.toString();
                }
                finalDescription[1] = inputs[1];
            } else {
                // "format" occurs more than once; time is taken to be the string beyond the last occurrence
                String[] description = inputs[0].split(command + " ");
                StringBuilder firstPart = new StringBuilder();
                if (description.length == 2) {
                    firstPart.append(description[1]);
                } else {
                    for (int i = 1; i < description.length; i++) {
                        firstPart.append(description[i]);
                        if (i < description.length - 1) {
                            firstPart.append(command).append(" ");
                        }
                    }
                }
                for (int i = 1; i < inputs.length - 1; i++) {
                    if (i < inputs.length - 1) {
                        firstPart.append(" ").append(format).append(" ");
                    }
                    firstPart.append(inputs[i]);
                }
                finalDescription[0] = firstPart.toString();
                finalDescription[1] = inputs[inputs.length - 1];
            }
            return finalDescription;
        } catch (PatternSyntaxException e) {
            throw new WrongInputException("D: D: D: Please specify the timing using " + format + " D: D: D:");
        }
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
        String[] inputs = input.split(command + " ");
        if (inputs.length == 1) {
            throw new IncompleteInputException("D: D: D: The description of a " + command
                    + " cannot be empty D: D: D:");
        }
        if (inputs.length == 2) {
            return inputs[1];
        }
        StringBuilder finalDescription = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            finalDescription.append(inputs[i]);
            if (i < inputs.length - 1) {
                finalDescription.append(command).append(" ");
            }
        }
        return finalDescription.toString();
    }

    /**
     * Adds a reminder to the task with task number specified in the user input
     * and returns the task.
     *
     * @param input The user input.
     * @param taskList The task list to retrieve the task from.
     * @return The task specified by the task number in the user input.
     * @throws WrongInputException If the input format does not match the required format.
     */
    public Task parseReminderDescription(String input, TaskList taskList) throws WrongInputException {
        String[] inputs = input.split(" ");
        if (inputs.length != 4) {
            throw new WrongInputException("Unrecognized format D: D: D:"
                    + " Please reformat in 'remind [task number] [reminder date in YYYY-MM-DD]"
                    + "[reminder time in HH:MM]'!");
        }
        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new WrongInputException("Task with specified index does not exist!");
            }
            Task taskToRemind = taskList.getTask(index);
            LocalDateTime reminderDateTime = LocalDateTime.of(LocalDate.parse(inputs[2]),
                    LocalTime.parse(inputs[3]));
            if (reminderDateTime.isBefore(LocalDateTime.now())) {
                throw new WrongInputException("D: The specified date/time has already passed!");
            }
            taskToRemind.setReminder(reminderDateTime);
            return taskToRemind;
        } catch (NumberFormatException e) {
            throw new WrongInputException("Cannot recognize task number D: D: Please reformat in "
                    + "'remind [task number] [reminder date in YYYY-MM-DD]"
                    + "[reminder time in HH:MM]' format!");
        } catch (DateTimeParseException e) {
            throw new WrongInputException("Cannot recognize date/time format D: D: "
                    + "Date should be in YYYY-MM-DD and time should be in HH:MM!");
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
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        true);
            } else {
                return new Deadline(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        false);
            }
        } else {
            assert(entryParts[0].equals("E"));
            if (entryParts[1].equals("1")) {
                return new Event(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        true);
            } else {
                return new Event(entryParts[2],
                        LocalDate.parse(entryParts[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        false);
            }
        }
    }
}
