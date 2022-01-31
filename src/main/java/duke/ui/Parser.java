package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import duke.exception.IncompleteInputException;
import duke.exception.WrongInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Helps to make sense of user input */
public class Parser {

    /**
     * Creates a new Parser instance.
     */
    public Parser() {
    }

    /**
     * Returns the command part of the user input.
     *
     * @param s The user input.
     * @return The command part of the user input.
     * @throws WrongInputException If the input does not contain a valid command.
     */
    public String parseCommand(String s) throws WrongInputException {
        String[] inputs = s.split(" ");
        String[] acceptableInputs = new String[]
            {"mark", "unmark", "todo", "deadline", "event", "delete", "find"};
        boolean isAcceptable = false;
        for (String acceptableInput : acceptableInputs) {
            if (acceptableInput.equals(inputs[0])) {
                isAcceptable = true;
                break;
            }
        }
        if (!isAcceptable) {
            throw new WrongInputException("D: D: D: I don't understand the input D: D: D:");
        }
        return inputs[0];
    }

    /**
     * Returns the numerical part of the user input.
     * Assumes the user input contains a command that requires a numerical input.
     *
     * @param s The user input.
     * @param command The command contained in the user input.
     * @param length The length of the task list.
     * @return The numerical part of the user input.
     * @throws WrongInputException If the input is not in the expected form.
     */
    public int parseNumericalDescription(String s, String command, int length) throws WrongInputException {
        String[] inputs = s.split(command + " ");
        if (length == 0) {
            throw new WrongInputException("D: D: D: There are no items in the list D: D: D:");
        }
        if (inputs.length > 2) {
            throw new WrongInputException("D: D: D: The " + command
                    + " should be followed by a single number D: D: D:");
        }

        try {
            int tasknumber = Integer.parseInt(inputs[1]);
            if (tasknumber > length || tasknumber < 1) {
                throw new WrongInputException("D: D: D: The task number isn't in the list D: D: D:");
            }
            return tasknumber - 1;
        } catch (NumberFormatException e) {
            throw new WrongInputException("D: D: D: The input doesn't seem to contain a number D: D: D:");
        }
    }

    /**
     * Returns an array containing the task description and its associated time.
     *
     * @param s The user input.
     * @param command The command contained in the user input.
     * @param format The regular expression that indicates the timing part of the task.
     * @return An array containing the task description and its associated time.
     * @throws WrongInputException If the regular expression cannot be found in the user input.
     * @throws IncompleteInputException If the input is incomplete.
     */
    public String[] parseFormatDescription(String s, String command, String format)
            throws WrongInputException, IncompleteInputException {
        try {
            String[] inputs = s.split(" " + format + " ");
            if (inputs.length < 2) {
                throw new IncompleteInputException("D: D: D: The description of a " + command
                        + " is incorrect or empty D: D: D:");
            }
            String[] params = new String[2];
            if (inputs.length == 2) {
                String[] desc = inputs[0].split(command + " ");
                if (desc.length == 2) {
                    params[0] = desc[1];
                } else {
                    StringBuilder firstPart = new StringBuilder();
                    for (int i = 1; i < desc.length; i++) {
                        firstPart.append(desc[i]);
                        if (i < desc.length - 1) {
                            firstPart.append(command + " ");
                        }
                    }
                    params[0] = firstPart.toString();
                }
                params[1] = inputs[1];
            } else {
                // inputs.length > 2
                // more than one occurrence of "format"; beyond the last one is taken to be the time
                String[] desc = inputs[0].split(command + " ");
                StringBuilder firstPart = new StringBuilder();
                if (desc.length == 2) {
                    firstPart.append(desc[1]);
                } else {
                    for (int i = 1; i < desc.length; i++) {
                        firstPart.append(desc[i]);
                        if (i < desc.length - 1) {
                            firstPart.append(command + " ");
                        }
                    }
                }
                for (int i = 1; i < inputs.length - 1; i++) {
                    if (i < inputs.length - 1) {
                        firstPart.append(" " + format + " ");
                    }
                    firstPart.append(inputs[i]);
                }
                params[0] = firstPart.toString();
                params[1] = inputs[inputs.length - 1];
            }
            return params;
        } catch (PatternSyntaxException e) {
            throw new WrongInputException("D: D: D: Please specify the timing using " + format + " D: D: D:");
        }
    }

    /**
     * Returns the task description contained in the user input.
     *
     * @param s The user input.
     * @param command The command contained in the user input.
     * @return The task description.
     * @throws IncompleteInputException If the input is incomplete.
     */
    public String parseStringDescription(String s, String command) throws IncompleteInputException {
        String[] inputs = s.split(command + " ");
        if (inputs.length == 1) {
            throw new IncompleteInputException("D: D: D: The description of a " + command
                    + " cannot be empty D: D: D:");
        }
        if (inputs.length == 2) {
            return inputs[1];
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            output.append(inputs[i]);
            if (i < inputs.length - 1) {
                output.append(command + " ");
            }
        }
        return output.toString();
    }

    /**
     * Returns a Task object from parsing a saved line in a storage file.
     *
     * @param entry A line from a storage file containing the description of a Task.
     * @return A Task object formed from parsing a line in a storage file.
     * @throws DateTimeParseException If any time in the file is not in the correct format.
     */
    public static Task parseFileFormat(String entry) throws DateTimeParseException {
        String[] entrySplit = entry.split(" \\| ");
        if (entrySplit[0].equals("T")) {
            if (entrySplit[1].equals("1")) {
                return new Todo(entrySplit[2], true);
            } else {
                return new Todo(entrySplit[2], false);
            }
        } else if (entrySplit[0].equals("D")) {
            if (entrySplit[1].equals("1")) {
                return new Deadline(entrySplit[2],
                        LocalDate.parse(entrySplit[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        true);
            } else {
                return new Deadline(entrySplit[2],
                        LocalDate.parse(entrySplit[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        false);
            }
        } else {
            if (entrySplit[1].equals("1")) {
                return new Event(entrySplit[2],
                        LocalDate.parse(entrySplit[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        true);
            } else {
                return new Event(entrySplit[2],
                        LocalDate.parse(entrySplit[3], DateTimeFormatter.ofPattern("MMM dd yyyy")),
                        false);
            }
        }
    }
}
