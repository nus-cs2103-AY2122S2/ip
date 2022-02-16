package seedu.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

/**
 * The Command abstract class
 */
public abstract class Command {

    protected static boolean isExit = false;
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");
    protected final int INDEX_OFFSET = 1;

    public abstract void validate(String inst) throws DukeException;
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Converts string to integer form.
     *
     * @param str A number represented as a string
     * @return An integer
     * @throws DukeException Input is not an integer or number is negative
     */
    public int checkInt(String str) throws DukeException {
        checkExist(str);
        try {
            int num = Integer.parseInt(str);

            if (num < 0) {
                throw new DukeException("Number should not be less than 0");
            } else {
                return num;
            }
        } catch (NumberFormatException e) {
            throw new DukeException("That is not a number.");
        }
    }

    /**
     * Checks whether instruction is blank
     *
     * @param inst The string directly after the command type from the input
     * @return The string directly after the command type from the input
     * @throws DukeException There was no string after the command type from the input
     */
    public String checkExist(String inst) throws DukeException {
        if (inst.equals("")) {
            throw new DukeException("No instruction in command entered");
        } else {
            return inst;
        }
    }

    /**
     * Converts datetime in string to datetime in LocalDateTime format.
     *
     * @param dateInString Datetime in string form
     * @return DateTime in LocalDateTime format
     * @throws DukeException Cannot parse string into LocalDateTime format.
     */
    public LocalDateTime checkDateTime(String dateInString) throws DukeException {
        try {
            return LocalDateTime.parse(dateInString.toUpperCase(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("That date is in the wrong format\nNeed to follow this example: 1/3/2022 07:00 PM");
        }
    }

    /**
     * Returns state of the isExit boolean
     *
     * @return state of the isExit boolean
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * Returns the task to be shown in the gui
     *
     * @param prefix String unique to each command
     * @param task The task
     * @return A string to be used in the gui
     */
    public String show(String prefix, Task task) {
        return prefix + "\n" +
                "\tType: " + task.getType() + "\n" +
                "\tPriority: " + task.getPriority() + "\n" +
                "\tCompleted?: " + task.getCompleted() + "\n" +
                "\tDescription: " + task.getDescription() + "\n\n";
    }
}
