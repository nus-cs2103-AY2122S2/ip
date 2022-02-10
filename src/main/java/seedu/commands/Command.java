package seedu.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

/**
 * Contains the bare minimum functions that all commands should contain
 */
public abstract class Command {

    protected static boolean isExit = false;
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");

    /**
     * Checks if input instruction follows format
     *
     * @param inst the input task to be executed on
     * @throws DukeException Throws if mistakes were found
     */
    public abstract void input(String inst) throws DukeException;
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Converts index of list in string form into integer form.
     *
     * @param idx Index of task list in string form
     * @return Index of task list in integer form.
     * @throws DukeException Input is not an integer.
     */
    public int checkIdx(String idx) throws DukeException {
        checkExist(idx);
        try {
            return Integer.parseInt(idx) - 1;
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
}
