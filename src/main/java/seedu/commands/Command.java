package seedu.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.DukeException;
import seedu.storage.TaskList;
import seedu.task.Task;

public abstract class Command {

    protected static boolean isExit = false;
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");
    protected final int INDEX_OFFSET = 1;

    /**
     * Checks if input instruction follows format
     *
     * @param inst the input task to be executed on
     * @throws DukeException Throws if mistakes were found
     */
    public abstract void validate(String inst) throws DukeException;
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Converts string to integer form.
     *
     * @param str Number in string type
     * @return An integer.
     * @throws DukeException Input is not an integer.
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
     * @param inst The string directly after the command type from the input.
     * @return The string directly after the command type from the input.
     * @throws DukeException There was no string after the command type from the input.
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

    public static boolean isExit() {
        return isExit;
    }

    public String print(String type, Task task) {
        StringBuilder out = new StringBuilder(type + "\n");
        out.append("\tType: " + task.getType() + "\n");
        out.append("\tPriority: " + task.getPriority() + "\n");
        out.append("\tCompleted?: " + task.getCompleted() + "\n");
        out.append("\tDescription: " + task.getDescription() + "\n\n");
        return out.toString();
    }
}
